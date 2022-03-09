package fr.sg.kata.infrastructure;

import fr.sg.kata.domain.*;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountStatementFormatter {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DecimalFormat decimalFormat = new DecimalFormat("#.00");

    private final String LINE_FORMAT = "%10s | %15s | %15s | %15s";
    private final String HEADER = String.format(LINE_FORMAT, "Date", "Operation", "Amount", "Balance");

    public String format(final OperationHistory operationHistory) {
        return Stream
                .of(
                        HEADER,
                        reverseStatementList(operationHistory.getStatements()).stream()
                                .sorted(Collections.reverseOrder())
                                .map(this::formatLine).collect(Collectors.joining(System.lineSeparator()))
                )
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private List<Statement> reverseStatementList(List<Statement> statements) {
        return statements.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(ArrayList::new), lst -> {
                            Collections.reverse(lst);
                            return lst.stream();
                        }
                )).collect(Collectors.toCollection(ArrayList::new));
    }

    private String formatLine(final Statement statement) {
        final Operation operation = statement.getOperation();
        return String.format(
                LINE_FORMAT,
                operation.getDate().format(dateTimeFormatter),
                getOperationsName(operation.getType()),
                formatAmount(operation.getAmount(), operation.getType()),
                formatBalance(statement.getBalance())
        );
    }

    private String formatAmount(final Amount amount, final Operation.Type type) {
        return switch (type) {
            case WITHDRAWAL -> "-" + formatAmount(amount);
            case DEPOSIT -> formatAmount(amount);
        };
    }

    private String formatAmount(final Amount amount) {
        return decimalFormat.format(amount.getValue());
    }

    private String formatBalance(final Balance balance) {
        return decimalFormat.format(balance.getValue());
    }

    private String getOperationsName(final Operation.Type type) {
        return switch (type) {
            case WITHDRAWAL -> "Withdrawal";
            case DEPOSIT -> "Deposit";
        };
    }

}
