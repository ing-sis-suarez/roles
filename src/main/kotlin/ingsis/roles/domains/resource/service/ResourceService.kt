package ingsis.roles.domains.resource.service

import ingsis.roles.domains.resource.dto.CreateResourceDTO

interface ResourceService {

    fun createResource(role: CreateResourceDTO): Boolean
}