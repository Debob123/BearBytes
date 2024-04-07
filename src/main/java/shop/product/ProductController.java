package shop.product;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.ArrayList;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductDAO products;

    public ProductController() {
        products = new ProductDAO();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getproducts")
    public Collection<Product> getRooms() {
        try {
            return products.getProducts();
        } catch(Exception e) {
            System.out.println("API fail");
        }
        return new ArrayList<>();
    }
}
