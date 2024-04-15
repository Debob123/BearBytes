package bearbytes.dev.hotel.product;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private String image;

    public Product(Integer id, String name, Double price, String image)  {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName()  {
        return name;
    }
    public void setName(String name)  {
        this.name = name;
    }
    public Integer getId()  {
        return id;
    }
    public void setId(Integer id)  {
        this.id = id;
    }
    public Double getPrice()  {
        return price;
    }
    public void setPrice(Double price)  {
        this.price = price;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
