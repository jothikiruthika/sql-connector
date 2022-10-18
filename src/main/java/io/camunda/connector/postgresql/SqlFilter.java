package io.camunda.connector.postgresql;

import jakarta.validation.constraints.NotEmpty;

public class SqlFilter {
    @NotEmpty
    private String name;
    @NotEmpty
    private String operations;
    @NotEmpty
    private String values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
