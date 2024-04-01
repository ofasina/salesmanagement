/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.workaround.salesmanagement.repository;

import com.workaround.salesmanagement.model.UserRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Olawale
 */
public interface UserRoleRepository extends JpaRepository <UserRole, Long>{
    
    Optional<UserRole> findByRole(String role);
    
}
