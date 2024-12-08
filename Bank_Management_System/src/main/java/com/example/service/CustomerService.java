package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Customer;
import com.example.repositories.CustomerRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CustomerService {
	
	@Autowired
    private CustomerRepository customerRepository;
	
	public void addCustomer(Long adminId, CustomerRequestDTO customerRequestDTO, MultipartFile file) throws IOException {
	    String staticFolderPath;
	    try {
	        staticFolderPath = new ClassPathResource("static/test1").getFile().getAbsolutePath();
	    } catch (IOException e) {
	        staticFolderPath = Paths.get("src/main/resources/static/test1").toAbsolutePath().toString();
	    }

	    File staticFolder = new File(staticFolderPath);
	    if (!staticFolder.exists()) {
	        staticFolder.mkdirs(); 
	    }

	    if (file.isEmpty()) {
	        throw new IOException("Uploaded file is empty.");
	    }

	    String originalFileName = file.getOriginalFilename();
	    String filePath = staticFolderPath + File.separator + originalFileName;
	    Path path = Paths.get(filePath);

	    try {
	        Files.write(path, file.getBytes());
	    } catch (IOException e) {
	        throw new IOException("Failed to save the file: " + e.getMessage());
	    }

	    Customer customer = new Customer(
	            customerRequestDTO.getName(),
	            customerRequestDTO.getEmail(),
	            customerRequestDTO.getAddress(),
	            customerRequestDTO.getPhone(),
	            customerRequestDTO.getDate(),
	            customerRequestDTO.getType(),
	            originalFileName,
	            "/test1/" + originalFileName,
	            null,
	            0.0,
	            false,
	            null
	    );
	    customerRepository.save(customer);
	}

	public String deposit(Long customerId, Double amount) throws Exception {
		
		if (amount <= 0) {
            throw new Exception("Amount must be greater than zero");
        }
		
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new Exception("Customer not found"));
        
        if (!customer.getIsApproved()) {
            throw new Exception("Customer is not approved by the bank.");
        }
        

        customer.setBalance(customer.getBalance() - amount);
        customerRepository.save(customer);
        
        return "Deposit successful, new balance: " + customer.getBalance();
    }

	public String credit(Long customerId, Double amount) throws Exception {
	    Customer customer = customerRepository.findById(customerId)
	            .orElseThrow(() -> new Exception("Customer not found"));

	    if (!customer.getIsApproved()) {
	        throw new Exception("Customer is not approved by the bank.");
	    }

	    if (amount <= 0) {
	        throw new Exception("Credit amount must be greater than 0.");
	    }

	    if (customer.getBalance() < amount) {
	        throw new Exception("Insufficient balance for credit.");
	    }

	    // Update customer balance
	    customer.setBalance(customer.getBalance() + amount); 
	    customerRepository.save(customer);

	    return "Credit successful, new balance: " + customer.getBalance();
	}

    public Double getBalance(Long customerId) throws Exception {
        // Fetch customer by ID
        Customer customer = customerRepository.findById(customerId)
               .orElseThrow(() -> new Exception("Customer not found"));

        if (!customer.getIsApproved()) {
            throw new Exception("Customer is not approved by the bank.");
        }

        // Return the balance
        return customer.getBalance();
    }
}
