package ingsis.roles.domains.resource.service

import ingsis.roles.domains.resource.dto.CreateResourceDTO
import ingsis.roles.domains.resource.repository.ResourceRepository
import ingsis.roles.domains.type.repository.TypeRepository
import ingsis.roles.model.Resource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResourceServiceImpl: ResourceService {

    @Autowired
    private var typeRepository: TypeRepository

    @Autowired
    private var resourceRepository: ResourceRepository

    @Autowired
    constructor(typeRepository: TypeRepository, resourceRepository: ResourceRepository) {
        this.typeRepository = typeRepository
        this.resourceRepository = resourceRepository
    }

    override fun createResource(dto: CreateResourceDTO): Boolean {

        val type = typeRepository.findByName(dto.resourceType) ?: return false
        val resource = Resource(type, dto.resourceId)
        resourceRepository.save(resource)
        return true
    }
}