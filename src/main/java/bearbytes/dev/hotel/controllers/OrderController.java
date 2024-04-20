package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.checkout.Order;
import bearbytes.dev.hotel.database.OrderDAO;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequestMapping("/shop")
public class OrderController {
    private OrderDAO orderDAO;

    OrderController() {
        orderDAO = new OrderDAO();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public Boolean add(@RequestBody Order order) throws ClassNotFoundException, SQLException {
        try {
            orderDAO.addOrder(order);
        } catch(Exception e) {
            System.out.println("order not processed");
        }
        return false;
    }
}
