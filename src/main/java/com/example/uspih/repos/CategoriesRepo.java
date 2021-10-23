package com.example.uspih.repos;

import com.example.uspih.domain.Bank;
import com.example.uspih.domain.CategoriesTransaction;
import com.example.uspih.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepo extends JpaRepository<CategoriesTransaction, Long> {
    List<CategoriesTransaction> findByOwner(User owner);

    void delete(CategoriesTransaction categoriesTransaction);
}
