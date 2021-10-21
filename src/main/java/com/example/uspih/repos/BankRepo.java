package com.example.uspih.repos;

import com.example.uspih.domain.Bank;
import com.example.uspih.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepo extends JpaRepository<Bank, Long> {
    Bank findByOwner(User owner);
}
