package com.example.uspih.controller;

import com.example.uspih.domain.Bank;
import com.example.uspih.domain.CategoriesTransaction;
import com.example.uspih.domain.Transactions;
import com.example.uspih.domain.User;
import com.example.uspih.repos.BankRepo;
import com.example.uspih.repos.CategoriesRepo;
import com.example.uspih.repos.TransactionRepo;
import com.example.uspih.service.BankService;
import com.example.uspih.service.ValidateScoreCervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class MainController {

    @Autowired
    private BankService bankService;

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private ValidateScoreCervice validateScoreCervice;

    @Autowired
    private CategoriesRepo categoriesRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @PreAuthorize("hasAuthority('ACTIVATE')")
    @GetMapping("/main")
    public String MainPage(
            @AuthenticationPrincipal User user,
            HttpServletRequest request,
            Model model) {
        String parameter_newbank = request.getParameter("newbank");
        String parameter_deletebank = request.getParameter("deletebank");
        String parameter_newmoney = request.getParameter("newmoney");
        String parameter_newcategory = request.getParameter("newcategory");
        String parameter_deletecategory = request.getParameter("deletecategory");

        if (Objects.equals(parameter_newbank, "yes")) {
            model.addAttribute("ActivateNewBankForm", true);
        } else {
            model.addAttribute("ActivateNewBankForm", false);
        }

        if (Objects.equals(parameter_deletebank, "yes")) {
            model.addAttribute("ActivateDeleteBankForm", true);
        } else {
            model.addAttribute("ActivateDeleteBankForm", false);
        }

        if (Objects.equals(parameter_newmoney, "yes")) {
            model.addAttribute("ActivateNewMoneyForm", true);
        } else {
            model.addAttribute("ActivateNewMoneyForm", false);
        }

        if (Objects.equals(parameter_newcategory, "yes")) {
            model.addAttribute("ActivateNewCategoryForm", true);
        } else {
            model.addAttribute("ActivateNewCategoryForm", false);
        }

        if (Objects.equals(parameter_deletecategory, "yes")) {
            model.addAttribute("AcivateDeleteCategoryForm", true);
        } else {
            model.addAttribute("AcivateDeleteCategoryForm", false);
        }

        List<Bank> banks = bankRepo.findByOwner(user);
        List<CategoriesTransaction> categoriesTransactions = categoriesRepo.findByOwner(user);
        Iterable<Transactions> transactionsList = transactionRepo.findByOwner(user);

        model.addAttribute("banks", banks);
        model.addAttribute("categories", categoriesTransactions);
        model.addAttribute("TotalScore", bankService.TotalScore(user));
        model.addAttribute("transactionsList", transactionsList);

        return "main";
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }


}

