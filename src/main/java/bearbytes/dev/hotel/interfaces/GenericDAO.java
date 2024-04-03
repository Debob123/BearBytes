package bearbytes.dev.hotel.interfaces;

import java.sql.Connection;
import java.util.Properties;

public interface GenericDAO {
    static final String dbClassName = "com.mysql.cj.jdbc.Driver";
    static final String CONNECTION = "jdbc:mysql://127.0.0.1/mysql";
}
