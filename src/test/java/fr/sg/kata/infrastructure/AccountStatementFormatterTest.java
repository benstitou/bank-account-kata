package fr.sg.kata.infrastructure;

import fr.sg.kata.domain.Amount;
import fr.sg.kata.domain.Balance;
import fr.sg.kata.domain.Operation;
import fr.sg.kata.domain.OperationHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test du formatter")
public class AccountStatementFormatterTest {

    private final AccountStatementFormatter accountStatementFormatter = new AccountStatementFormatter();

    private final ZoneId europeParisZoneId = ZoneId.of("Europe/Paris");

    private final Clock FIXED_CLOCK_1 = Clock.fixed(
            LocalDate.of(2022, 3, 5).atStartOfDay(europeParisZoneId).toInstant(),
            europeParisZoneId
    );

    private final Clock FIXED_CLOCK_2 = Clock.fixed(
            LocalDate.of(2022, 3, 9).atStartOfDay(europeParisZoneId).toInstant(),
            europeParisZoneId
    );

    @Test
    public void shouldReturnEmptyTabGivenEmptyOperationList() {

        // Given
        final String expectedHeader = "      Date |       Operation |          Amount |         Balance" + System.lineSeparator();

        // When
        final String formattedHeader = accountStatementFormatter.format(new OperationHistory());

        // Then
        assertEquals(expectedHeader, formattedHeader);
    }

    @Test
    @DisplayName("Doit formatter une liste des operations")
    void shouldFormatOperationsList() {
        // Given
        final OperationHistory operationHistory = new OperationHistory();
        operationHistory.addOperation(new Operation(Operation.Type.DEPOSIT, LocalDate.now(FIXED_CLOCK_1), new Amount(BigDecimal.valueOf(100))), new Balance(BigDecimal.valueOf(100)));
        operationHistory.addOperation(new Operation(Operation.Type.DEPOSIT, LocalDate.now(FIXED_CLOCK_1), new Amount(BigDecimal.valueOf(1000L))), new Balance(BigDecimal.valueOf(1100L)));
        operationHistory.addOperation(new Operation(Operation.Type.WITHDRAWAL, LocalDate.now(FIXED_CLOCK_1), new Amount(BigDecimal.valueOf(250L))), new Balance(BigDecimal.valueOf(850L)));
        operationHistory.addOperation(new Operation(Operation.Type.DEPOSIT, LocalDate.now(FIXED_CLOCK_2), new Amount(BigDecimal.valueOf(350.5))), new Balance(BigDecimal.valueOf(1200.5)));
        operationHistory.addOperation(new Operation(Operation.Type.WITHDRAWAL, LocalDate.now(FIXED_CLOCK_2), new Amount(BigDecimal.valueOf(700L))), new Balance(BigDecimal.valueOf(500.5)));

        final String expectedOutput =
                "      Date |       Operation |          Amount |         Balance" + System.lineSeparator() +
                        "09/03/2022 |      Withdrawal |         -700.00 |          500.50" + System.lineSeparator() +
                        "09/03/2022 |         Deposit |          350.50 |         1200.50" + System.lineSeparator() +
                        "05/03/2022 |      Withdrawal |         -250.00 |          850.00" + System.lineSeparator() +
                        "05/03/2022 |         Deposit |         1000.00 |         1100.00" + System.lineSeparator() +
                        "05/03/2022 |         Deposit |          100.00 |          100.00";

        // When
        final String formattedOperations = accountStatementFormatter.format(operationHistory);

        // Then
        assertEquals(expectedOutput, formattedOperations);
    }

}


