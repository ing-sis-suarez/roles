package ingsis.roles.domains.roles.controller

import ingsis.roles.domains.resource.dto.CreateResourceDTO
import ingsis.roles.domains.roles.services.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
class RoleController {

    @Autowired
    private var roleService: RoleService

    @Autowired
    constructor(roleService: RoleService) {
        this.roleService = roleService
    }

    @PostMapping("/create")
    fun createResource(@RequestBody role: CreateResourceDTO) {
        val result = roleService.createResource(role)
    }
}