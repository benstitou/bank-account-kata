package fr.sg.kata.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test du Balance")
public class BalanceTest {

    @Test
    void shouldCreateANewBalanceWithValue() {
        // Given
        final BigDecimal value = BigDecimal.TEN;

        // When
        final Balance balance = new Balance(value);

        // Then
        assertEquals(BigDecimal.TEN, balance.getValue());
    }

    @Test
    void shouldBalanceBe20WhenAddingAmountOfTenToBalanceOf10() {
        // Given
        final BigDecimal value = BigDecimal.TEN;
        final Balance balanceOfTen = new Balance(value);
        final Amount amountOfTen = new Amount(BigDecimal.TEN);

        // When
        final Balance newBalanceOf20 = balanceOfTen.add(amountOfTen);

        // Then
        assertEquals(BigDecimal.valueOf(20), newBalanceOf20.getValue());
    }
}
