package com.example.uspih.repos;

import com.example.uspih.domain.Transactions;
import com.example.uspih.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transactions, Long> {
    List<Transactions> findByOwner(User owner);

    Transactions findByIdAndOwner(Long id, User owner);

    @Override
    void delete(Transactions transactions);
}
