package shop;

import java.math.BigDecimal;

public class ProductSpecification {
    private String name;
    private int id;
    private BigDecimal price;
    private String description;

    public String getName()  {
        return name;
    }
    public void setName(String name)  {
        this.name = name;
    }
    public int getId()  {
        return id;
    }
    public void setId(int id)  {
        this.id = id;
    }
    public BigDecimal getPrice()  {
        return price;
    }
    public void setPrice(BigDecimal price)  {
        this.price = price;
    }
    public String getDescription()  {
        return description;
    }
    public void setDescription(String description)  {
        this.description = description;
    }

    public ProductSpecification(String name, BigDecimal price, String description)  {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
