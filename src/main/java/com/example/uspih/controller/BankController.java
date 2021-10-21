package com.example.uspih.controller;

import com.example.uspih.domain.Bank;
import com.example.uspih.domain.User;
import com.example.uspih.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@PreAuthorize("hasAuthority('ACTIVATE')")
@RequestMapping("/main")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping
    public String MainPage(HttpServletRequest request, Model model) {
        String parameter = request.getParameter("newbank");
        if (Objects.equals(parameter, "yes")) {
            model.addAttribute("ActivateNewBankForm", true);
        } else {
            model.addAttribute("ActivateNewBankForm", false);
        }
        return "main";
    }

    @PostMapping("/newbank")
    public String NewBank(
            @AuthenticationPrincipal User user,
            @RequestParam("title_bank") String title_bank,
            @RequestParam("score") Float score, // Это должны нормализовать
            Model model
    ) {
        Bank newbank = new Bank();

        newbank.setTitle_bank(title_bank);
        newbank.setScore(score);

        bankService.addBank(user, newbank);

        return "redirect:/main";
    }
}