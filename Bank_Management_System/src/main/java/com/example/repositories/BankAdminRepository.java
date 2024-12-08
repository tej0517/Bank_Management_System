package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.BankAdmin;

public interface BankAdminRepository extends JpaRepository<BankAdmin, Long> {
}
