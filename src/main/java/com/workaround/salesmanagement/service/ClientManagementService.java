/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement.service;

import com.workaround.salesmanagement.DTO.ClientDTO;
import com.workaround.salesmanagement.DTO.ResponseDTO;
import com.workaround.salesmanagement.model.Clients;
import com.workaround.salesmanagement.repository.ClientRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olawale
 */
@Service
@RequiredArgsConstructor
public class ClientManagementService {

    private final ClientRepository clientRepository;

    public ResponseDTO createProduct(ClientDTO request) {
        try {
            // check that no clent exist with same email or phone number;
            Optional<Clients> existingClient
                    = clientRepository.findByEmailAndMobile(request.getEmail(), request.getMobileNumber());

            if (existingClient.isPresent()) {
                return new ResponseDTO(HttpStatus.UNPROCESSABLE_ENTITY.toString(), "Existing user with email address or mobile", null);

            }
            Clients newClient = new Clients();
            newClient.setAddress(request.getAddress());
            newClient.setEmail(request.getEmail());
            newClient.setFirstName(request.getFirstName());
            newClient.setLastName(request.getLastName());
            newClient.setMobile(request.getMobileNumber());
            clientRepository.save(newClient);
            return new ResponseDTO(HttpStatus.CREATED.toString(), "Created", newClient);

        } catch (Exception e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
                    null);
        }
    }
    
     public ResponseDTO fetchAllCustomers(int page, int size) {
        try {
            Pageable pagingSort = PageRequest.of(page, size);
            Page<Clients> list = clientRepository.findAll(pagingSort);
            if (list == null) {
                list = Page.empty(pagingSort);
                return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "No record Found", null);

            }
              return new ResponseDTO(HttpStatus.OK.toString(), "Client List", list);

        } catch (Exception e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
                    null);
        }
    }
     
      public ResponseDTO updateClient(ClientDTO request, long clientId) {

        try {
            Optional<Clients> client = clientRepository.findById(clientId);
            if (client.isEmpty()) {
                return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "No client found", null);
            }

            client.get().setAddress(request.getAddress());
            client.get().setEmail(request.getEmail());
            client.get().setFirstName(request.getFirstName());
            client.get().setLastName(request.getLastName());
            client.get().setMobile(request.getMobileNumber());
            return new ResponseDTO(HttpStatus.OK.toString(), "Update successful", client.get());
        } catch (Exception e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
                    null);
        }
    }

    public ResponseDTO deleteClient(long clientId) {
        try {
            Optional<Clients> client = clientRepository.findById(clientId);
            if (client.isEmpty()) {
                return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "No Client record found", null);
            }
            clientRepository.deleteById(clientId);
            return new ResponseDTO(HttpStatus.OK.toString(), "Client record deleted", null);

        } catch (Exception e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
                    null);
        }

    }
}
