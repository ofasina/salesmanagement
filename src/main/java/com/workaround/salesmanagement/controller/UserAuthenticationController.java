/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.controller;

import com.workaround.salesmanagement.DTO.AuthDTO;
import com.workaround.salesmanagement.DTO.Credential;
import com.workaround.salesmanagement.DTO.ResponseDTO;
import com.workaround.salesmanagement.DTO.User;
import com.workaround.salesmanagement.constants.Endpoints;
import com.workaround.salesmanagement.security.JwtTokenUtil;
import com.workaround.salesmanagement.service.UserManagementService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Olawale
 */
@RestController
@RequiredArgsConstructor
public class UserAuthenticationController {
    
    private final AuthenticationManager authenticationManager; 
    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;
    private final JwtTokenUtil tokenService;
    private final UserManagementService userService;
    
    @PostMapping(value = Endpoints.CREATE_USER, consumes = JSON, produces = JSON)
    public ResponseDTO createUser(@Valid @RequestBody User user) {
        return userService.createUserProfile(user);
    }
    
     @PostMapping(value = Endpoints.VALIDATE_USER, consumes = JSON, produces = JSON)
    public ResponseDTO authenticateAndGetToken(@RequestBody Credential authRequest) { 
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())); 
        if (authentication.isAuthenticated()) { 
            AuthDTO auth = new AuthDTO();
            auth.setEmail(authRequest.getEmail());
            auth.setToken(tokenService.generateToken(authRequest));
            return new ResponseDTO(HttpStatus.OK.toString(), "OK" , auth); 
        } else { 
            return new ResponseDTO(HttpStatus.UNAUTHORIZED.toString(), "Unauthorized", null); 
        } 
        }catch(Exception e){
                 return new ResponseDTO(HttpStatus.UNAUTHORIZED.toString(), "Unauthorized", null); 
        
        }
    } 
}
