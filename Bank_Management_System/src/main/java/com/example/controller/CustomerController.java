package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.CustomerRequestDTO;
import com.example.service.CustomerService;
import java.io.IOException;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/addcustomer/{adminId}")
    public ResponseEntity<String> uploadImageWithData(
            @PathVariable Long adminId,
            @RequestParam("file") MultipartFile file,
            @ModelAttribute CustomerRequestDTO customerRequestDTO) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is required.");
            }

            // Call the service method to handle the upload
            customerService.addCustomer(adminId, customerRequestDTO, file);

            return ResponseEntity.ok("File and customer data uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File upload failed: " + e.getMessage());
        }
    }
    
    
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/deposit/{customerId}")
    public ResponseEntity<String> deposit(@PathVariable Long customerId, @RequestBody CustomerRequestDTO customerRequestDTO) {
        try {
        	Double amount = customerRequestDTO.getAmount();
        	String message = customerService.deposit(customerId, amount);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/credit/{customerId}")
    public ResponseEntity<String> credit(@PathVariable Long customerId,  @RequestBody CustomerRequestDTO customerRequestDTO) {
        try {
        	Double amount = customerRequestDTO.getAmount();
            String message = customerService.credit(customerId, amount);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/balance/{customerId}")
    public ResponseEntity<?> getBalance(@PathVariable Long customerId) {
        try {
            // Call the service method to get the balance
            Double balance = customerService.getBalance(customerId);
            return ResponseEntity.ok(balance); // Return the balance with a 200 status code
        } catch (Exception e) {
            // Return the error message with a 400 status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
