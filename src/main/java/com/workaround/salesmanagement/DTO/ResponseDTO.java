/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.DTO;

import lombok.Data;

/**
 *
 * @author Olawale
 */
@Data
public class ResponseDTO {

    private String responseCode;
    private String description;
    private Object data;

    public ResponseDTO(String responseCode, String description, Object data) {
        this.responseCode = responseCode;
        this.description = description;
        this.data = data;

    }
}
