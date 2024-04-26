package bearbytes.dev.hotel.checkout;

import bearbytes.dev.hotel.product.Product;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class OrderWithUsername extends Order {
    String username;

    @JsonCreator
    public OrderWithUsername(Integer orderId, String purchaseDate, List<Product> purchasedProducts, String username) {
        super(orderId, purchaseDate, purchasedProducts);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username)  {
        this.username = username;
    }
}
