package shop.cart;

import shop.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cartItems = new ArrayList<>();

    public List<Product> getCartItems()  {
        return cartItems;
    }

    public double getCartSubtotal()  {
        BigDecimal subtotal = BigDecimal.valueOf(0.0);
        for(Product item : cartItems)  {
            subtotal = subtotal.add(item.getPrice());
        }
        return subtotal.doubleValue();
    }

    public double getCartTax()  {
        BigDecimal subtotal = BigDecimal.valueOf(getCartSubtotal());
        BigDecimal tax = subtotal.multiply(BigDecimal.valueOf(0.05));
        return tax.doubleValue();
    }

    public double getCartTotal()  {
        BigDecimal subtotal = BigDecimal.valueOf(getCartSubtotal());
        BigDecimal tax = BigDecimal.valueOf(getCartTax());
        BigDecimal total = subtotal.add(tax);
        return total.doubleValue();
    }

    public void addToCart(Product product)  {
        cartItems.add(product);
    }

    public void removeFromCart(Product product)  {
        cartItems.remove(product);
    }

}
