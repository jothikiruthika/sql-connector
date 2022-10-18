package io.camunda.connector.postgresql;

import io.camunda.connector.api.annotation.Secret;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.Objects;

public class PostgresqlConnectorRequest {

    //  private SqlFilter filter;
    @NotNull
    private Map<String, String> selector;
    @NotNull
    @Secret
    private Connection connection;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    private String filter;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    private String table;


    @Override
    public int hashCode() {
        return Objects.hash(getTable(), getSelector(), getFilter());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        PostgresqlConnectorRequest other = (PostgresqlConnectorRequest) obj;
        return Objects.equals(getSelector(), other.getSelector())
                && Objects.equals(getTable(), other.getTable())
                && Objects.equals(getFilter(), other.getFilter());
    }

    @Override
    public String toString() {
        return "PostgresqlConnectorRequest [connection= " + getConnection() + ", table= "+ getTable()+
                ", filters= " + getFilter() + ", selectors= " + getSelector() + "]";
    }

//  public void setFilter(SqlFilter filter) {
//    this.filter = filter;
//  }
//
//  public SqlFilter getFilter() {
//    return filter;
//  }

    public Map<String, String> getSelector() {
        return selector;
    }

    public void setSelector(Map<String, String> selector) {
        this.selector = selector;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
