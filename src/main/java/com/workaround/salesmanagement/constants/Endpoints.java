/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.constants;

/**
 *
 * @author Olawale
 */
public class Endpoints {
    
    public static final String VALIDATE_USER = "/user-auth";
    
     public static final String CREATE_CLIENT = "/createClient";
     public static final String UPDATE_CLIENT = "/updateClient/{clientid}";
     public static final String DELETE_CLIENT = "/deleteClient/{clientid}";
     public static final String VIEW_CLIENT_EMAIL = "/viewClient/{email}";
     public static final String VIEW_CLIENT_ADDRESS = "/viewClient/{address}";
     public static final String VIEW_CLIENT_ID = "/viewClient/{clientid}";
     public static final String FETCH_CLIENTS = "/fetchClient";
     
     public static final String CREATE_PRODUCT = "/createProduct";
     public static final String CREATE_PRODUCT_CATEGORY = "/createProduct-category";
     public static final String UPDATE_PRODUCT = "updateProduct/{productId}";
     public static final String DELETE_PRODUCT = "/deleteProduct/{productId}";
     public static final String VIEW_PRODUCT = "/viewProduct";
     public static final String VIEW_PRODUCT_ID = "/viewProduct/{productId}";
     
     public static final String FETCH_PRODUCT = "/fetchProduct";
     
     public static final String CREATE_USER = "/user_management/create";
     public static final String CREATE_ROLE = "/roleCreation";
     
     public static final String CREATE_SALE = "/createSale";
     public static final String FETCH_SALE = "/fetchSale";
}
