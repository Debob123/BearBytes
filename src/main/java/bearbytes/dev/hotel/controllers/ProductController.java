package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.database.ReservationDAO;
import bearbytes.dev.hotel.product.Product;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/shop")
public class ProductController {
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
