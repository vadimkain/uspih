package com.example.uspih.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transaction_archive")
public class TransactionsArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    private String category;

    private String title_bank;

    private double money;

    private LocalDate localDate;

    public TransactionsArchive() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle_bank() {
        return title_bank;
    }

    public void setTitle_bank(String title_bank) {
        this.title_bank = title_bank;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}