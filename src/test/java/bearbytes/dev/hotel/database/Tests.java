package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.accounts.*;
import bearbytes.dev.hotel.controllers.AccountController;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tests {
    static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    static final String DB_CONNECTION = "jdbc:derby:myDB";
    static final String DB_USER = "";
    static final String DB_PASSWORD = "";

    AccountAuthenticator auth = new AccountAuthenticator();
    AccountController controller = new AccountController();

    @Test
    public void test1() throws SQLException {
        System.out.println(controller.authGuest(new Guest("guest", "password", "test", "test", "test", "test")));
    }

    public void temp(Account acc) {
        System.out.println("SELECT * FROM APP." + acc.getClass().getSimpleName() + "Accounts WHERE username = ?");
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
