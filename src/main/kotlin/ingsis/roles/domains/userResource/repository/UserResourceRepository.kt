package ingsis.roles.domains.userResource.repository

import ingsis.roles.model.Resource
import ingsis.roles.model.ResourceType
import ingsis.roles.model.Role
import ingsis.roles.model.User_Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface UserResourceRepository: JpaRepository<User_Resource, UUID> {

    @Query("" +
            "SELECT ur " +
            "FROM User_Resource ur " +
            "WHERE ur.userId = :ownerId " +
            "AND ur.resource.resourceType = :resourceType " +
            "AND ur.role.name = 'owner'")
    fun findByOwnerId(ownerId: String, resourceType: ResourceType): List<User_Resource>
    
    @Query("SELECT ur FROM User_Resource ur WHERE ur.role = :role AND ur.userId = :userId AND ur.resource.resourceType = :resourceType")
    fun findByRoleIdUserIdAndResourceType(role: Role, userId: String, resourceType: ResourceType): User_Resource?

    @Query("SELECT ur FROM User_Resource ur WHERE ur.role = :role AND ur.userId = :userId AND ur.resource = :resource")
    fun findByRoleIdUserIdAndResourceId(role: Role, userId: String, resource: Resource): User_Resource

    @Query("SELECT ur FROM User_Resource ur WHERE ur.userId = :userId AND ur.resource = :resource")
    fun findByResourceIdAndUserId(resource: Resource, userId: String): List<User_Resource>
}