package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.BankAdmin;
import com.example.service.AdminService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("addadmin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> addAdmin(@RequestBody BankAdmin bankAdmin) {
		try {
			adminService.addAdmin(bankAdmin);
			return ResponseEntity.ok("admin add successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to add admin: " + e.getMessage());

		}
	}

	@GetMapping("getadminbyid/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<BankAdmin> getAdminById(@PathVariable Long id) {
		try {
			BankAdmin bankAdmin = adminService.getAdminById(id);
			if (bankAdmin != null) {
				return ResponseEntity.ok(bankAdmin);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping("/updateadmin/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> updateAdmin(@PathVariable Long id, @RequestBody BankAdmin updatedAdmin) {
		try {
			adminService.updateAdmin(id, updatedAdmin);
			return ResponseEntity.ok("Admin updated successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to update admin: " + e.getMessage());
		}
	}

	@DeleteMapping("/deleteadmin/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
		try {
			adminService.deleteAdmin(id);
			return ResponseEntity.ok("Admin deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete admin: " + e.getMessage());
		}
	}

	@PatchMapping("/approvecustomer/{customerId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> approveCustomer(@PathVariable Long customerId) {
		try {
			adminService.approveCustomer(customerId);
			return ResponseEntity.ok("Customer approved successfully with ID: " + customerId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to approve customer: " + e.getMessage());
		}
	}

	

}
