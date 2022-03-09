package fr.sg.kata.domain;

import java.math.BigDecimal;

public class Account {

    private Balance balance;

    public Account() {
        this.balance = new Balance(BigDecimal.ZERO);
    }

    public Balance getBalance() {
        return balance;
    }
}
