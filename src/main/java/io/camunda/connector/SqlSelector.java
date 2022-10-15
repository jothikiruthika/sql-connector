package io.camunda.connector;

import jakarta.validation.constraints.NotEmpty;

public class SqlSelector {
    @NotEmpty
    private String selectors;

    public String getSelectors() {
        return selectors;
    }

    public void setSelectors(String selectors) {
        this.selectors = selectors;
    }
}
