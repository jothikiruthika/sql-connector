package io.camunda.connector.postgresql;

import jakarta.validation.constraints.NotEmpty;

public class            SqlSelector {
    @NotEmpty
    private String selectors;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public String getSelectors() {
        return selectors;
    }

    public void setSelectors(String selectors) {
        this.selectors = selectors;
    }
}
