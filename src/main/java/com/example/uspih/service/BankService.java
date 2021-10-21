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

    public boolean addBank(User owner, Bank bank) {
        Bank OwnerNewBank = bankRepo.findByOwner(owner);

        System.out.println(OwnerNewBank);

        return true;
    }

}
