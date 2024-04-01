/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.workaround.salesmanagement.repository;

import com.workaround.salesmanagement.model.Clients;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Olawale
 */
public interface ClientRepository extends JpaRepository <Clients, Long>{
    
    Optional<Clients> findByEmail(String email);
    
    Optional<Clients> findByMobile(String mobile);
    
    Optional<Clients> findByEmailAndMobile(String email, String mobile);
    
}
