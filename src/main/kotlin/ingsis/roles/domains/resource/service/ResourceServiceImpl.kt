package ingsis.roles.domains.resource.service

import ingsis.roles.domains.resource.dto.CreateResourceDTO
import ingsis.roles.domains.resource.repository.ResourceRepository
import ingsis.roles.domains.roles.repository.RoleRepository
import ingsis.roles.domains.type.repository.TypeRepository
import ingsis.roles.domains.userResource.dto.DeleteResourceRequestDTO
import ingsis.roles.domains.userResource.repository.UserResourceRepository
import ingsis.roles.error.HTTPError
import ingsis.roles.model.Resource
import ingsis.roles.model.User_Resource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class ResourceServiceImpl : ResourceService {

    @Autowired
    private var typeRepository: TypeRepository

    @Autowired
    private var resourceRepository: ResourceRepository

    @Autowired
    private var userResourceRepository: UserResourceRepository

    @Autowired
    private var roleRepository: RoleRepository

    @Autowired
    constructor(
        typeRepository: TypeRepository,
        resourceRepository: ResourceRepository,
        userResourceRepository: UserResourceRepository,
        roleRepository: RoleRepository
    ) {
        this.typeRepository = typeRepository
        this.resourceRepository = resourceRepository
        this.userResourceRepository = userResourceRepository
        this.roleRepository = roleRepository
    }

    override fun getResourceByresourceTypeAndOwner(resourceType: String, ownerId: String): UUID? {
        val type = typeRepository.findByName(resourceType) ?: throw HTTPError("Resource type not found", HttpStatus.NOT_FOUND)
        val role = roleRepository.findByNameAndResourceType("owner", type) ?: throw HTTPError("Owner role not found", HttpStatus.NOT_FOUND)
        val userResource = userResourceRepository.findByRoleIdUserIdAndResourceType(role, ownerId, type) ?: throw HTTPError("Resource not found", HttpStatus.NOT_FOUND)
        return userResource.resource!!.resourceId!!
    }

    override fun createResource(dto: CreateResourceDTO, ownerId: String): UUID {
        val type = typeRepository.findByName(dto.resourceType) ?: throw HTTPError("Resource type not found", HttpStatus.NOT_FOUND)
        val role = roleRepository.findByNameAndResourceType("owner", type) ?: throw HTTPError("Owner role not found", HttpStatus.NOT_FOUND)
        val resource = Resource(type, dto.resourceId)
        val newResource = resourceRepository.save(resource)
        val availableRoles = roleRepository.findByResourceType(type)
        val userResources = availableRoles.map { uniqueRole -> User_Resource(ownerId, uniqueRole, newResource) }
        userResourceRepository.saveAll(userResources)
        return newResource.id!!
    }

    override fun deleteResource(dto: DeleteResourceRequestDTO): Boolean {
        val type = typeRepository.findByName(dto.resourceType) ?: throw HTTPError("Resource type not found", HttpStatus.NOT_FOUND)
        val resource = resourceRepository.findByExternalResourceIdAndResourceType(dto.resourceId, type) ?: throw HTTPError("Resource not found", HttpStatus.NOT_FOUND)
        resourceRepository.delete(resource)
        return true
    }


}