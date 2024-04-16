package bearbytes.dev.hotel.interfaces;

import bearbytes.dev.hotel.accounts.Guest;

import java.sql.SQLException;
import java.text.ParseException;

public interface IAccountDAO {
    static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    static final String DB_CONNECTION = "jdbc:derby:myDB";
    static final String DB_USER = "";
    static final String DB_PASSWORD = "";

    boolean verify();

    boolean checkAvailability();
    boolean add(Guest g) throws SQLException;
    boolean remove(Guest g) throws SQLException;
}
