package fr.sg.kata.domain;

import fr.sg.kata.exception.NegativeAmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test du Amount")
public class AmountTest {

    @Test
    void shouldCreateANewAmountWithValue() {
        // Given
        final BigDecimal value = BigDecimal.TEN;

        // When
        final Amount amount = new Amount(value);

        // Then
        assertEquals(BigDecimal.TEN, amount.getValue());
    }

    @Test
    void shouldThrowNegativeAmountExceptionWhenCreateNewAmountWithNegativeValue() {
        // Given
        final BigDecimal value = BigDecimal.TEN.negate();

        // Then
        assertThrows(NegativeAmountException.class, () ->
                        new Amount(value),
                "Amount should be positive !"
        );
    }

}

