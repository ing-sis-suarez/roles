package ingsis.roles.domains.resource.repository

import ingsis.roles.model.Resource
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ResourceRepository: JpaRepository<Resource, UUID> {
}