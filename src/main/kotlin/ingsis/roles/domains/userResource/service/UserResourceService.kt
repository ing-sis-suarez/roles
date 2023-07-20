package ingsis.roles.domains.userResource.service

import ingsis.roles.domains.userResource.dto.EditUserResourceDTO
import ingsis.roles.domains.userResource.dto.IdList
import java.util.*

interface UserResourceService {

    fun createUserResource(dto: EditUserResourceDTO, ownerId: String): UUID
    fun deleteUserResource(dto: EditUserResourceDTO, ownerId: String): Boolean

    fun getUserResourcesByOwner(ownerId: String, resourceType: String): IdList
    fun getRoles(resourceId: UUID, resourceType: String, userId: String): List<String>
}