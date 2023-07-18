package ingsis.roles.domains.resource.controller

import ingsis.roles.domains.resource.dto.CreateResourceDTO
import ingsis.roles.domains.resource.service.ResourceService
import ingsis.roles.domains.userResource.dto.DeleteResourceRequestDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*

@RestController
@CrossOrigin("*")
class ResourceController {

    @Autowired
    private var resourceService: ResourceService

    @Autowired
    constructor(resourceService: ResourceService) {
        this.resourceService = resourceService
    }

    @GetMapping("/hello")
    fun hello() = "Hello"

    @PostMapping("/resource/create")
    fun createResource(@RequestBody dto: CreateResourceDTO, principal: Principal): ResponseEntity<UUID> {
        return ResponseEntity(resourceService.createResource(dto, UUID.fromString(principal.name)), HttpStatus.CREATED)
    }

    @DeleteMapping("/resource/delete")
    fun deleteResource(@RequestBody dto: DeleteResourceRequestDTO, principal: Principal): ResponseEntity<Boolean> {
        return ResponseEntity(resourceService.deleteResource(dto), HttpStatus.OK)
    }


}