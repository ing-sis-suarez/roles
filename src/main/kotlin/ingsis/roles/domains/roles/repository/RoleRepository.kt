package ingsis.roles.domains.roles.repository

import ingsis.roles.model.Resource
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface RoleRepository: JpaRepository<Resource, UUID> {
}