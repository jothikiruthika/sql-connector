package io.camunda.connector;

import io.camunda.connector.api.annotation.Secret;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Connection {
    @Secret
    @NotEmpty
    private String serverUrl;
    @Secret
    @NotEmpty
    private String postgresUserName;
    @Secret
    @NotEmpty
    private String postgresPassword;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getPostgresUserName() {
        return postgresUserName;
    }

    public void setPostgresUserName(String postgresUserName) {
        this.postgresUserName = postgresUserName;
    }

    public String getPostgresPassword() {
        return postgresPassword;
    }

    public void setPostgresPassword(String postgresPassword) {
        this.postgresPassword = postgresPassword;
    }
}
