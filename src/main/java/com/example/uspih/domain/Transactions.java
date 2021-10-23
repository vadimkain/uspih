package com.example.uspih.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "title_category")
    private CategoriesTransaction category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "title_bank")
    private Bank bank;

    private double money;

    @Column(name = "local_date", columnDefinition = "DATE")
    private LocalDate localDate;

    public Transactions() {

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
        return category.getTitle_category();
    }

    public void setCategory(CategoriesTransaction category) {
        this.category = category;
    }

    public String getBank() {
        return bank.getTitle_bank();
    }

    public void setBank(Bank bank) {
        this.bank = bank;
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
