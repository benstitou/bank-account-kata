package fr.sg.kata.domain;

import fr.sg.kata.exception.NegativeAmountException;

import java.math.BigDecimal;

public class Amount {

    private final BigDecimal value;

    public Amount(BigDecimal value) {
        if (value == null || value.signum() == -1) {
            throw new NegativeAmountException("Amount should be positive !");
        }
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
