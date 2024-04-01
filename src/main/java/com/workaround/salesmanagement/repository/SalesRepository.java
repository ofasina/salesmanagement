/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.workaround.salesmanagement.repository;

import com.workaround.salesmanagement.model.Sales;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Olawale
 */
public interface SalesRepository extends JpaRepository <Sales, Long>{
    
    Optional<Sales> findByCreatedBy (String name);
    Optional<Sales> findByClientName (String clientName);
    Optional<Sales> findByTransactionReference (String transRef);
}
