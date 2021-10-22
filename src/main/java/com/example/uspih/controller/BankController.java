package com.example.uspih.controller;

import com.example.uspih.domain.Bank;
import com.example.uspih.domain.User;
import com.example.uspih.repos.BankRepo;
import com.example.uspih.service.BankService;
import com.example.uspih.service.ValidateScoreCervice;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@PreAuthorize("hasAuthority('ACTIVATE')")
@RequestMapping("/main")
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private ValidateScoreCervice validateScoreCervice;

    @GetMapping
    public String MainPage(
            @AuthenticationPrincipal User user,
            HttpServletRequest request,
            Model model) {
        String parameter_newbank = request.getParameter("newbank");
        String parameter_deletebank = request.getParameter("deletebank");

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

        List<Bank> banks = bankRepo.findByOwner(user);

        model.addAttribute("banks", banks);
        model.addAttribute("TotalScore", bankService.TotalScore(user));

        return "main";
    }

    @PostMapping("/newbank")
    public String NewBank(
            @AuthenticationPrincipal User user,
            @RequestParam("title_bank") String title_bank,
            @RequestParam("score") String score, // Это должны нормализовать
            Model model
    ) {
        Iterable<Bank> banks = bankRepo.findByOwner(user);

        model.addAttribute("ActivateNewBankForm", true);
        model.addAttribute("ActivateDeleteBankForm", false);
        model.addAttribute("banks", banks);
        model.addAttribute("TotalScore", bankService.TotalScore(user));

        if (title_bank.isEmpty()) {
            model.addAttribute("TitleBankError", "Це поле не може бути порожнім");
            if (score.isEmpty()) {
                model.addAttribute("ScoreBankError", "Це поле не може бути порожнім");
                return "main";
            }
            return "main";
        } else if (score.isEmpty()) {
            model.addAttribute("ScoreBankError", "Це поле не може бути порожнім");
            return "main";
        } else {
            if (validateScoreCervice.ValidateScore(score)) {
                double doubleScore = Double.parseDouble(score);
                doubleScore = (double) Math.round(doubleScore * 100) / 100;
                bankService.NewBank(user, title_bank, doubleScore);
            } else {
                model.addAttribute("ScoreBankError", "Неправильно введені дані. (Приклад: -25000,50 або 125000)");
                return "main";
            }
        }

        return "redirect:/main";
    }

    @PostMapping("/deletebank")
    public String DeleteBank(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            Model model
    ) {
        bankService.DeleteBank(user, form);

        return "redirect:/main";
    }
}