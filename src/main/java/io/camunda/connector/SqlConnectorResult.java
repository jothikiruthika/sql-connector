package io.camunda.connector;

import java.util.Objects;

public class SqlConnectorResult {

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
    final SqlConnectorResult that = (SqlConnectorResult) o;
    return Objects.equals(outputValue, that.outputValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(outputValue);
  }

  @Override
  public String toString() {
    return "SqlConnectorResult [outputValue=" + outputValue + "]";
  }

}
