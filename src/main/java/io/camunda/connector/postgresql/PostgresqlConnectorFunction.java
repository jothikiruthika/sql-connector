package io.camunda.connector.postgresql;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@OutboundConnector(
        name = "PostgresqlConnector",
        inputVariables = {"filter", "selector", "connection", "table"},
        type = "io.camunda:postgresql-connector:1")
public class PostgresqlConnectorFunction implements OutboundConnectorFunction {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresqlConnectorFunction.class);

    @Override
    public Object execute(OutboundConnectorContext context) throws Exception {
        var connectorRequest = context.getVariablesAsType(PostgresqlConnectorRequest.class);

//    context.validate(connectorRequest);
        context.replaceSecrets(connectorRequest);

        return executeConnector(connectorRequest);
    }

    private PostgresqlConnectorResult executeConnector(final PostgresqlConnectorRequest connectorRequest) {
        // TODO: implement connector logic
        LOGGER.info("Executing my connector with request {}", connectorRequest);

        Connection conn = null;
        Statement stmt = null;
        List<String> results = new ArrayList<String>();
        try {
            System.out.println("Connecting to database...");
            io.camunda.connector.postgresql.Connection connection = connectorRequest.getConnection();
            conn = DriverManager.getConnection(connection.getServerUrl(), connection.getPostgresUserName(), connection.getPostgresPassword());
            String sql = "";

            stmt = conn.createStatement();
            var selectList = connectorRequest.getSelector().keySet().stream().collect(Collectors.joining(","));
            sql = "SELECT " + selectList + " FROM " + connectorRequest.getTable();
            //SqlFilter filter = connectorRequest.getFilter();
            String filter = connectorRequest.getFilter();
            if (filter != null)
                // sql += " where " + filter.getName() +" "+ filter.getOperations() + " '" + filter.getValues() + "'";
                sql += " where " + filter;
            System.out.println("sql query -- " + sql);
            ResultSet rs = stmt.executeQuery(sql);


            try {
            while (rs.next()) {
                connectorRequest.getSelector().forEach((colName, colTYpe) -> {
                    try {
                    switch (colTYpe.toLowerCase()) {
                        case "string": {
                            results.add(rs.getString(colName));
                            break;
                        }
                        case "bool": {
                            results.add(String.valueOf(rs.getBoolean(colName)));
                            break;
                        }
                        case "int": {
                            results.add(String.valueOf(rs.getInt(colName)));
                            break;
                        }
                    }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
        var result = new PostgresqlConnectorResult();
        result.setOutputValue(results.stream().collect(Collectors.joining(",")));
        return result;
    }
}
