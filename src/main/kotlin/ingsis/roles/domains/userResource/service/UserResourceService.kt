package ingsis.roles.domains.userResource.service

import ingsis.roles.domains.userResource.dto.CreateUserResourceDTO
import java.util.*

interface UserResourceService {

    fun createUserResource(dto: CreateUserResourceDTO, ownerId: UUID): UUID
    fun deleteUserResource(dto: CreateUserResourceDTO, ownerId: UUID): Boolean

    fun getRoles(resourceId: UUID, resourceType: String, role: String, userId: UUID): List<String>
}