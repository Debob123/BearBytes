package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.checkout.Order;
import bearbytes.dev.hotel.checkout.OrderWithUsername;
import bearbytes.dev.hotel.database.OrderDAO;
import bearbytes.dev.hotel.database.OrderWithUsernameDAO;
import bearbytes.dev.hotel.database.ReservationDAO;
import bearbytes.dev.hotel.product.Product;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The OrderController class controls interactions when creating an order of
 * products.
 */
@RestController
@RequestMapping("/shop")
public class OrderController {
<<<<<<< HEAD
    private OrderWithUsernameDAO orderDAO;
=======
    // An instance of an order via a Data Access Object
    private OrderDAO orderDAO;
>>>>>>> main

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

<<<<<<< HEAD
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getOrders")
    public Collection<OrderWithUsername> getProducts(@RequestBody String username) {
        try {
            return orderDAO.getOrders(username);
        } catch(Exception e) {
            System.out.println("API fail");
            e.printStackTrace();
=======
    // The ProductController class controls interactions with individual products.
    @RestController
    @RequestMapping("/shop")
    public static class ProductController {
        // An instance of an product via a Data Access Object
        ReservationDAO.ProductDAO productDAO;

        /**
         * The Default Constructor for a ProductController: creates a productDAO
         * instance.
         */
        public ProductController() {
            productDAO = new ReservationDAO.ProductDAO();
        }

        /**
         * Gets all the products currently in the order.
         * 
         * @return True if all products correctly found, else false.
         */
        @CrossOrigin(origins = "http://localhost:3000")
        @PostMapping("/getProducts")
        public Collection<Product> getProducts() {
            try {
                return productDAO.getProducts();
            } catch (Exception e) {
                System.out.println("API fail");
                e.printStackTrace();
            }
            return new ArrayList<>();
>>>>>>> main
        }
        return new ArrayList<>();
    }
}
