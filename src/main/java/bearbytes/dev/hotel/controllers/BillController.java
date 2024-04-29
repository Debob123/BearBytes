package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.bill.Bill;
import bearbytes.dev.hotel.checkout.Order;
import bearbytes.dev.hotel.checkout.OrderWithUsername;
import bearbytes.dev.hotel.database.BillDAO;
import bearbytes.dev.hotel.database.OrderDAO;
import bearbytes.dev.hotel.database.OrderWithUsernameDAO;
import bearbytes.dev.hotel.database.ReservationDAO;
import bearbytes.dev.hotel.product.Product;
import bearbytes.dev.hotel.reservation.Reservation;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The OrderController class controls interactions when creating an order of
 * products.
 */
@RestController
@RequestMapping("/bill")
public class BillController {
    // An instance of an order via a Data Access Object
    private BillDAO billDAO;
    private ReservationDAO reservationDAO;
    private OrderWithUsernameDAO orderDAO;

    // The Default Constructor for an OrderController: creates an orderDAO instance.
    BillController() {
        billDAO = new BillDAO();
        reservationDAO = new ReservationDAO();
        orderDAO = new OrderWithUsernameDAO();
    }

    /**
     * Generates a bill for a guest.
     *
     * @param username The username of current user.
     * @return Final bill of user.
     * @throws ClassNotFoundException If Bill was not inserted.
     * @throws SQLException           If a database access error occurs.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/generateBill")
    public Bill generateBill(@RequestBody String username) throws ClassNotFoundException, SQLException {
        try {
            List<Reservation> reservations = new ArrayList<>(reservationDAO.getAll(username));
            List<Order> orders = new ArrayList<>(orderDAO.getOrders(username));
            return billDAO.generateBill(reservations, orders, username);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("bill not generated");
        }
        return null;
    }

}
