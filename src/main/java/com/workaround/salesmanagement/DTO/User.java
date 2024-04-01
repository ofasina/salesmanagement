/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Olawale
 */
@Data
public class User {

    @NotBlank(message = "Name cannot be blank, empty or null")
    private String name;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank, empty or null")
    private String email;
    @Size(min = 8, message = "Password must be between 8 and 20 characters")
    @NotBlank(message = "Password cannot be blank, empty or null")
    private String password;
    @NotBlank(message = "Role cannot be blank, empty or null")
    private String role;
}
