/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.controller;

import com.workaround.salesmanagement.DTO.ResponseDTO;
import com.workaround.salesmanagement.DTO.User;
import com.workaround.salesmanagement.constants.Endpoints;
import com.workaround.salesmanagement.service.UserRoleManagementService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Olawale
 */
@RestController
@RequiredArgsConstructor
public class RoleController {

    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;
    private final UserRoleManagementService userRoleService;

    @PostMapping(value = Endpoints.CREATE_ROLE, consumes = JSON, produces = JSON)
    public ResponseDTO createRole(@RequestParam(required = true) String role) {
        return userRoleService.createRole(role);
    }
}
