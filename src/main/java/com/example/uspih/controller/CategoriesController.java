package com.example.uspih.controller;

import com.example.uspih.domain.Bank;
import com.example.uspih.domain.Transactions;
import com.example.uspih.domain.User;
import com.example.uspih.repos.BankRepo;
import com.example.uspih.repos.CategoriesRepo;
import com.example.uspih.repos.TransactionRepo;
import com.example.uspih.service.BankService;
import com.example.uspih.service.CategoriesService;
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

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ACTIVATE')")
@RequestMapping("/main")
public class CategoriesController {

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

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping("/newcategory")
    public String NewCategory(
            @AuthenticationPrincipal User user,
            @RequestParam("title_category") String title_category,
            Model model) {

        Iterable<Bank> banks = bankRepo.findByOwner(user);
        Iterable<Transactions> transactionsList = transactionRepo.findByOwner(user);

        model.addAttribute("ActivateNewBankForm", false);
        model.addAttribute("ActivateDeleteBankForm", false);
        model.addAttribute("ActivateNewMoneyForm", false);
        model.addAttribute("ActivateNewCategoryForm", true);
        model.addAttribute("AcivateDeleteCategoryForm", false);
        model.addAttribute("banks", banks);
        model.addAttribute("TotalScore", bankService.TotalScore(user));
        model.addAttribute("transactionsList", transactionsList);

        if (!categoriesService.addCategory(user, title_category)) {
            model.addAttribute("TitleCategoryError", "Така категорія вже існує");
            return "main";
        }
        return "redirect:/main";
    }

    @PostMapping("/deletecategory")
    public String DeleteCategory(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            Model model
    ) {

        categoriesService.DeleteCategory(user, form);

        return "redirect:/main";
    }
}
