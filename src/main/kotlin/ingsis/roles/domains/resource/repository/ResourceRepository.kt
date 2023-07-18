package ingsis.roles.domains.resource.repository

import ingsis.roles.model.Resource
import ingsis.roles.model.ResourceType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ResourceRepository: JpaRepository<Resource, UUID> {

    @Query("SELECT r FROM Resource r WHERE r.resourceId = :externalResourceId AND r.resourceType = :resourceType")
    fun findByExternalResourceIdAndResourceType(externalResourceId: UUID, resourceType: ResourceType): Resource?
}