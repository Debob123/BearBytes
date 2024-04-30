package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.database.ReservationDAO;
import bearbytes.dev.hotel.exceptions.InvalidArgumentException;
import bearbytes.dev.hotel.reservation.Reservation;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

// The ReservationController class controls interactions when on the reservation page.
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    // An instance of an Reservation via a Data Access Object
    private ReservationDAO resDAO;

    /**
     * The Default Constructor for a ReservationController: creates a reservationDAO
     * instance.
     */
    public ReservationController() {
        resDAO = new ReservationDAO();
    }

    /**
     * Confirms a reservation.
     *
     * @param reservation The reservation to confirm.
     * @return True if successfully confirmed, else false.
     * @throws ClassNotFoundException If no Reservation found.
     * @throws SQLException           If a database access error occurs.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public Boolean add(@RequestBody Reservation reservation) {
        try {
            return resDAO.add(reservation);
        } catch (SQLException | InvalidArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Modifies a current reservation.
     *
     * @param reservations The list of all current reservations, to find the current
     *                     reservation being modified.
     * @return True if successfully modified, else false.
     * @throws SQLException If a database access error occurs.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/modify")
    public Collection<Integer> modify(@RequestBody Reservation[] reservations) {
        try {
            return resDAO.modify(reservations);
        } catch (SQLException | InvalidArgumentException e) {
            e.printStackTrace();
        }
        List<Integer> result = new ArrayList<>();
        result.add(-1);
        return result;
    }

    /**
     * Removes a reservation.
     *
     * @param reservation The reservation to remove.
     * @return True if successfully removed, else false.
     * @throws ClassNotFoundException If a Reservation was not found.
     * @throws SQLException           If a database access error occurs.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/remove")
    public Boolean cancel(@RequestBody Reservation reservation) {
        try {
            Date today = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String todayDate = dateFormat.format(today);
            today = dateFormat.parse(todayDate);
            Date start = dateFormat.parse(reservation.getStartDate());
            long diffInMili = start.getTime() - today.getTime();
            long diff = TimeUnit.DAYS.convert(diffInMili, TimeUnit.MILLISECONDS);
            if (diff < 2) {
                return resDAO.cancel(reservation);
            } else {
                return resDAO.remove(reservation);
            }
        } catch (SQLException | InvalidArgumentException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Gets a collection of all current reservations of a user.
     *
     * @param username The name in the system for a user's reservations.
     * @return A Collection of all the reservations of the given user.
     * @throws ClassNotFoundException If the required class is not found.
     * @throws SQLException           If a database access error occurs.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getReservations")
    public Collection<Reservation> get(@RequestBody String username) throws ClassNotFoundException, SQLException {
        try {
            return resDAO.getAll(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Gets a collection of all current reservations.
     *
     * @return A Collection of all the reservations.
     * @throws ClassNotFoundException If the required class is not found.
     * @throws SQLException           If a database access error occurs.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAllReservations")
    public Collection<Reservation> getAllReservations() throws ClassNotFoundException, SQLException {
        try {
            return resDAO.getAllRes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
