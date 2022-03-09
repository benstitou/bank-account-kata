package fr.sg.kata.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test du client account")
public class AccountTest {

    private static final Amount AMOUNT_OF_TEN = new Amount(BigDecimal.TEN);

    @Test
    void shouldBalanceBeZeroWhenCreateAccountWithoutInitialAmount() {
        // Given
        final Account account = new Account();

        // Then
        assertEquals(new Balance(BigDecimal.ZERO), account.getBalance());
    }

    @Test
    void shouldBalanceBeTenWhenCreateAccountWithAmountOfTen() {
        // Given
        final Account account = new Account(AMOUNT_OF_TEN);

        // Then
        assertEquals(new Balance(BigDecimal.TEN), account.getBalance());
    }

}
