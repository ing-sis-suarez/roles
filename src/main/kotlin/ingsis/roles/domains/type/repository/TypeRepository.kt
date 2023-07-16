package ingsis.roles.domains.type.repository

import ingsis.roles.model.ResourceType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface TypeRepository: JpaRepository<ResourceType, UUID> {

    @Query("SELECT t FROM ResourceType t WHERE t.name = :name")
    fun findByName(name: String): ResourceType?
}