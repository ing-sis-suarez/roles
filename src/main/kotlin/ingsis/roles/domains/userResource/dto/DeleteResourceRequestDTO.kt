package ingsis.roles.domains.userResource.dto

import java.util.UUID

data class DeleteResourceRequestDTO(val resourceId: UUID, val resourceType: String) {
}