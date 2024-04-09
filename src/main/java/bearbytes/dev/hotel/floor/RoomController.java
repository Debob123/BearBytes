package bearbytes.dev.hotel.floor;

import bearbytes.dev.hotel.interfaces.IRoomDAO;
import bearbytes.dev.hotel.interfaces.InterfaceDAO;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;

@RestController
@RequestMapping("/room")
public class RoomController {
    IRoomDAO rooms;

    public RoomController() {
        rooms = new RoomDAO();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getAvailableRooms")
    public Collection<Room> getAvailableRooms(@RequestBody String[] dates) {
        try {
            return rooms.getAvailable(dates);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getRooms")
    public Collection<Room> getRooms() {
        try {
            return rooms.getAll();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public boolean add(@RequestBody Room room) {
        try {
            return rooms.add(room);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/modify")
    public boolean modify(@RequestBody Room[] r) {
        try {
            return rooms.modify(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
