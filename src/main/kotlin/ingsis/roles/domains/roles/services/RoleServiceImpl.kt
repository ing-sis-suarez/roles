package ingsis.roles.domains.roles.services

import ingsis.roles.domains.resource.dto.CreateResourceDTO
import ingsis.roles.domains.roles.repository.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl : RoleService {

    @Autowired
    private var roleRepository: RoleRepository

    @Autowired
    constructor(roleRepository: RoleRepository) {
        this.roleRepository = roleRepository
    }

    override fun createResource(role: CreateResourceDTO): Boolean {
        TODO("Not yet implemented")
    }


}