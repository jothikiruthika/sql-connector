package io.camunda.connector.postgresql;

import java.util.Objects;

public class PostgresqlConnectorResult {

  // TODO: define connector result properties, which are returned to the process engine
  private String outputValue;

  public String getOutputValue() {
    return outputValue;
  }

  public void setOutputValue(String outputValue) {
    this.outputValue = outputValue;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PostgresqlConnectorResult that = (PostgresqlConnectorResult) o;
    return Objects.equals(outputValue, that.outputValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(outputValue);
  }

  @Override
  public String toString() {
    return "PostgresqlConnectorResult [outputValue=" + outputValue + "]";
  }

}
