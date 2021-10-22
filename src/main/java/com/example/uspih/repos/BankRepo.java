package com.example.uspih.repos;

import com.example.uspih.domain.Bank;
import com.example.uspih.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepo extends JpaRepository<Bank, Long> {
    List<Bank> findByOwner(User owner);
}
