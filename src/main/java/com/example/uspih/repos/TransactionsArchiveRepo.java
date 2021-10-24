package com.example.uspih.repos;

import com.example.uspih.domain.TransactionsArchive;
import com.example.uspih.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsArchiveRepo extends JpaRepository<TransactionsArchive, Long> {
    List<TransactionsArchive> findByOwner(User owner);
}
