package bearbytes.dev.hotel.checkout;

import bearbytes.dev.hotel.product.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;

/**
 * This class tracks all information necessary to successfully operate an order,
 * including the purchase date, the price, and all the items purchased.
 */
public class Order {
    // The unique id of the order.
    private Integer orderId;

    // The purchase date of the order.
    private String purchaseDate = null;

    // The list of all products in the order.
    private List<Product> purchasedProducts = null;

    // The price of the order.
    private Double subtotal = 0.0;

    /**
     * The Default Constructor for an Order: sets a unique id, the purchase date,
     * and all the purchased products and sets the correct price.
     * 
     * @param orderId           The unique id of this order.
     * @param purchaseDate      The date the order was purchased.
     * @param purchasedProducts The products that were ordered.
     */
    @JsonCreator
    public Order(Integer orderId, String purchaseDate, List<Product> purchasedProducts) {
        this.orderId = orderId;
        this.purchaseDate = purchaseDate;
        this.purchasedProducts = purchasedProducts;
        for (Product p : this.purchasedProducts) {
            subtotal += p.getPrice();
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", purchasedProducts=" + purchasedProducts +
                ", subtotal=" + subtotal +
                '}';
    }

    /**
     * Gets the unique id of this order.
     * 
     * @return The unique id of this order.
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * Sets the id of this order.
     * 
     * @param orderId The new unique id of this order.
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the purchase date of the order.
     * 
     * @return The purchase date of the order.
     */
    public String getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the purchase date of the order.
     * 
     * @param purchaseDate The new purchase date of the order.
     */
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * Gets the list of products in the order.
     * 
     * @return A list of all products in the order.
     */
    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    /**
     * Sets the list of products in the order.
     * 
     * @param purchasedProducts The new list of products that make up the order.
     */
    public void setPurchasedProducts(List<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    /**
     * Gets the price of the order.
     * 
     * @return The price of the order.
     */
    public Double getSubtotal() {
        return subtotal;
    }
}
