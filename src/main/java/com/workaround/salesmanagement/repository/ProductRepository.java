/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.workaround.salesmanagement.repository;

import com.workaround.salesmanagement.model.Products;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Olawale
 */
public interface ProductRepository extends JpaRepository <Products, Long>{
    
    Optional<Products> findByName (String name);
    
    Optional<Products> findByCategory (String category);
    
    Optional<Products> findByDescription (String description);
    
    List<Products> findByCreatedDate (LocalDate date);
}
