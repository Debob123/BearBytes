package shop.product;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price)  {
        this.name = name;
        this.price = price;
        generateSerialNumber();
    }

    private void generateSerialNumber() {
        id = UUID.fromString(name);
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

}
