package ingsis.roles.domains.resource.service

import ingsis.roles.domains.resource.dto.CreateResourceDTO
import ingsis.roles.domains.userResource.dto.CreateUserResourceDTO
import ingsis.roles.domains.userResource.dto.DeleteResourceRequestDTO
import java.util.*

interface ResourceService {

    fun createResource(dto: CreateResourceDTO, ownerId: UUID): UUID

    fun deleteResource(dto: DeleteResourceRequestDTO): Boolean
}