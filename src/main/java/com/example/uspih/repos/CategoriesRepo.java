package com.example.uspih.repos;

import com.example.uspih.domain.Bank;
import com.example.uspih.domain.CategoriesTransaction;
import com.example.uspih.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriesRepo extends JpaRepository<CategoriesTransaction, Long> {
    List<CategoriesTransaction> findByOwner(User owner);

    @Query("select c from CategoriesTransaction c where c.owner = ?1 and c.title_category = ?2")
    CategoriesTransaction findByOwnerAndTitle_category(User owner, String title_category);

    void delete(CategoriesTransaction categoriesTransaction);
}
