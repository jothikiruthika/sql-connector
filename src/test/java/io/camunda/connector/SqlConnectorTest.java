package io.camunda.connector;

import static org.assertj.core.api.Assertions.assertThat;

import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.Test;

public class SqlConnectorTest {
  static final String DB_URL = "jdbc:postgresql://localhost:5432/db-java-docker";

  //  Database credentials
  static final String USER = "postgres";
  static final String PASS = "Welcome@12345";
//  @Test
//  void shouldReturnReceivedMessageWhenExecute() throws Exception {
//    // given
//    var input = new PostgresqlConnectorRequest();
//
//    var connection = new Connection();
//    connection.setServerUrl(DB_URL);
//    connection.setPostgresUserName(USER);
//    connection.setPostgresPassword(PASS);
//    input.setConnection(connection);
//
//    var sqlFilters = new SqlFilter();
//    sqlFilters.setName("requestType");
//    sqlFilters.setOperations("=");
//    sqlFilters.setValues("update");
//    input.setFilter(sqlFilters);
//
//    var selectors = new SqlSelector();
//    selectors.setSelectors("requiredImplementation");
//    input.setSelector(selectors);
//
//    var context = OutboundConnectorContextBuilder.create()
//            .build();
//    var function = new PostgresqlConnectorFunction();
//    // when
//    var result = function.execute(context);
//    // then
//    assertThat(result)
//      .isInstanceOf(PostgresqlConnectorResult.class)
//      .extracting("outputValue")
//      .isEqualTo("create,none");
 // }
}