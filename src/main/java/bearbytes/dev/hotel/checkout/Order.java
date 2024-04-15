package bearbytes.dev.hotel.checkout;

import bearbytes.dev.hotel.product.Product;

import java.util.List;

public class Order {
    private Integer orderId;
    private String purchaseDate = null;
    private List<Product> purchasedProducts = null;
    private Double subtotal = 0.0;


    public Order(Integer orderId, String purchaseDate, List<Product> purchasedProducts)  {
        this.orderId = orderId;
        this.purchaseDate = purchaseDate;
        this.purchasedProducts = purchasedProducts;
        for(Product p : purchasedProducts)  {
            subtotal += p.getPrice();
        }
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public Double getSubtotal() {
        return subtotal;
    }
}
