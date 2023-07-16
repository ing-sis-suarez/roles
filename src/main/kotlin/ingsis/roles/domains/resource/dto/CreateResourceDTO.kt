package ingsis.roles.domains.resource.dto

import java.util.*

data class CreateResourceDTO(val resourceId: UUID, val userId: UUID, val resourceType: String)