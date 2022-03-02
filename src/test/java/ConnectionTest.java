import connection.PostgresConnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ConnectionTest {
    @Test
    public void testConnection() {
        assertDoesNotThrow(() -> PostgresConnection.getInstance().getConnection());
    }
}

