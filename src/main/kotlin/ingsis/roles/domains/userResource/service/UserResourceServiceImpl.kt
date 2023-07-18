package ingsis.roles.domains.userResource.service

import ingsis.roles.domains.resource.repository.ResourceRepository
import ingsis.roles.domains.roles.repository.RoleRepository
import ingsis.roles.domains.type.repository.TypeRepository
import ingsis.roles.domains.userResource.dto.CreateUserResourceDTO
import ingsis.roles.domains.userResource.repository.UserResourceRepository
import ingsis.roles.error.HTTPError
import ingsis.roles.model.ResourceType
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

    override fun createUserResource(dto: CreateUserResourceDTO, ownerId: UUID): UUID {
        val resourceType = typeRepository.findByName(dto.resourceType) ?: throw HTTPError("Resource type not found", HttpStatus.NOT_FOUND)
        val resource = resourceRepository.findByExternalResourceIdAndResourceType(dto.resourceId, resourceType.id!!) ?: throw HTTPError("Resource not found", HttpStatus.NOT_FOUND)
        val ownerRole = roleRepository.findByNameAndResourceType("owner", dto.resourceType) ?: throw HTTPError("Owner role not found", HttpStatus.NOT_FOUND)
        val hasOwnerRole = userResourceRepository.findByRoleIdUserIdAndResourceId(ownerRole.id!!, ownerId, resource.id!!) ?: throw HTTPError("User is not owner of resource", HttpStatus.UNAUTHORIZED)
        val newRole = roleRepository.findByNameAndResourceType(dto.role, dto.resourceType) ?: throw HTTPError("Role not found for this resource", HttpStatus.NOT_FOUND)
        val userResource = User_Resource(dto.userId, newRole, resource)
        val userRole = userResourceRepository.save(userResource)
        return userRole.id!!
    }

    override fun deleteUserResource(dto: CreateUserResourceDTO, ownerId: UUID): Boolean {
        val resourceType = typeRepository.findByName(dto.resourceType) ?: throw HTTPError("Resource type not found", HttpStatus.NOT_FOUND)
        val resource = resourceRepository.findByExternalResourceIdAndResourceType(dto.resourceId, resourceType.id!!) ?: throw HTTPError("Resource not found", HttpStatus.NOT_FOUND)
        val ownerRole = roleRepository.findByNameAndResourceType("owner", dto.resourceType) ?: throw HTTPError("Owner role not found", HttpStatus.NOT_FOUND)
        val hasOwnerRole = userResourceRepository.findByRoleIdUserIdAndResourceId(ownerRole.id!!, ownerId, resource.id!!) ?: throw HTTPError("User is not owner of resource", HttpStatus.UNAUTHORIZED)
        val roleToDelete = roleRepository.findByNameAndResourceType(dto.role, dto.resourceType) ?: throw HTTPError("Role not found for this resource", HttpStatus.NOT_FOUND)
        val userResource = userResourceRepository.findByRoleIdUserIdAndResourceId(roleToDelete.id!!, dto.userId, resource.id!!) ?: throw HTTPError("User does not have this role", HttpStatus.NOT_FOUND)
        userResourceRepository.delete(userResource)
        return true
    }

    override fun getRoles(resourceId: UUID, resourceType: String, role: String, userId: UUID): List<String> {
        val type = typeRepository.findByName(resourceType) ?: throw HTTPError("Resource type not found", HttpStatus.NOT_FOUND)
        val resource = resourceRepository.findByExternalResourceIdAndResourceType(resourceId, type.id!!) ?: throw HTTPError("Resource not found", HttpStatus.NOT_FOUND)
        val userRoles = userResourceRepository.findByResourceIdAndUserId(resource.id!!, userId)
        val roles = userRoles.map { it.roleId!!.name!! }
        return roles
    }
}