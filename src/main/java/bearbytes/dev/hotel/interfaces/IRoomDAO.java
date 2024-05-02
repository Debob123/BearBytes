package bearbytes.dev.hotel.interfaces;

import bearbytes.dev.hotel.floor.Room;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IRoomDAO extends InterfaceDAO<Room> {
    Collection<Room> getAvailable(String[] dates) throws ClassNotFoundException, SQLException;

    boolean modify(Room[] r) throws ClassNotFoundException, SQLException;

    Collection<Room> getAll() throws ClassNotFoundException, SQLException;

    List<Map<String, String>> roomStatus(int roomNumber) throws ClassNotFoundException, SQLException;

}
