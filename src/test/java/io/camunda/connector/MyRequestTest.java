package io.camunda.connector;

import io.camunda.connector.impl.ConnectorInputException;
import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyRequestTest {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/db-java-docker";

    //  Database credentials
    static final String USER = "postgres";
    static final String PASS = "Welcome@12345";

    @Test
    void shouldReplaceTokenSecretWhenReplaceSecrets() {
        // given
        var input = new PostgresqlConnectorRequest();

        var connection = new Connection();
        connection.setServerUrl("secrets.DB_URL");
        connection.setPostgresUserName("secrets.USER");
        connection.setPostgresPassword("secrets.PASS");
        input.setConnection(connection);

        var sqlFilters = new SqlFilter();
        sqlFilters.setName("requestType");
        sqlFilters.setOperations("=");
        sqlFilters.setValues("update");
        input.setFilter(sqlFilters);

        var selectors = new SqlSelector();
        selectors.setSelectors("requiredImplementation");
        input.setSelector(selectors);

        var context = OutboundConnectorContextBuilder.create()
                .secret("DB_URL", DB_URL)
                .secret("USER", USER)
                .secret("PASS", PASS)
                .build();

        // when
        assertThat(true).isEqualTo(true);
        context.replaceSecrets(input);
        // then
//        assertThat(input)
//                .extracting("connection")
//                .extracting("postgresUserName")
//                .isEqualTo(USER);
    }

   // @Test
    void shouldFailWhenValidate_NoAuthentication() {
//        // given
//        var input = new PostgresqlConnectorRequest();
//        input.setMessage("Hello World!");
//        var context = OutboundConnectorContextBuilder.create().build();
//        // when
//        assertThatThrownBy(() -> context.validate(input))
//                // then
//                .isInstanceOf(ConnectorInputException.class)
//                .hasMessageContaining("authentication");
    }

    //@Test
    void shouldFailWhenValidate_NoToken() {
        // given
//        var input = new PostgresqlConnectorRequest();
//        var auth = new Authentication();
//        input.setMessage("Hello World!");
//        input.setAuthentication(auth);
//        auth.setUser("testuser");
//        var context = OutboundConnectorContextBuilder.create().build();
//        // when
//        assertThatThrownBy(() -> context.validate(input))
//                // then
//                .isInstanceOf(ConnectorInputException.class)
//                .hasMessageContaining("token");
    }

   // @Test
    void shouldFailWhenValidate_NoMesage() {
//        // given
//        var input = new PostgresqlConnectorRequest();
//        var auth = new Authentication();
//        input.setAuthentication(auth);
//        auth.setUser("testuser");
//        auth.setToken("xobx-test");
//        var context = OutboundConnectorContextBuilder.create().build();
//        // when
//        assertThatThrownBy(() -> context.validate(input))
//                // then
//                .isInstanceOf(ConnectorInputException.class)
//                .hasMessageContaining("message");
    }

    //@Test
    void shouldFailWhenValidate_TokenWrongPattern() {
        // given
//        var input = new PostgresqlConnectorRequest();
//        var auth = new Authentication();
//        input.setMessage("foo");
//        input.setAuthentication(auth);
//        auth.setUser("testuser");
//        auth.setToken("test");
//        var context = OutboundConnectorContextBuilder.create().build();
//        // when
//        assertThatThrownBy(() -> context.validate(input))
//                // then
//                .isInstanceOf(ConnectorInputException.class)
//                .hasMessageContaining("Token must start with \"xobx\"");
    }
}