package bearbytes.dev.hotel.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

public interface InterfaceDAO<T> {
    static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    static final String DB_CONNECTION = "jdbc:derby:myDB";
    static final String DB_USER = "";
    static final String DB_PASSWORD = "";

    boolean add(T value) throws SQLException, ParseException;
    boolean remove(T value) throws SQLException;


}
