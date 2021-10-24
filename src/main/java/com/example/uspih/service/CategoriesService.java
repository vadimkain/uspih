package com.example.uspih.service;

import com.example.uspih.domain.Bank;
import com.example.uspih.domain.CategoriesTransaction;
import com.example.uspih.domain.Transactions;
import com.example.uspih.domain.User;
import com.example.uspih.repos.BankRepo;
import com.example.uspih.repos.CategoriesRepo;
import com.example.uspih.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepo categoriesRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private TransactionsArchiveService transactionsArchiveService;

    public boolean addCategory(User user, String title_category) {
        CategoriesTransaction CategoryFromDb = categoriesRepo.findByOwnerAndTitle_category(user, title_category);

        if (CategoryFromDb != null) {
            return false;
        }

        CategoriesTransaction categoriesTransaction = new CategoriesTransaction();
        categoriesTransaction.setOwner(user);
        categoriesTransaction.setTitle_category(title_category);
        categoriesRepo.save(categoriesTransaction);

        return true;
    }

    public boolean DeleteCategory(User owner, Map<String, String> form) {

        List<CategoriesTransaction> category = categoriesRepo.findByOwner(owner);

        for (CategoriesTransaction keyCategory : category) {
            for (String keyForm : form.keySet()) {
                if (keyCategory.getTitle_category().contains(keyForm)) {
                    List<Transactions> transaction = transactionRepo.findByOwnerAndCategory(owner, keyCategory);
                    for (Transactions keyTransaction : transaction) {

                        transactionsArchiveService.archive(owner, keyTransaction);

                        Bank updateBankScore = bankRepo.findByOwnerAndTitle_bank(owner, keyTransaction.getBank());
                        updateBankScore.setScore(updateBankScore.getScore() - keyTransaction.getMoney());
                        bankRepo.save(updateBankScore);
                        transactionRepo.delete(keyTransaction);
                    }
                    categoriesRepo.delete(keyCategory);
                }
            }
        }

        return true;
    }

}
