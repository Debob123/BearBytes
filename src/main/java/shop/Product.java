package shop;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private UUID serialNumber = null;
    ProductSpecification specification = null;

    public void generateSerialNumber()  {
        serialNumber = UUID.fromString(specification.getName() + specification.getDescription());
    }
    public Product(String name, BigDecimal price, String description)  {
        specification = new ProductSpecification(name, price, description);
        generateSerialNumber();
    }


}
