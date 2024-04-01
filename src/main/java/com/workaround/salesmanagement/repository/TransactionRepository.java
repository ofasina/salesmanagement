/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.workaround.salesmanagement.repository;

import com.workaround.salesmanagement.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Olawale
 */
public interface TransactionRepository extends JpaRepository <Transactions, Long>{
    
    Transactions findByTransactionReference (String transactionReference);
    
}
