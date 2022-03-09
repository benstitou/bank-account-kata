package fr.sg.kata.infrastructure;

import fr.sg.kata.domain.OperationHistory;
import fr.sg.kata.domain.StatementPrinter;

import java.io.PrintStream;

public class AccountStatementPrinter implements StatementPrinter {

    final private PrintStream printStream;
    final private AccountStatementFormatter accountStatementFormatter;

    public AccountStatementPrinter(final PrintStream printStream, final AccountStatementFormatter accountStatementFormatter) {
        this.printStream = printStream;
        this.accountStatementFormatter = accountStatementFormatter;
    }

    @Override
    public void print(OperationHistory operationHistory) {
        final String accountStatement = accountStatementFormatter.format(operationHistory);
        printStream.print(accountStatement);
    }
}