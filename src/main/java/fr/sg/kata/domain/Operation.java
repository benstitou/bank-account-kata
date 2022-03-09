package fr.sg.kata.domain;

import java.time.LocalDate;

public class Operation {

    public enum Type {
        DEPOSIT
    }

    private final Type type;
    private final LocalDate date;
    private final Amount amount;

    public Operation(Type type, LocalDate date, Amount amount) {
        this.type = type;
        this.date = date;
        this.amount = amount;
    }
}
