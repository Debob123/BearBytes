package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.database.ProductDAO;
import bearbytes.dev.hotel.database.ReservationDAO;
import bearbytes.dev.hotel.product.Product;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

// The ProductController class controls interactions with individual products.
@RestController
@RequestMapping("/shop")
public class ProductController {
    // An instance of an product via a Data Access Object
    ProductDAO productDAO;

    /**
     * The Default Constructor for a ProductController: creates a productDAO
     * instance.
     */
    public ProductController() {
        productDAO = new ProductDAO();
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
        } catch(Exception e) {
            System.out.println("API fail");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
