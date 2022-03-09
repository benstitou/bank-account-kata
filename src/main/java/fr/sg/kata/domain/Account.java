package fr.sg.kata.domain;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;

public class Account {

    private final OperationHistory operationHistory;
    private final Clock clock;
    private Balance balance;

    public Account(Clock clock) {
        this.clock = clock;
        this.operationHistory = new OperationHistory();
        this.balance = new Balance(BigDecimal.ZERO);
    }

    public Account(Amount amount, Clock clock) {
        this.clock = clock;
        this.operationHistory = new OperationHistory();
        this.balance = new Balance(amount.getValue());
        final Operation operation = new Operation(Operation.Type.DEPOSIT, LocalDate.now(this.clock), amount);
        this.operationHistory.addOperation(operation, this.balance);
    }

    public void deposit(final Amount amount) {
        this.balance = this.balance.add(amount);
        final Operation depositOperation = new Operation(Operation.Type.DEPOSIT, LocalDate.now(this.clock), amount);
        this.operationHistory.addOperation(depositOperation, this.balance);
    }

    public Balance getBalance() {
        return balance;
    }
}
