package fr.sg.kata.infrastructure;

import fr.sg.kata.domain.Amount;
import fr.sg.kata.domain.Balance;
import fr.sg.kata.domain.Operation;
import fr.sg.kata.domain.OperationHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test du printer")
public class AccountStatementPrinterTest {

    private final ZoneId europeParisZoneId = ZoneId.of("Europe/Paris");

    private final Clock FIXED_CLOCK_1 = Clock.fixed(
            LocalDate.of(2022, 3, 5).atStartOfDay(europeParisZoneId).toInstant(),
            europeParisZoneId
    );

    @Test
    @DisplayName("Doit imprimer une input")
    void shouldFormatAndPrintOperationHistory() {
        // Given
        final OutputStream outputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(outputStream);
        final AccountStatementFormatter accountStatementFormatter = new AccountStatementFormatter();
        final AccountStatementPrinter accountStatementPrinter = new AccountStatementPrinter(printStream, accountStatementFormatter);

        final OperationHistory operationHistory = new OperationHistory();
        operationHistory.addOperation(new Operation(Operation.Type.DEPOSIT, LocalDate.now(FIXED_CLOCK_1), new Amount(BigDecimal.valueOf(100))), new Balance(BigDecimal.valueOf(100)));

        final String expectedOutput =
                "      Date |       Operation |          Amount |         Balance" + System.lineSeparator() +
                        "05/03/2022 |         Deposit |          100.00 |          100.00";

        // When
        accountStatementPrinter.print(operationHistory);

        // Then
        assertEquals(expectedOutput, outputStream.toString());
    }
}
