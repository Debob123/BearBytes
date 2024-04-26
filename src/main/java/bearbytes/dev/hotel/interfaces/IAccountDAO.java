package bearbytes.dev.hotel.interfaces;

import bearbytes.dev.hotel.accounts.*;

import java.sql.SQLException;
import java.text.ParseException;

public interface IAccountDAO {
    static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    static final String DB_CONNECTION = "jdbc:derby:myDB";
    static final String DB_USER = "";
    static final String DB_PASSWORD = "";

    boolean verify();

    boolean checkAvailability();
    boolean addGuest(Guest g) throws SQLException;
    boolean addClerk(Clerk cl) throws SQLException;
    boolean addManager(Manager m) throws SQLException;
    boolean remove(Account acc) throws SQLException;

    boolean changeGuestUsername(Guest g, String u);
    boolean changeGuestPassword(Guest g, String p);
    boolean changeClerkUsername(Clerk cl, String u);
    boolean changeClerkPassword(Clerk cl, String p);
    boolean changeManagerUsername(Manager m, String u);
    boolean changeManagerPassword(Manager m, String p);
}