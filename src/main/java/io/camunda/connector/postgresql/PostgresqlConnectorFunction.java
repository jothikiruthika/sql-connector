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

        LOGGER.info("Executing my connector with request {}", connectorRequest);

        Connection conn = null;
        Statement stmt = null;
        List<String> results = new ArrayList<String>();
        try {
            io.camunda.connector.postgresql.Connection connection = connectorRequest.getConnection();
            LOGGER.info("Connecting to database {} {}",connection.getServerUrl(),connection.getPostgresUserName());
            conn = DriverManager.getConnection(connection.getServerUrl(), connection.getPostgresUserName(), connection.getPostgresPassword());
            stmt = conn.createStatement();
            String sql = prepareSqlQuery(connectorRequest);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                retrieveData(connectorRequest, results, rs);
            }
            closeResources(conn, stmt, rs);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        var result = new PostgresqlConnectorResult();
        result.setOutputValue(results.stream().collect(Collectors.joining(",")));
        return result;
    }

    private static void closeResources(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        rs.close();
        stmt.close();
        conn.close();
    }

    private static String prepareSqlQuery(PostgresqlConnectorRequest connectorRequest) {
        var selectList = connectorRequest.getSelector().keySet().stream().collect(Collectors.joining(","));

        String sql = "SELECT " + selectList + " FROM " + connectorRequest.getTable();

        String filter = connectorRequest.getFilter();
        if (filter != null)
            sql += " where " + filter;

        System.out.println("sql query -- " + sql);
        return sql;
    }

    private static void retrieveData(PostgresqlConnectorRequest connectorRequest, List<String> results, ResultSet rs) {
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
}
