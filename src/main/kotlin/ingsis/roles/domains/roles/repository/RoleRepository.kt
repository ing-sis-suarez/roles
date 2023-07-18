package ingsis.roles.domains.roles.repository

import ingsis.roles.model.Resource
import ingsis.roles.model.ResourceType
import ingsis.roles.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface RoleRepository: JpaRepository<Role, UUID> {

    @Query("SELECT r FROM Role r WHERE r.name = :name AND r.resourceType = :resourceType")
    fun findByNameAndResourceType(name: String, resourceType: String): Role?
}