package com.example.uspih.service;

import com.example.uspih.domain.Transactions;
import com.example.uspih.domain.TransactionsArchive;
import com.example.uspih.domain.User;
import com.example.uspih.repos.TransactionRepo;
import com.example.uspih.repos.TransactionsArchiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionsArchiveService {

    @Autowired
    private TransactionsArchiveRepo transactionsArchiveRepo;

    public boolean archive(User owner, Transactions transactions) {
        TransactionsArchive transactionsArchive = new TransactionsArchive();

        transactionsArchive.setOwner(owner);
        transactionsArchive.setCategory(transactions.getCategory());
        transactionsArchive.setTitle_bank(transactions.getBank());
        transactionsArchive.setMoney(transactions.getMoney());
        transactionsArchive.setLocalDate(transactions.getLocalDate());

        transactionsArchiveRepo.save(transactionsArchive);

        return true;
    }

}
