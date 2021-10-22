package com.example.uspih.service;

import com.example.uspih.domain.Bank;
import com.example.uspih.domain.User;
import com.example.uspih.repos.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    private BankRepo bankRepo;

    public boolean NewBank(User owner, String title_bank, double score) {
        Bank newbank = new Bank();

        newbank.setOwner(owner);
        newbank.setTitle_bank(title_bank);
        newbank.setScore(score);
        bankRepo.save(newbank);

        return true;
    }

}
