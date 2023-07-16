package ingsis.roles.domains.resource.controller

import ingsis.roles.domains.resource.dto.CreateResourceDTO
import ingsis.roles.domains.resource.service.ResourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@CrossOrigin("*")
@RequestMapping("/resources")
class ResourceController {

    @Autowired
    private var resourceService: ResourceService

    @Autowired
    constructor(resourceService: ResourceService) {
        this.resourceService = resourceService
    }

    @GetMapping("/hello")
    fun hello() = "Hello"

    @PostMapping("/create")
    fun createResource(@RequestBody dto: CreateResourceDTO, principal: Principal) {
        val result = resourceService.createResource(dto)
    }
}