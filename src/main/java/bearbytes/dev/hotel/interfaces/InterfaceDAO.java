package bearbytes.dev.hotel.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

public interface InterfaceDAO<T> {
    final String dbClassName = "com.mysql.cj.jdbc.Driver";
    final String CONNECTION = "jdbc:mysql://127.0.0.1/mysql";

    boolean add(T value) throws SQLException, ClassNotFoundException, ParseException;
    boolean remove(T value) throws SQLException;


}
