package ingsis.roles.domains.resource.dto

import java.util.*

data class ResourceDTO(val id: UUID, val resourceId: UUID, val userId: String, val resourceType: String)