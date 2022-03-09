package fr.sg.kata.domain;

public class Statement {

    private final Operation operation;
    private final Balance balance;

    public Statement(Operation operation, Balance balance) {
        this.operation = operation;
        this.balance = balance;
    }

}
