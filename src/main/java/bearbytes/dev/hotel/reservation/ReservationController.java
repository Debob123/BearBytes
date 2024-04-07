package bearbytes.dev.hotel.reservation;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationDAO resDAO;

    ReservationController() {
        resDAO = new ReservationDAO();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public Boolean add(@RequestBody Reservation reservation) throws ClassNotFoundException, SQLException {
        try {
            resDAO.add(reservation);
        } catch(Exception e) {
            System.out.println("Woops");
        }
        return false;
    }
}
