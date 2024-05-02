package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.database.RoomDAO;
import bearbytes.dev.hotel.floor.Room;
import bearbytes.dev.hotel.interfaces.IRoomDAO;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The RoomController class controls all interactions when looking at available
 * rooms.
 */
@RestController
@RequestMapping("/room")
public class RoomController {
    // An instance of an Room via a Data Access Object
    IRoomDAO rooms;

    // The Default Constructor for a RoomController: creates a RoomDAO instance.
    public RoomController() {
        rooms = new RoomDAO();
    }

    /**
     * Gets all available rooms in the hotel over a period of time.
     * 
     * @param dates The time constraint for the rooms to be available for.
     * @return The Collection of rooms that are available for the given constraints.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getAvailableRooms")
    public Collection<Room> getAvailableRooms(@RequestBody String[] dates) {
        try {
            return rooms.getAvailable(dates);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Gets all rooms in the hotel.
     * 
     * @return A collection of all the rooms in the hotel.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getRooms")
    public Collection<Room> getRooms() {
        try {
            return rooms.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Adds a room to the hotel.
     * 
     * @param room The room to add.
     * @return True if the room is successfully added, else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public boolean add(@RequestBody Room room) {
        try {
            return rooms.add(room);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Modifies an existing room.
     * 
     * @param r The list of rooms to modify.
     * @return True if all rooms were correctly modified, else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/modify")
    public boolean modify(@RequestBody Room[] r) {
        try {
            return rooms.modify(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/status/{roomNumber}")
    public String getRoomStatus(@PathVariable int roomNumber) throws ClassNotFoundException {
        try {
            return rooms.roomStatus(roomNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred while checking room status";
        }
    }
}
