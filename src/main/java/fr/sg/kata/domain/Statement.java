package fr.sg.kata.domain;

public class Statement implements Comparable<Statement> {

    private final Operation operation;
    private final Balance balance;

    public Statement(Operation operation, Balance balance) {
        this.operation = operation;
        this.balance = balance;
    }

    public Operation getOperation() {
        return operation;
    }

    public Balance getBalance() {
        return balance;
    }

    @Override
    public int compareTo(Statement o) {
        return this.getOperation().getDate().compareTo(o.getOperation().getDate());
    }
}
