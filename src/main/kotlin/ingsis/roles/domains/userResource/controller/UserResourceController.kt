package ingsis.roles.domains.userResource.controller;


import ingsis.roles.domains.userResource.dto.EditUserResourceDTO
import ingsis.roles.domains.userResource.dto.IdList
import ingsis.roles.domains.userResource.dto.UserRolesResponseDTO
import ingsis.roles.domains.userResource.service.UserResourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*

@RestController
@CrossOrigin("*")
class UserResourceController {

    @Autowired
    private var userResourceService: UserResourceService

    @Autowired
    constructor(userResourceService: UserResourceService) {
        this.userResourceService = userResourceService
    }

    @PostMapping("/share")
    fun createUserResource(@RequestBody dto: EditUserResourceDTO, principal: Principal): ResponseEntity<UUID> {
        return ResponseEntity(userResourceService.createUserResource(dto, principal.name), HttpStatus.CREATED)
    }

    @DeleteMapping("/share")
    fun deleteUserResource(@RequestBody dto: EditUserResourceDTO, principal: Principal): ResponseEntity<Boolean> {
        return ResponseEntity(userResourceService.deleteUserResource(dto, principal.name), HttpStatus.OK)
    }

    @GetMapping("/role")
    fun getRole(
        @RequestParam(name = "resourceId") resourceId: UUID,
        @RequestParam(name = "resourceType") resourceType: String,
        principal: Principal
    ): ResponseEntity<UserRolesResponseDTO> {
        return ResponseEntity(
            UserRolesResponseDTO(userResourceService.getRoles(resourceId, resourceType, principal.name)),
            HttpStatus.OK
        )
    }

    @GetMapping("/resources")
    fun getResourcesByOwnerAndType(
        @RequestParam(name = "resourceType") resourceType: String,
        principal: Principal
    ): ResponseEntity<IdList> {
        return ResponseEntity(userResourceService.getUserResourcesByOwner(principal.name, resourceType), HttpStatus.OK)
    }
}
