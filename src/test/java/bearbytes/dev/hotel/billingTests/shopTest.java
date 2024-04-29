package bearbytes.dev.hotel.billingTests;

import bearbytes.dev.hotel.checkout.Order;
import bearbytes.dev.hotel.product.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class shopTest {
    @Test
    public void testOrder() {
        Product product = new Product(1, "name", 1.0, "description",0);
        List<Product> products = new ArrayList<>();
        products.add(product);
        Order order = new Order(1, "2021-04-01", products);
        assertEquals(1, order.getOrderId());
        assertEquals("2021-04-01", order.getPurchaseDate());
        assertEquals(product, order.getPurchasedProducts().getFirst());
        assertEquals(1.0, order.getSubtotal());
    }

    @Test
    public void testProduct() {
        Product product = new Product(1, "name", 1.0, "image",0);
        assertEquals(1, product.getId());
        assertEquals("name", product.getName());
        assertEquals(1.0, product.getPrice());
        assertEquals("image", product.getImage());

    }

    @Test
    public void testProductSetters() {
        Product product = new Product(1, "name", 1.0, "image",0);
        product.setId(2);
        product.setName("newName");
        product.setPrice(2.0);
        assertEquals(2, product.getId());
        assertEquals("newName", product.getName());
        assertEquals(2.0, product.getPrice());
        assertEquals("image", product.getImage());

    }

}
