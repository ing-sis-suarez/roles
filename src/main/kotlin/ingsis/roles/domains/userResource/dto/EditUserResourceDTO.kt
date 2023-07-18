package ingsis.roles.domains.userResource.dto

import java.util.*

data class EditUserResourceDTO(val resourceId: UUID, val role: String, val resourceType: String, val userId: String)