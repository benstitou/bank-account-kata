package fr.sg.kata.domain;

import fr.sg.kata.exception.AccountCreditException;

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

    public void withdraw(final Amount amount) {
        checkAccountCredit(amount);
        this.balance = this.balance.subtract(amount);
        final Operation withdrawalOperation = new Operation(Operation.Type.WITHDRAWAL, LocalDate.now(this.clock), amount);
        this.operationHistory.addOperation(withdrawalOperation, this.balance);
    }

    private void checkAccountCredit(final Amount amount) {
        if (this.balance.getValue().compareTo(amount.getValue()) < 0) {
            throw new AccountCreditException("No enough money in this account to make this withdrawal !");
        }
    }

    public Balance getBalance() {
        return balance;
    }
}
