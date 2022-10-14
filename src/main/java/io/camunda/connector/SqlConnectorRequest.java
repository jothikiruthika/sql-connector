package io.camunda.connector;

import io.camunda.connector.api.annotation.Secret;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class SqlConnectorRequest {

  @NotEmpty
  private String message;

  @Valid
  @NotNull
  @Secret
  private Authentication authentication;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Authentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(Authentication authentication) {
    this.authentication = authentication;
  }

  @Override
  public int hashCode() {
    return Objects.hash(authentication, message);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    SqlConnectorRequest other = (SqlConnectorRequest) obj;
    return Objects.equals(authentication, other.authentication)
        && Objects.equals(message, other.message);
  }

  @Override
  public String toString() {
    return "SqlConnectorRequest [message=" + message + ", authentication=" + authentication + "]";
  }
}
