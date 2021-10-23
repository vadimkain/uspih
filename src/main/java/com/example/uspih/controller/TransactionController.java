package com.example.uspih.controller;

import com.example.uspih.domain.Bank;
import com.example.uspih.domain.CategoriesTransaction;
import com.example.uspih.domain.Transactions;
import com.example.uspih.domain.User;
import com.example.uspih.repos.BankRepo;
import com.example.uspih.repos.CategoriesRepo;
import com.example.uspih.repos.TransactionRepo;
import com.example.uspih.service.BankService;
import com.example.uspih.service.TransactionService;
import com.example.uspih.service.ValidateScoreCervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ACTIVATE')")
@RequestMapping("/main")
public class TransactionController {

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private ValidateScoreCervice validateScoreCervice;

    @Autowired
    private CategoriesRepo categoriesRepo;

    @Autowired
    private BankService bankService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepo transactionRepo;

    @PostMapping("/newmoney")
    public String NewMoney(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            Model model
    ) {
        Iterable<Bank> banks = bankRepo.findByOwner(user);
        List<CategoriesTransaction> categoriesTransactions = categoriesRepo.findByOwner(user);
        Iterable<Transactions> transactionsList = transactionRepo.findByOwner(user);

        model.addAttribute("ActivateNewBankForm", false);
        model.addAttribute("ActivateDeleteBankForm", false);
        model.addAttribute("ActivateNewMoneyForm", true);
        model.addAttribute("banks", banks);
        model.addAttribute("categories", categoriesTransactions);
        model.addAttribute("TotalScore", bankService.TotalScore(user));
        model.addAttribute("transactionsList", transactionsList);

        if (validateScoreCervice.ValidateScore(form.get("money"))) {
            transactionService.AddTransaction(user, form);
        } else {
            model.addAttribute("MoneyError", "Неправильно введені дані. (Приклад: -25000,50 або 125000)");
            return "main";
        }

        return "redirect:/main";
    }

    @PostMapping("/deletetransaction")
    public String DeleteTransaction(
            @AuthenticationPrincipal User user,
            @RequestParam("deletetransaction") Long idTrans,
            Model model) {

        transactionService.DeleteTransaction(user, idTrans);

        return "redirect:/main";
    }
}
