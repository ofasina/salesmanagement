/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.service;

import com.workaround.salesmanagement.DTO.ProductCategoryDTO;
import com.workaround.salesmanagement.DTO.ProductDTO;
import com.workaround.salesmanagement.DTO.ResponseDTO;
import com.workaround.salesmanagement.model.ProductCategory;
import com.workaround.salesmanagement.model.Products;
import com.workaround.salesmanagement.repository.ProductCategoryRepository;
import com.workaround.salesmanagement.repository.ProductRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olawale
 */
@Service
@RequiredArgsConstructor
public class ProductManagementService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepo;

    public ResponseDTO createProduct(ProductDTO request, Authentication auth) {
        try {
            Products newProduct = new Products();
            newProduct.setCategory(request.getCategory());
            newProduct.setCreatedAt(LocalDateTime.now());
            newProduct.setCreatedBy("");
            newProduct.setDescription(request.getDescription());
            newProduct.setName(request.getName());
            newProduct.setPrice(request.getPrice());
            newProduct.setQuantity(request.getQuantity());
            newProduct.setCreatedBy(auth.getName());
            productRepository.save(newProduct);
            return new ResponseDTO(HttpStatus.CREATED.toString(), "Created", newProduct);

        } catch (Exception e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
                    null);
        }
    }

    public ResponseDTO fetchProducts(int page, int size) {
        try {
            Pageable pagingSort = PageRequest.of(page, size);
            Page<Products> productList = productRepository.findAll(pagingSort);
            if (productList == null) {
                productList = Page.empty(pagingSort);
                return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "No record Found", null);

            }
            return new ResponseDTO(HttpStatus.OK.toString(), "Product List", productList);

        } catch (Exception e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
                    null);
        }
    }

    public ResponseDTO viewProducts(String name, String description, String catgeory, LocalDate createdDate) {

        try {
            if (createdDate != null) {
                //check if record exist for the date
                List<Products> productsByDate = productRepository.findByCreatedAt(createdDate);
                return new ResponseDTO(HttpStatus.OK.toString(), "Ok",
                        productsByDate.isEmpty() ? "No record for selected date found" : productsByDate);
            }
            if (name != null) {
                Optional<Products> products = productRepository.findByName(name);
                return new ResponseDTO(HttpStatus.OK.toString(), "Ok",
                        products.isEmpty() ? "No record found" : products.get());
            }
            if (description != null) {
                Optional<Products> products = productRepository.findByDescription(description);
                return new ResponseDTO(HttpStatus.OK.toString(), "Ok",
                        products.isEmpty() ? "No record found" : products.get());
            }
            if (catgeory != null) {
                Optional<Products> products = productRepository.findByCategory(catgeory);
                return new ResponseDTO(HttpStatus.OK.toString(), "Ok",
                        products.isEmpty() ? "No record found" : products.get());
            }
            return new ResponseDTO(HttpStatus.BAD_REQUEST.toString(), "Bad Request", null);
        } catch (Exception e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
                    null);
        }
    }

    public ResponseDTO updateProduct(ProductDTO request, long productId) {

        try {
            Optional<Products> product = productRepository.findById(productId);
            if (product.isEmpty()) {
                return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "No Product found", null);
            }

            product.get().setCategory(request.getCategory());
            product.get().setCreatedAt(LocalDateTime.now());
            product.get().setCreatedBy("");
            product.get().setDescription(request.getDescription());
            product.get().setName(request.getName());
            product.get().setPrice(request.getPrice());
            product.get().setQuantity(request.getQuantity());
            productRepository.save(product.get());
            return new ResponseDTO(HttpStatus.OK.toString(), "Update successful", product.get());
        } catch (Exception e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
                    null);
        }
    }

    public ResponseDTO deleteProduct(long productId) {
        try {
            Optional<Products> product = productRepository.findById(productId);
            if (product.isEmpty()) {
                return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "No Product found", null);
            }
            productRepository.deleteById(productId);
            return new ResponseDTO(HttpStatus.OK.toString(), "Product deleted", null);

        } catch (Exception e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
                    null);
        }
    }

    public ResponseDTO createProductCategory(ProductCategoryDTO request) {
        try {
            ProductCategory category = new ProductCategory();
            BeanUtils.copyProperties(request, category);
            category.setCreatedAt(LocalDateTime.now());
            productCategoryRepo.save(category);
            return new ResponseDTO(HttpStatus.CREATED.toString(), "Created", category);

        } catch (Exception e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
                    null);
        }
    }
    
    public ResponseDTO viewProductById(long productId) {
        try {
            Optional<Products> product = productRepository.findById(productId);
            if (product.isEmpty()) {
                return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "No Product found", null);
            }
            return new ResponseDTO(HttpStatus.OK.toString(), "Ok", product.get());

        } catch (Exception e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
                    null);
        }
    }
}
