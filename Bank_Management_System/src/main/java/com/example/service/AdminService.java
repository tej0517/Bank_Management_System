package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.BankAdmin;
import com.example.entity.Customer;
import com.example.repositories.BankAdminRepository;
import com.example.repositories.CustomerRepository;

@Service
public class AdminService {

	@Autowired
	private BankAdminRepository bankAdminRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public void addAdmin(BankAdmin bankAdmin) {
		try {
			bankAdminRepository.save(bankAdmin);
		} catch (Exception e) {
			// Log the exception and throw a custom exception to handle in the controller
			throw new RuntimeException("Error while saving admin: " + e.getMessage(), e);
		}
	}

	public BankAdmin getAdminById(Long id) {
		return bankAdminRepository.findById(id).orElseThrow(null);
	}

	public void updateAdmin(Long id, BankAdmin updatedAdmin) {
		BankAdmin existingAdmin = bankAdminRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
		existingAdmin.setName(updatedAdmin.getName());
		existingAdmin.setUsername(updatedAdmin.getUsername());
		existingAdmin.setPassword(updatedAdmin.getPassword());
		bankAdminRepository.save(existingAdmin);
	}

	public void deleteAdmin(Long id) {
		BankAdmin existingAdmin = bankAdminRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
		bankAdminRepository.delete(existingAdmin);
	}

	public void approveCustomer(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer with ID " + customerId + " not found"));

		if (customer.getIsApproved()) {
			throw new RuntimeException("Customer is already approved");
		}

		customer.setIsApproved(true);
		customerRepository.save(customer);
	}
	
	public List<Customer> getApprovedCustomers(Long adminId) {
        return customerRepository.findByBankAdminIdAndIsApprovedTrue(adminId);
    }

    public List<Customer> getNotApprovedCustomers(Long adminId) {
        return customerRepository.findByBankAdminIdAndIsApprovedFalse(adminId);
    }
}
