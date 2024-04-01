/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.controller;

import com.workaround.salesmanagement.DTO.ClientDTO;
import com.workaround.salesmanagement.DTO.ProductDTO;
import com.workaround.salesmanagement.DTO.ResponseDTO;
import com.workaround.salesmanagement.constants.Endpoints;
import com.workaround.salesmanagement.service.ClientManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Olawale
 */
@RestController
@RequiredArgsConstructor
public class ClientController {
    
    
    private final ClientManagementService clientService;
    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    @PostMapping(value = Endpoints.CREATE_PRODUCT, consumes = JSON, produces = JSON)
    public ResponseDTO createProduct(@RequestBody ClientDTO request) {
        return clientService.createProduct(request);
    }

    @GetMapping(value = Endpoints.FETCH_CLIENTS, consumes = JSON, produces = JSON)
    public ResponseDTO fetchAllProducts(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return clientService.fetchAllCustomers(page, size);
    }

    @PostMapping(value = Endpoints.UPDATE_PRODUCT, consumes = JSON, produces = JSON)
    public ResponseDTO createProduct(@PathVariable(name = "clientId", required = true) long clientId,
            @RequestBody ClientDTO request) {
        return clientService.updateClient(request, clientId);
    }
}
