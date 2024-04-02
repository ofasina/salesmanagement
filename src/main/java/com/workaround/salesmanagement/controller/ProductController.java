/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.controller;

import com.workaround.salesmanagement.DTO.ProductCategoryDTO;
import com.workaround.salesmanagement.DTO.ProductDTO;
import com.workaround.salesmanagement.DTO.ResponseDTO;
import com.workaround.salesmanagement.constants.Endpoints;
import com.workaround.salesmanagement.service.ProductManagementService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
public class ProductController {

    private final ProductManagementService productService;
    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    @PostMapping(value = Endpoints.CREATE_PRODUCT, consumes = JSON, produces = JSON)
    @PreAuthorize("hasAnyRole('Admin Role')")
    public ResponseDTO createProduct(@RequestBody ProductDTO request, Authentication auth) {
        return productService.createProduct(request, auth);
    }
    
     @PostMapping(value = Endpoints.CREATE_PRODUCT, consumes = JSON, produces = JSON)
    @PreAuthorize("hasAnyRole('Admin Role')")
    public ResponseDTO createProductCategory(@RequestBody ProductCategoryDTO request, Authentication auth) {
        return productService.createProductCategory(request);
    }

    @GetMapping(value = Endpoints.FETCH_PRODUCT, consumes = JSON, produces = JSON)
    public ResponseDTO fetchAllProducts(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return productService.fetchProducts(page, size);
    }

    @PostMapping(value = Endpoints.UPDATE_PRODUCT, consumes = JSON, produces = JSON)
    public ResponseDTO updateProduct(@PathVariable(name = "productId", required = true) long productId,
            @RequestBody ProductDTO request) {
        return productService.updateProduct(request, productId);
    }
    
    @GetMapping(value = Endpoints.VIEW_PRODUCT, consumes = JSON, produces = JSON)
    public ResponseDTO viewProducts(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDate,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "category", required = false) String category) {
        return productService.viewProducts(name, description, category, createdDate);
    }
    
     @GetMapping(value = Endpoints.VIEW_PRODUCT_ID, consumes = JSON, produces = JSON)
    public ResponseDTO fetchAllProducts(@PathVariable(name = "productId", required = true) long productId) {
        return productService.viewProductById(productId);
    }
}
