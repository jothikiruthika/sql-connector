package io.camunda.connector;

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
        inputVariables = {"filter", "selector", "connection"},
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
            io.camunda.connector.Connection connection = connectorRequest.getConnection();
            conn = DriverManager.getConnection(connection.getServerUrl(), connection.getPostgresUserName(), connection.getPostgresPassword());
            String sql = "";

            stmt = conn.createStatement();
            sql = "SELECT " + connectorRequest.getSelector() .getSelectors()+ " FROM RequiredImplementations";
            SqlFilter filter = connectorRequest.getFilter();
            if (filter != null)
                sql += " where " + filter.getName() +" "+ filter.getOperations() + " '" + filter.getValues() + "'";
            System.out.println("sql query -- "+ sql);
            ResultSet rs = stmt.executeQuery(sql);

            var selector = connectorRequest.getSelector().getSelectors();
            while (rs.next()) {
                String result = rs.getString(selector);
                results.add(result);
                System.out.print("Retrieved : " + result);
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
