package ingsis.roles.domains.resource.repository

import ingsis.roles.model.Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ResourceRepository: JpaRepository<Resource, UUID> {

    @Query("SELECT r FROM Resource r WHERE r.resourceId = :externalResourceId AND r.resourceType.id = :resourceTypeId")
    fun findByExternalResourceIdAndResourceType(externalResourceId: UUID, resourceTypeId: UUID): Resource?
}