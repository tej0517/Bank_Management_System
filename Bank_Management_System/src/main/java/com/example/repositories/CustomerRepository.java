package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>  {

	List<Customer> findByBankAdminIdAndIsApprovedTrue(Long bankAdminId);

    // Fetch not-approved customers for a specific BankAdmin
    List<Customer> findByBankAdminIdAndIsApprovedFalse(Long bankAdminId);
}
