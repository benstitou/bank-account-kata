package fr.sg.kata.domain;

import java.util.ArrayList;
import java.util.List;

public class MockAccountStatementPrinter implements StatementPrinter {

    private final List<Statement> statements = new ArrayList<>();

    @Override
    public void print(OperationHistory operationHistory) {
        this.statements.addAll(operationHistory.getStatements());
    }

    public List<Statement> getStatements() {
        return statements;
    }
}
