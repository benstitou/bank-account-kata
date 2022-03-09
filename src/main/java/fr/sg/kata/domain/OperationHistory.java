package fr.sg.kata.domain;

import java.util.ArrayList;
import java.util.List;

public class OperationHistory {

    private final List<Statement> statements;

    public OperationHistory() {
        this.statements = new ArrayList<>();
    }

    public void addOperation(final Operation operation, final Balance balance) {
        this.statements.add(new Statement(operation, balance));
    }

    public List<Statement> getStatements() {
        return statements;
    }
}
