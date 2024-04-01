/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.service;

import com.workaround.salesmanagement.DTO.ResponseDTO;
import com.workaround.salesmanagement.model.UserRole;
import com.workaround.salesmanagement.repository.UserRoleRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olawale
 */
@Service
@RequiredArgsConstructor
public class UserRoleManagementService {

    private final UserRoleRepository userRoleRepo;

    public ResponseDTO createRole(String role) {
        UserRole createRole = new UserRole();
        //check for exiting role
        Optional<UserRole> existingRole = userRoleRepo.findByRole(role);
        if (existingRole.isPresent()) {
            return new ResponseDTO(HttpStatus.BAD_REQUEST.toString(), "Existing role", null);
        }
        BeanUtils.copyProperties(role, createRole);
        createRole.setCreatedAt(LocalDateTime.now());
        createRole.setCreatedBy("SysAdmin");

        userRoleRepo.save(createRole);
        return new ResponseDTO(HttpStatus.CREATED.toString(), "Created successfully", createRole);

    }
}
