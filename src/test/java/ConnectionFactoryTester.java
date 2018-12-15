import com.wcc.ConnectionFactory;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionFactoryTester {
    @Test
    public void testConnection() throws SQLException {
        ConnectionFactory factory = ConnectionFactory.getInstance();
        Connection connection = factory.makeConnection();
        connection.close();
    }
}
