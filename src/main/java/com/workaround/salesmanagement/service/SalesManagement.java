/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.service;

import com.workaround.salesmanagement.DTO.ResponseDTO;
import com.workaround.salesmanagement.DTO.SalesDTO;
import com.workaround.salesmanagement.DTO.TransactionSale;
import com.workaround.salesmanagement.model.Products;
import com.workaround.salesmanagement.model.Sales;
import com.workaround.salesmanagement.model.Transactions;
import com.workaround.salesmanagement.repository.ProductRepository;
import com.workaround.salesmanagement.repository.SalesRepository;
import com.workaround.salesmanagement.repository.TransactionRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olawale
 */
@Service
@RequiredArgsConstructor
public class SalesManagement {

    private final SalesRepository salesRepo;
    private final TransactionRepository transRepo;
    private final ProductRepository productRepo;

    public ResponseDTO createSalesTransaction(SalesDTO sales) {
        try {
            Transactions sold = new Transactions();
            //generate  a transactionreference
            RandomStringGenerator generator = new RandomStringGenerator.Builder()
                    .withinRange('a', 'z').build();
            String transRef = generator.generate(12);
            
           // initialize the total sale
           double totalSale = 0.00;
            for (TransactionSale trans : sales.getTransactions()) {
                // check that the product exist
                Optional<Products> productexist = productRepo.findByName(trans.getProduct());
                if (productexist.isPresent()) {

                    double quantityPrice = productexist.get().getPrice() * trans.getQuantity();
                    sold.setProductId(productexist.get().getId());
                    sold.setInitalPrice(productexist.get().getPrice());
                    sold.setPrice(productexist.get().getQuantity() == trans.getQuantity() //
                            ? productexist.get().getPrice() : quantityPrice);
                    sold.setQuantity(trans.getQuantity());
                    sold.setTransactionReference(transRef);
                    sold.setCreatedAt(LocalDateTime.now());
                    transRepo.save(sold);
                    // add each product transaction sale;
                    totalSale = totalSale + sold.getPrice();
                }
            }
            Sales newSale = new Sales();
            newSale.setClientName(sales.getClientName());
            newSale.setTransactionReference(transRef);
            newSale.setCreatedAt(LocalDateTime.now());
            newSale.setTotalSale(totalSale);
            
            salesRepo.save(newSale);
            return new ResponseDTO(HttpStatus.OK.getReasonPhrase(), "Total Sales " + totalSale ,
                    sold);
        }catch(Exception e){
             return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
                    null);
        }
    }
}
