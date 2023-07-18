package ingsis.roles.domains.userResource.repository

import ingsis.roles.model.User_Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface UserResourceRepository: JpaRepository<User_Resource, UUID> {

    @Query("SELECT ur FROM User_Resource ur WHERE ur.roleId = :name AND ur.userId = :userId AND ur.resourceId = :resourceId")
    fun findByRoleIdUserIdAndResourceId(roleId: UUID, userId: UUID, resourceId: UUID): User_Resource?

    @Query("SELECT ur FROM User_Resource ur WHERE ur.userId = :userId AND ur.resourceId = :resourceId")
    fun findByResourceIdAndUserId(resourceId: UUID, userId: UUID): List<User_Resource>
}