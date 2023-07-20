package ingsis.roles.domains.resource.service

import ingsis.roles.domains.resource.dto.CreateResourceDTO
import ingsis.roles.domains.userResource.dto.DeleteResourceRequestDTO
import java.util.*

interface ResourceService {

    fun getResourceByresourceTypeAndOwner(resourceType: String, ownerId: String): UUID?

    fun createResource(dto: CreateResourceDTO, ownerId: String): UUID

    fun deleteResource(dto: DeleteResourceRequestDTO): Boolean
}