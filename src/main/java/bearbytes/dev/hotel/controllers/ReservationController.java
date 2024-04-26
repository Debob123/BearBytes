package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.database.ReservationDAO;
import bearbytes.dev.hotel.exceptions.InvalidArgumentException;
import bearbytes.dev.hotel.reservation.Reservation;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationDAO resDAO;

    public ReservationController() {
        resDAO = new ReservationDAO();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public Boolean add(@RequestBody Reservation reservation) {
        try {
            return resDAO.add(reservation);
        } catch (SQLException | InvalidArgumentException e ) {
            e.printStackTrace();
        }
        return false;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/modify")
    public Collection<Integer> modify(@RequestBody Reservation[] reservations) {
        try {
            return resDAO.modify(reservations);
        } catch (SQLException | InvalidArgumentException e ) {
            e.printStackTrace();
        }
        List<Integer> result = new ArrayList<>();
        result.add(-1);
        return result;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/remove")
    public Boolean remove(@RequestBody Reservation reservation) {
        try {
            return resDAO.remove(reservation);
        } catch (SQLException | InvalidArgumentException e ) {
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
