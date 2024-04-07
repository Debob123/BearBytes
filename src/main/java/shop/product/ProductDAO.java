package shop.product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class ProductDAO  {
    private Properties p;
    private Connection c;

    static final String dbClassName = "com.mysql.cj.jdbc.Driver";
    static final String CONNECTION = "jdbc:mysql://127.0.0.1/mysql";

    public ProductDAO() {
        // Properties for user and password. Here the user
        // is root and the password is password
        p = new Properties();
        p.put("user", "root");
        p.put("password", "password");
        c = null;
    }

    public Collection<Product> getProducts() throws ClassNotFoundException, SQLException {
        List<Product> products = new ArrayList<>();

        // Class.forName(xxx) loads the jdbc classes and
        // creates a drivermanager class factory
        Class.forName(dbClassName);

        // Now try to connect
        try {
            c = DriverManager.getConnection(CONNECTION, p);
            // Query for the products
            Statement stmt = c.createStatement();
            String query = "SELECT * FROM myDB.Products";
            ResultSet rs = stmt.executeQuery(query);
            // Iterate over the returned products
            while (rs.next()) {
                Product p = extractProduct(rs);
                products.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return products;
    }

    private Product extractProduct(ResultSet rs) throws SQLException {
        String productName = rs.getString("productName");
        BigDecimal productPrice = rs.getBigDecimal("productPrice");

        return new Product(productName, productPrice);
    }
}
