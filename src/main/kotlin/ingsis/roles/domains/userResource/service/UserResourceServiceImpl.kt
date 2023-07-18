package ingsis.roles.domains.userResource.service

import ingsis.roles.domains.resource.repository.ResourceRepository
import ingsis.roles.domains.roles.repository.RoleRepository
import ingsis.roles.domains.type.repository.TypeRepository
import ingsis.roles.domains.userResource.dto.EditUserResourceDTO
import ingsis.roles.domains.userResource.repository.UserResourceRepository
import ingsis.roles.error.HTTPError
import ingsis.roles.model.User_Resource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserResourceServiceImpl : UserResourceService {

    @Autowired
    private var roleRepository: RoleRepository

    @Autowired
    private var resourceRepository: ResourceRepository

    @Autowired
    private var userResourceRepository: UserResourceRepository

    @Autowired
    private var typeRepository: TypeRepository

    @Autowired
    constructor(
        roleRepository: RoleRepository,
        resourceRepository: ResourceRepository,
        userResourceRepository: UserResourceRepository,
        typeRepository: TypeRepository
    ) {
        this.roleRepository = roleRepository
        this.resourceRepository = resourceRepository
        this.userResourceRepository = userResourceRepository
        this.typeRepository = typeRepository
    }

    override fun createUserResource(dto: EditUserResourceDTO, ownerId: String): UUID {
        val resourceType = typeRepository.findByName(dto.resourceType) ?: throw HTTPError("Resource type not found", HttpStatus.NOT_FOUND)
        val resource = resourceRepository.findByExternalResourceIdAndResourceType(dto.resourceId, resourceType) ?: throw HTTPError("Resource not found", HttpStatus.NOT_FOUND)
        val ownerRole = roleRepository.findByNameAndResourceType("owner", resourceType) ?: throw HTTPError("Owner role not found", HttpStatus.NOT_FOUND)
        val hasOwnerRole = userResourceRepository.findByRoleIdUserIdAndResourceId(ownerRole, ownerId, resource) ?: throw HTTPError("User is not owner of resource", HttpStatus.UNAUTHORIZED)
        val newRole = roleRepository.findByNameAndResourceType(dto.role, resourceType) ?: throw HTTPError("Role not found for this resource", HttpStatus.NOT_FOUND)
        val userResource = User_Resource(dto.userId, newRole, resource)
        val userRole = userResourceRepository.save(userResource)
        return userRole.id!!
    }

    override fun deleteUserResource(dto: EditUserResourceDTO, ownerId: String): Boolean {
        val resourceType = typeRepository.findByName(dto.resourceType) ?: throw HTTPError("Resource type not found", HttpStatus.NOT_FOUND)
        val resource = resourceRepository.findByExternalResourceIdAndResourceType(dto.resourceId, resourceType) ?: throw HTTPError("Resource not found", HttpStatus.NOT_FOUND)
        val ownerRole = roleRepository.findByNameAndResourceType("owner", resourceType) ?: throw HTTPError("Owner role not found", HttpStatus.NOT_FOUND)
        val hasOwnerRole = userResourceRepository.findByRoleIdUserIdAndResourceId(ownerRole, ownerId, resource) ?: throw HTTPError("User is not owner of resource", HttpStatus.UNAUTHORIZED)
        val roleToDelete = roleRepository.findByNameAndResourceType(dto.role, resourceType) ?: throw HTTPError("Role not found for this resource", HttpStatus.NOT_FOUND)
        val userResource = userResourceRepository.findByRoleIdUserIdAndResourceId(roleToDelete, dto.userId, resource) ?: throw HTTPError("User does not have this role", HttpStatus.NOT_FOUND)
        userResourceRepository.delete(userResource)
        return true
    }

    override fun getRoles(resourceId: UUID, resourceType: String, userId: String): List<String> {
        val type = typeRepository.findByName(resourceType) ?: throw HTTPError("Resource type not found", HttpStatus.NOT_FOUND)
        val resource = resourceRepository.findByExternalResourceIdAndResourceType(resourceId, type) ?: throw HTTPError("Resource not found", HttpStatus.NOT_FOUND)
        val userRoles = userResourceRepository.findByResourceIdAndUserId(resource, userId)
        val roles = userRoles.map { it.role!!.name!! }
        return roles
    }
}