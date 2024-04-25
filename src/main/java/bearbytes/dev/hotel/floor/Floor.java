package bearbytes.dev.hotel.floor;

import java.util.ArrayList;
import java.util.List;

/**
 * The Floor class contains a list of all the rooms the make up a floor in the
 * hotel. The theme can be set, and rooms can be added to the floor.
 */
public class Floor {
    // A list of all the rooms contained on this floor.
    List<Room> rooms = new ArrayList<>();

    // The theme of this floor.
    String floorTheme;

    /**
     * Sets the theme of this floor to the given theme.
     * 
     * @param floorTheme The theme to set this floor's theme to.
     */
    public void setFloorTheme(String floorTheme) {
        this.floorTheme = floorTheme;
    }

    /**
     * Sets the rooms of the floor.
     * 
     * @param rooms The rooms that now make up this floor.
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = new ArrayList<>(rooms);
    }

    /**
     * Gets a list of the rooms contained on this floor.
     * 
     * @return A list of all rooms on the floor.
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Gets the theme of the floor.
     * 
     * @return The theme of the floor.
     */
    public String getFloorTheme() {
        return floorTheme;
    }

    /**
     * Adds a room to the floor.
     * 
     * @param room The room to be added to the floor.
     */
    public void addRoom(Room room) {
        rooms.add(room);
    }
}
