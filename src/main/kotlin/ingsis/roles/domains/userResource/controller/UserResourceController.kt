package ingsis.roles.domains.userResource.controller;


import ingsis.roles.domains.userResource.dto.EditUserResourceDTO;
import ingsis.roles.domains.userResource.dto.UserRolesResponseDTO
import ingsis.roles.domains.userResource.service.UserResourceService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.UUID;

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
}
