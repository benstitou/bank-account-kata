package fr.sg.kata.exception;

public class AccountCreditException extends RuntimeException {
    public AccountCreditException(String message) {
        super(message);
    }
}
