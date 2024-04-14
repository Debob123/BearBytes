package bearbytes.dev.hotel.interfaces;

import bearbytes.dev.hotel.floor.Room;

import java.sql.SQLException;
import java.util.Collection;

public interface IRoomDAO extends InterfaceDAO<Room>{
    Collection<Room> getAvailable(String[] dates) throws ClassNotFoundException, SQLException;

    boolean modify(Room[] r) throws ClassNotFoundException, SQLException;
    Collection<Room> getAll() throws ClassNotFoundException, SQLException;
}
