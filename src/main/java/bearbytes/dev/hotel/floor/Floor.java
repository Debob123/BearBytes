package bearbytes.dev.hotel.floor;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    List<Room> rooms = new ArrayList<>();
    String floorTheme;

    public void setFloorTheme(String floorTheme) { this.floorTheme = floorTheme; }

    public void setRooms(List<Room> rooms) { this.rooms = new ArrayList<>(rooms); }

    public List<Room> getRooms() { return rooms; }

    public String getFloorTheme() { return floorTheme; }

    public void addRoom(Room room) { rooms.add(room); }
}
