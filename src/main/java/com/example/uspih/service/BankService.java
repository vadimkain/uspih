package com.example.uspih.service;

import com.example.uspih.domain.Bank;
import com.example.uspih.domain.User;
import com.example.uspih.repos.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BankService {

    @Autowired
    private BankRepo bankRepo;

    public double TotalScore(User user) {
        double totalscore = 0;

        List<Bank> banks = bankRepo.findByOwner(user);

        for (Bank keybank : banks) {
            totalscore += keybank.getScore();
        }

        return totalscore;
    }

    public boolean NewBank(User owner, String title_bank, double score) {
        Bank newbank = new Bank();

        newbank.setOwner(owner);
        newbank.setTitle_bank(title_bank);
        newbank.setScore(score);
        bankRepo.save(newbank);

        return true;
    }

    public boolean DeleteBank(User owner, Map<String, String> form) {
        List<Bank> banks = bankRepo.findByOwner(owner);

        for (Bank keyBank : banks) {
            for (String keyForm : form.keySet()) {
                if (keyBank.getTitle_bank().contains(keyForm)) {
                    bankRepo.delete(keyBank);
                }
            }
        }
        return true;
    }

}
