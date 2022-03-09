package fr.sg.kata.domain;

import java.time.LocalDate;

public class Operation {

    public enum Type {
        DEPOSIT, WITHDRAWAL
    }

    private final Type type;
    private final LocalDate date;
    private final Amount amount;

    public Operation(Type type, LocalDate date, Amount amount) {
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public Type getType() {
        return type;
    }

    public Amount getAmount() {
        return amount;
    }
}
