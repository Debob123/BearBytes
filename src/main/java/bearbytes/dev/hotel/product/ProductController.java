package bearbytes.dev.hotel.product;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.ArrayList;

@RestController
@RequestMapping("/shop")
public class ProductController {
    ProductDAO productDAO;

    public ProductController() {
        productDAO = new ProductDAO();
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
