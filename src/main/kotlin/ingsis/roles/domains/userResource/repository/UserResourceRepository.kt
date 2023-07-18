package ingsis.roles.domains.userResource.repository

import ingsis.roles.model.User_Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface UserResourceRepository: JpaRepository<User_Resource, UUID> {

    @Query("SELECT ur FROM User_Resource ur WHERE ur.roleId = :roleId AND ur.userId = :userId AND ur.resourceId = :resourceId")
    fun findByRoleIdUserIdAndResourceId(@Param("roleId") roleId: UUID, @Param("userId") userId: UUID, @Param("resourceId") resourceId: UUID): User_Resource

    @Query("SELECT ur FROM User_Resource ur WHERE ur.userId = :userId AND ur.resourceId = :resourceId")
    fun findByResourceIdAndUserId(@Param("resourceId") resourceId: UUID, @Param("userId") userId: UUID): List<User_Resource>
}