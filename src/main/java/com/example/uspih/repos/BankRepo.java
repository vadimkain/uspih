package com.example.uspih.repos;

import com.example.uspih.domain.Bank;
import com.example.uspih.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BankRepo extends JpaRepository<Bank, Long> {
    @Query("select b from Bank b where b.title_bank = ?1")
    Bank findByTitle_bank(String title);

    @Query("select b from Bank b where b.owner = ?1 and b.title_bank = ?2")
    Bank findByOwnerAndTitle_bank(User owner, String title);

    List<Bank> findByOwner(User owner);

    void delete(Bank bank);
}
