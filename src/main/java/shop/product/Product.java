package shop;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private BigDecimal price;
    private String description;

    public Product(String name, BigDecimal price, String description)  {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void generateSerialNumber() {
        id = UUID.fromString(getName() + getDescription());
    }

    public String getName()  {
        return name;
    }
    public void setName(String name)  {
        this.name = name;
    }
    public UUID getId()  {
        return id;
    }
    public void setId(UUID id)  {
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

}
