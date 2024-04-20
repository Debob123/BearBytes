package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.checkout.Order;
import bearbytes.dev.hotel.database.OrderDAO;
import bearbytes.dev.hotel.database.ReservationDAO;
import bearbytes.dev.hotel.product.Product;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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

    @RestController
    @RequestMapping("/shop")
    public static class ProductController {
        ReservationDAO.ProductDAO productDAO;

        public ProductController() {
            productDAO = new ReservationDAO.ProductDAO();
        }

        @CrossOrigin(origins = "http://localhost:3000")
        @PostMapping("/getProducts")
        public Collection<Product> getProducts() {
            try {
                return productDAO.getProducts();
            } catch(Exception e) {
                System.out.println("API fail");
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }
}
