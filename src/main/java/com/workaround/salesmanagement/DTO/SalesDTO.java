/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.DTO;

import java.util.List;
import lombok.Data;

/**
 *
 * @author Olawale
 */
@Data
public class SalesDTO {
    private List<TransactionSale> transactions;
    private String clientName;
}
