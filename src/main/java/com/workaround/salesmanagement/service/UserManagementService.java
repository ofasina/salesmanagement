/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.service;

import com.workaround.salesmanagement.DTO.ClientDTO;
import com.workaround.salesmanagement.DTO.ResponseDTO;
import com.workaround.salesmanagement.DTO.User;
import com.workaround.salesmanagement.model.UserRole;
import com.workaround.salesmanagement.model.Users;
import com.workaround.salesmanagement.repository.UserRepository;
import com.workaround.salesmanagement.repository.UserRoleRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olawale
 */
@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final UserRepository userRepo;
    private final UserRoleRepository userRoleRepo;
    
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseDTO createUserProfile(User request) {

        //check for exiting user
        Optional<Users> existingUser = userRepo.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            return new ResponseDTO(HttpStatus.BAD_REQUEST.toString(), "Existing user with email", null);
        }
        // check for exiting role
//        Optional<UserRole> existingRole = userRoleRepo.findByRole(request.getRole());
//        if (existingRole.isEmpty()) {
//            return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "Role not found", null);
//        }
        Users newUser = new Users();
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setCreatedBy("SysAdm");
        newUser.setEmail(request.getEmail());
        newUser.setName(request.getName());
        newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        newUser.setUserRole(request.getRole());
        userRepo.save(newUser);
          return new ResponseDTO(HttpStatus.CREATED.toString(), "Created", newUser);
        
    }
}
