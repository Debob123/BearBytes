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

@RestController
@RequestMapping("/shop")
public class OrderController {
    private OrderWithUsernameDAO orderDAO;

    OrderController() {
        orderDAO = new OrderWithUsernameDAO();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public Boolean add(@RequestBody OrderWithUsername order) throws ClassNotFoundException, SQLException {
        try {
            orderDAO.addOrder(order);
        } catch(Exception e) {
            System.out.println("order not processed");
        }
        return false;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getOrders")
    public Collection<OrderWithUsername> getProducts(@RequestBody String username) {
        try {
            return orderDAO.getOrders(username);
        } catch(Exception e) {
            System.out.println("API fail");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
