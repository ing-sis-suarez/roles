package ingsis.roles.domains.resource.dto

import java.util.*

data class CreateResourceDTO(val resourceId: UUID, val userId: String, val resourceType: String)