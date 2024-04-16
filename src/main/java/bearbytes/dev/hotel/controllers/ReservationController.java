package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.database.ReservationDAO;
import bearbytes.dev.hotel.reservation.Reservation;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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
            return resDAO.add(reservation);
        } catch(Exception e) {
            System.out.println("Woops");
        }
        return false;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/remove")
    public Boolean remove(@RequestBody Reservation reservation) throws ClassNotFoundException, SQLException {
        try {
            return resDAO.remove(reservation);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getReservations")
    public Collection<Reservation> get(@RequestBody String username) throws ClassNotFoundException, SQLException {
        try {
            return resDAO.getAll(username);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
