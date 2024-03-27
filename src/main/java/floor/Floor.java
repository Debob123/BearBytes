package floor;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    List<Room> rooms = new ArrayList<>();
    Theme floorTheme;

    public void setFloorTheme(Theme floorTheme) { this.floorTheme = floorTheme; }

    public void setRooms(List<Room> rooms) { this.rooms = new ArrayList<>(rooms); }

    public List<Room> getRooms() { return rooms; }

    public Theme getFloorTheme() { return floorTheme; }

    public void addRoom(Room room) { rooms.add(room); }

    public enum Theme {NATURE_RETREAT, URBAN_ELEGANCE, VINTAGE_CHARM}
}
