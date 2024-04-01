/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author Olawale
 */
@Data
public class ClientDTO {
    
    @NotBlank(message = "First Name cannot be blank, empty or null")
    private String firstName;
    @NotBlank(message = "Last Name be blank, empty or null")
    private String lastName;
    @NotBlank(message = "MobileNumber be blank, empty or null")
    private String mobileNumber;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank, empty or null")
    private String email;
    @NotBlank(message = "Address cannot be blank, empty or null")
    private String address;
}
