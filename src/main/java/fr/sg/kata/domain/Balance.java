package fr.sg.kata.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Balance {

    private final BigDecimal value;

    public Balance(BigDecimal value) {
        this.value = value;
    }

    public Balance add(Amount amount) {
        return new Balance(this.value.add(amount.getValue()));
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Objects.equals(value, balance.value);
    }

}
