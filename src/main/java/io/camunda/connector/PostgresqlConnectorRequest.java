package io.camunda.connector;

import io.camunda.connector.api.annotation.Secret;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class PostgresqlConnectorRequest {

//  private SqlFilter filter;
  @NotNull
  private SqlSelector selector;
  @NotNull
  private Connection connection;

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }

  private String filter;


  @Override
  public int hashCode() {
    return Objects.hash(getSelector(), getFilter());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    PostgresqlConnectorRequest other = (PostgresqlConnectorRequest) obj;
    return Objects.equals(getSelector(), other.getSelector())
        && Objects.equals(getFilter(), other.getFilter());
  }

  @Override
  public String toString() {
    return "PostgresqlConnectorRequest [connection= " + getConnection() +
            ", filters= " + getFilter() + ", selectors= " + getSelector() + "]";
  }

//  public void setFilter(SqlFilter filter) {
//    this.filter = filter;
//  }
//
//  public SqlFilter getFilter() {
//    return filter;
//  }

  public SqlSelector getSelector() {
    return selector;
  }

  public void setSelector(SqlSelector selector) {
    this.selector = selector;
  }

  public Connection getConnection() {
    return connection;
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }
}
