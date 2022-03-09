package fr.sg.kata.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test du client account")
public class AccountTest {

    @Test
    void shouldBalanceBeZeroWhenCreateAccountWithoutInitialAmount() {
        // Given
        final Account account = new Account();

        // Then
        assertEquals(new Balance(BigDecimal.ZERO), account.getBalance());
    }

}
