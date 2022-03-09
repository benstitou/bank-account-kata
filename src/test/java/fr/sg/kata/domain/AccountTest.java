package fr.sg.kata.domain;

import fr.sg.kata.exception.NegativeAmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test du client account")
public class AccountTest {

    private static final Amount AMOUNT_OF_TEN = new Amount(BigDecimal.TEN);
    private static final Amount AMOUNT_OF_100 = new Amount(BigDecimal.valueOf(100));

    private final ZoneId europeParisZoneId = ZoneId.of("Europe/Paris");
    private final Clock FIXED_CLOCK = Clock.fixed(
            LocalDate.of(2022, 3, 9).atStartOfDay(europeParisZoneId).toInstant(),
            europeParisZoneId
    );

    @Test
    void shouldBalanceBeZeroWhenCreateAccountWithoutInitialAmount() {
        // Given
        final Account account = new Account(FIXED_CLOCK);

        // Then
        assertEquals(new Balance(BigDecimal.ZERO), account.getBalance());
    }

    @Test
    void shouldBalanceBeTenWhenCreateAccountWithAmountOfTen() {
        // Given
        final Account account = new Account(AMOUNT_OF_TEN, FIXED_CLOCK);

        // Then
        assertEquals(new Balance(BigDecimal.TEN), account.getBalance());
    }

    @Test
    void shouldBalanceBeIncreaseBy100WhenDepositAmountOf100() {
        // Given
        final Account account = new Account(FIXED_CLOCK);

        // When
        account.deposit(AMOUNT_OF_100);

        // Then
        assertEquals(new Balance(BigDecimal.valueOf(100)), account.getBalance());
    }

    @Test
    void shouldThrowNegativeAmountExceptionWhenDepositNegativeAmount() {
        // Given
        final Account account = new Account(FIXED_CLOCK);

        // When
        assertThrows(NegativeAmountException.class,
                () -> account.deposit(new Amount(BigDecimal.valueOf(100).negate())),
                "Amount should be positive !");
    }

}
