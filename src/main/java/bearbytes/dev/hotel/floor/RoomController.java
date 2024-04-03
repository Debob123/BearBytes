package bearbytes.dev.hotel.floor;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;

@RestController
@RequestMapping("/room")
public class RoomController {
    RoomDAO rooms;

    public RoomController() {
        rooms = new RoomDAO();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getAvailableRooms")
    public Collection<Room> getAvailableRooms(@RequestBody String[] dates) {
        try {
            return rooms.getAvailableRooms(dates);
        } catch(Exception e) {
            System.out.println("Woops");
        }
        return new ArrayList<>();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getRooms")
    public Collection<Room> getRooms() {
        try {
            return rooms.getRooms();
        } catch(Exception e) {
            System.out.println("Woops");
        }
        return new ArrayList<>();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public Boolean add(@RequestBody Room room) {
        try {
            return rooms.add(room);
        } catch(Exception e) {
            System.out.println("Woops");
        }

        return false;
    }
}
