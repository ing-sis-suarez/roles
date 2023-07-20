package ingsis.roles.domains.resource.service

import ingsis.roles.domains.resource.dto.CreateResourceDTO
import ingsis.roles.domains.userResource.dto.DeleteResourceRequestDTO
import ingsis.roles.domains.userResource.dto.IdList
import java.util.*

interface ResourceService {

    fun getResourcesByTypeAndRole(resourceType: String, ownerId: String, role: String): IdList

    fun getResourceByresourceTypeAndOwner(resourceType: String, ownerId: String): UUID?

    fun createResource(dto: CreateResourceDTO, ownerId: String): UUID

    fun deleteResource(dto: DeleteResourceRequestDTO): Boolean
}