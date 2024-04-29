package bearbytes.dev.hotel.product;

import java.math.BigDecimal;
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

    private Integer points;


    /**
     * The Default Constructor for a Product. Creates a unique id for the product,
     * names it, gives it a price and links an image.
     * 
     * @param id    The unique id of the product.
     * @param name  The name of the product.
     * @param price The price of one instance of the product.
     * @param image A link to an image of this product.
     */
    public Product(int id, String name, double price, String image, int points) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.points = points;
    }

    /**
     * The Default Constructor for a Product. Creates a unique id for the product,
     * names it, gives it a price and links an image.
     *
     * @param id    The unique id of the product.
     * @param name  The name of the product.
     * @param price The price of one instance of the product.
     * @param image A link to an image of this product.
     */
    public Product(int id, String name, double price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
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
     * Gets the image link for the product.
     *
     * @return points of the product
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * Sets the image link for the product.
     *
     * @param points of the product
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

}
