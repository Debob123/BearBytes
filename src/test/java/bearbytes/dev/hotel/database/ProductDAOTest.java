package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.product.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDAOTest {
    static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    static final String DB_CONNECTION = "jdbc:derby:myDB";
    static final String DB_USER = "";
    static final String DB_PASSWORD = "";

    protected ProductDAO productDAO = null;



    @BeforeAll
    static void loadProducts()  {
        clearProductDB();
        Connection dbConnection = getDBConnection();
        try {
            addDefaultProducts(dbConnection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void init() {productDAO = new ProductDAO();}


    @Test
    void getProductFromTableTest()  {
        String[] names = { "Tropical Shirt", "Shark Necklace", "Locally Made Vases", "Beach Hat",
                "Sunglasses", "Beach Towels", "Locally Crafted Seashell Bracelet", "Beach Umbrella" };
        double[] prices = { 25.00, 15.00, 50.00, 20.00, 10.00, 30.00, 15.00, 25.00 };
        String[] images = { "tropical-shirt.jpg", "shark-necklace.jpg", "vase.jpg",
                "beach-hat.jpg", "sunglasses.jpg", "beach-towels.jpg",
                "seashell-bracelet.jpg", "beach-umbrella.jpg" };

        List<Product> products = new ArrayList<>();
        List<Product> result = null;
        for(int i = 0; i < 8; i++)  {
            products.add(new Product(i + 1, names[i], prices[i], images[i], 0));
        }
        try {
            result = new ArrayList<>(productDAO.getProducts());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(result, (p1, p2) -> p1.getId().compareTo(p2.getId()));

        assertEquals(products.toString(), result.toString(), "product lists should be the same");
    }




    static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }


    static void clearProductDB()  {
        Connection dbConnection = null;
        Statement statement = null;
        String clearRowsSQL = "DELETE FROM APP.Products";
        String resetIDCounterSQL = "ALTER TABLE Products ALTER COLUMN productID RESTART WITH 1";

        System.out.println("===============================================");
        try  {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(clearRowsSQL);
            System.out.println("Table cleared");
            statement.execute(resetIDCounterSQL);
            System.out.println("ID Reset");
        } catch(SQLException e)  {
            System.out.println(e.getMessage());
        } finally  {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch(SQLException e)  {
                e.getMessage();
            }
        }
    }

    public static void addDefaultProducts(Connection dbConnection) throws SQLException {
        String[] names = { "Tropical Shirt", "Shark Necklace", "Locally Made Vases", "Beach Hat",
                "Sunglasses", "Beach Towels", "Locally Crafted Seashell Bracelet", "Beach Umbrella" };
        double[] prices = { 25.00, 15.00, 50.00, 20.00, 10.00, 30.00, 15.00, 25.00 };
        String[] images = { "tropical-shirt.jpg", "shark-necklace.jpg", "vase.jpg",
                "beach-hat.jpg", "sunglasses.jpg", "beach-towels.jpg",
                "seashell-bracelet.jpg", "beach-umbrella.jpg", };
        String insertProductsSQL = "INSERT INTO Products(name,price,image) values(?,?,?)";

        for (int i = 0; i < names.length; i++) {
            PreparedStatement ps = dbConnection.prepareStatement(insertProductsSQL);

            ps.setString(1, names[i]);
            ps.setDouble(2, prices[i]);
            ps.setString(3, images[i]);

            ps.executeUpdate();
        }
    }
}
