package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.checkout.OrderWithUsername;
import bearbytes.dev.hotel.database.OrderWithUsernameDAO;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.Collection;

/**
 * The OrderController class controls interactions when creating an order of
 * products.
 */
@RestController
@RequestMapping("/shop")
public class OrderController {
    private OrderWithUsernameDAO orderDAO;

    // The Default Constructor for an OrderController: creates an orderDAO instance.
    OrderController() {
        orderDAO = new OrderWithUsernameDAO();
    }

    /**
     * Confirms an order.
     * 
     * @param order The order to finalize.
     * @return True if the order was successfully finalized, else false.
     * @throws ClassNotFoundException If an Order was not found.
     * @throws SQLException           If a database access error occurs.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public Boolean add(@RequestBody OrderWithUsername order) throws ClassNotFoundException, SQLException {
        try {
            orderDAO.addOrder(order);
        } catch (Exception e) {
            System.out.println("order not processed");
        }
        return false;
    }

    /**
     * Retrieves all completed orders of a user
     *
     * @param username The username of guest.
     * @return Collection of orders retrieved from database.
     * @throws ClassNotFoundException If an Order was not found.
     * @throws SQLException           If a database access error occurs.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getOrders")
    public Collection<OrderWithUsername> getOrders(@RequestBody String username) throws ClassNotFoundException, SQLException {
        try {
            return orderDAO.getOrders(username);
        } catch (Exception e) {
            System.out.println("order not processed");
            e.printStackTrace();
        }
        return null;
    }
}
