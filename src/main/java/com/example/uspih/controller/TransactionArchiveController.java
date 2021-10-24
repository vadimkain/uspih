package com.example.uspih.controller;

import com.example.uspih.domain.TransactionsArchive;
import com.example.uspih.domain.User;
import com.example.uspih.repos.TransactionsArchiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAuthority('ACTIVATE')")
@RequestMapping("/transaction-archive")
public class TransactionArchiveController {

    @Autowired
    private TransactionsArchiveRepo transactionsArchiveRepo;

    @GetMapping
    public String ViewingArchivedTransactions(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        Iterable<TransactionsArchive> transactionsArchives = transactionsArchiveRepo.findByOwner(user);
        model.addAttribute("ArchivedTransactions", transactionsArchives);

        return "transaction-archive";
    }
}
