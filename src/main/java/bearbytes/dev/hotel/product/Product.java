package bearbytes.dev.hotel.product;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * The Product class holds all information relating to an individual product
 * type that the hotel carries.
 */
public class Product {
    // The unique id of this product.
    private Integer id;

    // The name of the product.
    private String name;

    // The price of one instance of the product.
    private Double price;

    // An image link of the product.
    private String image;

    // The point value of a product.
    private Integer points;


    /**
     * The Default Constructor for a Product. Creates a unique id for the product,
     * names it, gives it a price and links an image.
     * 
     * @param id    The unique id of the product.
     * @param name  The name of the product.
     * @param price The price of one instance of the product.
     * @param image A link to an image of this product.
     * @param points The point value of one instance of the product.
     */
    public Product(int id, String name, double price, String image, int points) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.points = points;
    }


    /**
     * Gets the name of the product.
     * 
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     * 
     * @param name The new name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the unique id of the product.
     * 
     * @return The id of the product.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique id of the product.
     * 
     * @param id The new id of the product.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the price of the product.
     * 
     * @return The price of the product.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     * 
     * @param price The new price of one instance of the product.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the image link for the product.
     * 
     * @return The link for the image of the product.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image link for the product.
     * 
     * @param image The new link to an image for the product.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the point value for the product.
     *
     * @return point value of the product
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * Sets the point value for the product.
     *
     * @param points of the product
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(image, product.image) && Objects.equals(points, product.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, image, points);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", points=" + points +
                '}';
    }
}
