/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Olawale
 */
@Data
public class ProductDTO {

    @NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be empty")
    private String name;
    private String description;
    @NotNull(message = "Catgory can not be null")
    @NotBlank(message = "Catgory can not be empty")
    private String category;
    @NotNull(message = "Quantity can not be null")
    @NotBlank(message = "Quantity can not be empty")
    private int quantity;
    @NotNull(message = "Price can not be null")
    @NotBlank(message = "Price can not be empty")
    private double price;
}
