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

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private CategoriesRepo categoriesRepo;

    public boolean AddTransaction(User user, Map<String, String> form) {
        List<Bank> banks = bankRepo.findByOwner(user);
        List<CategoriesTransaction> categoriesTransactions = categoriesRepo.findByOwner(user);

        Transactions transactions = new Transactions();
        transactions.setOwner(user);

        for (CategoriesTransaction keyCategorie : categoriesTransactions) {
            if (keyCategorie.getTitle_category().contains(form.get("category"))) {
                transactions.setCategory(keyCategorie);
            }
        }

        for (Bank keyBank : banks) {
            if (keyBank.getTitle_bank().contains(form.get("bank"))) {
                transactions.setBank(keyBank);
            }
        }

        double doubleMoney = Double.parseDouble(form.get("money"));
        doubleMoney = (double) Math.round(doubleMoney * 100) / 100;
        transactions.setMoney(doubleMoney);

        LocalDate date = LocalDate.parse(form.get("date"));
        transactions.setLocalDate(date);

        transactionRepo.save(transactions);

        Bank bankUpdateScore = new Bank();
        bankUpdateScore = bankRepo.findByTitle_bank(form.get("bank"));
        bankUpdateScore.setScore(bankUpdateScore.getScore() + doubleMoney);
        bankRepo.save(bankUpdateScore);

        return true;
    }

    public boolean DeleteTransaction(User user, Long idTrans) {

        Transactions transaction = transactionRepo.findByIdAndOwner(idTrans, user);

        Bank updateBankScore = bankRepo.findByOwnerAndTitle_bank(user, transaction.getBank());
        updateBankScore.setScore(updateBankScore.getScore() - transaction.getMoney());
        bankRepo.save(updateBankScore);

        transactionRepo.delete(transaction);

        return true;
    }

}