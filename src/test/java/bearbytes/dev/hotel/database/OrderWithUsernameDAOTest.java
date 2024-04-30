package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.checkout.OrderWithUsername;
import bearbytes.dev.hotel.product.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderWithUsernameDAOTest {
    static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    static final String DB_CONNECTION = "jdbc:derby:myDB";
    static final String DB_USER = "";
    static final String DB_PASSWORD = "";

    protected OrderWithUsernameDAO orderDAO = null;



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
    void clearTables()  {
        clearDB();
    }

    @BeforeEach
    void init() {orderDAO = new OrderWithUsernameDAO();}


    @Test
    void addSingleOrderTest()  {
        List<Product> purchasedProducts = new ArrayList<>();
        purchasedProducts.add(new Product(1, "", 0.00, "", 0));
        purchasedProducts.add(new Product(2, "", 0.00, "", 0));

        List<OrderWithUsername> orders = null;

        try {
            orderDAO.addOrder(new OrderWithUsername(1, "2024-04-29", purchasedProducts, "Jake"));
            orders = new ArrayList<>(orderDAO.getOrders("\"Jake\""));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, orders.size(), "Should be exactly one order in table");
    }

    @Test
    void correctUsernameInTableTest()  {
        List<Product> purchasedProducts = new ArrayList<>();
        purchasedProducts.add(new Product(1, "", 0.00, "", 0));
        purchasedProducts.add(new Product(2, "", 0.00, "", 0));

        List<OrderWithUsername> orders = null;

        try {
            orderDAO.addOrder(new OrderWithUsername(1, "2024-04-29", purchasedProducts, "Jake"));
            orders = new ArrayList<>(orderDAO.getOrders("\"Jake\""));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        assertEquals("Jake", orders.get(0).getUsername(), "Username should be identical");
    }

    @Test
    void correctProductsInTableTest()  {
        String[] names = { "Tropical Shirt", "Shark Necklace", "Locally Made Vases", "Beach Hat",
                "Sunglasses", "Beach Towels", "Locally Crafted Seashell Bracelet", "Beach Umbrella" };
        double[] prices = { 25.00, 15.00, 50.00, 20.00, 10.00, 30.00, 15.00, 25.00 };
        String[] images = { "tropical-shirt.jpg", "shark-necklace.jpg", "vase.jpg",
                "beach-hat.jpg", "sunglasses.jpg", "beach-towels.jpg",
                "seashell-bracelet.jpg", "beach-umbrella.jpg", };

        List<Product> purchasedProducts = new ArrayList<>();
        for(int i = 0; i < 8; i++)  {
            purchasedProducts.add(new Product(i + 1, names[i], prices[i], images[i], 0));
        }
        List<OrderWithUsername> orders = null;

        try {
            orderDAO.addOrder(new OrderWithUsername(1, "2024-04-29", purchasedProducts, "Jake"));
            orders = new ArrayList<>(orderDAO.getOrders("\"Jake\""));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Product> orderedProducts = orders.get(0).getPurchasedProducts();
        Collections.sort(orderedProducts, (p1, p2) -> p1.getId().compareTo(p2.getId()));

        assertEquals(purchasedProducts.toString(), orderedProducts.toString(), "Products should be identical");
    }

    @Test
    void addMultipleOrders()  {
        String[] names = { "Tropical Shirt", "Shark Necklace", "Locally Made Vases", "Beach Hat",
                "Sunglasses", "Beach Towels", "Locally Crafted Seashell Bracelet", "Beach Umbrella" };
        double[] prices = { 25.00, 15.00, 50.00, 20.00, 10.00, 30.00, 15.00, 25.00 };
        String[] images = { "tropical-shirt.jpg", "shark-necklace.jpg", "vase.jpg",
                "beach-hat.jpg", "sunglasses.jpg", "beach-towels.jpg",
                "seashell-bracelet.jpg", "beach-umbrella.jpg", };

        List<OrderWithUsername> orders = null;
        try {

            List<Product> purchasedProducts = new ArrayList<>();
            for(int i = 0; i < 3; i++)  {
                purchasedProducts.add(new Product(i + 1, names[i], prices[i], images[i], 0));
            }
            orderDAO.addOrder(new OrderWithUsername(1, "2024-04-29", purchasedProducts, "Jake"));

            purchasedProducts = new ArrayList<>();
            for(int i = 3; i < 7; i++)  {
                purchasedProducts.add(new Product(i + 1, names[i], prices[i], images[i], 0));
            }
            orderDAO.addOrder(new OrderWithUsername(1, "2024-04-29", purchasedProducts, "Jake"));

            purchasedProducts = new ArrayList<>();
            for(int i = 7; i < 8; i++)  {
                purchasedProducts.add(new Product(i + 1, names[i], prices[i], images[i], 0));
            }
            orderDAO.addOrder(new OrderWithUsername(1, "2024-04-29", purchasedProducts, "Jake"));


            orders = new ArrayList<>(orderDAO.getOrders("\"Jake\""));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        assertEquals(3, orders.size(), "Should be three separate orders");
    }

    @Test
    void differentOrderIdsTest()  {
        String[] names = { "Tropical Shirt", "Shark Necklace", "Locally Made Vases", "Beach Hat",
                "Sunglasses", "Beach Towels", "Locally Crafted Seashell Bracelet", "Beach Umbrella" };
        double[] prices = { 25.00, 15.00, 50.00, 20.00, 10.00, 30.00, 15.00, 25.00 };
        String[] images = { "tropical-shirt.jpg", "shark-necklace.jpg", "vase.jpg",
                "beach-hat.jpg", "sunglasses.jpg", "beach-towels.jpg",
                "seashell-bracelet.jpg", "beach-umbrella.jpg", };

        List<OrderWithUsername> orders = null;
        try {

            List<Product> purchasedProducts = new ArrayList<>();
            for(int i = 0; i < 3; i++)  {
                purchasedProducts.add(new Product(i + 1, names[i], prices[i], images[i], 0));
            }
            orderDAO.addOrder(new OrderWithUsername(1, "2024-04-29", purchasedProducts, "Jake"));

            purchasedProducts = new ArrayList<>();
            for(int i = 3; i < 7; i++)  {
                purchasedProducts.add(new Product(i + 1, names[i], prices[i], images[i], 0));
            }
            orderDAO.addOrder(new OrderWithUsername(1, "2024-04-29", purchasedProducts, "Jake"));

            purchasedProducts = new ArrayList<>();
            for(int i = 7; i < 8; i++)  {
                purchasedProducts.add(new Product(i + 1, names[i], prices[i], images[i], 0));
            }
            orderDAO.addOrder(new OrderWithUsername(1, "2024-04-29", purchasedProducts, "Jake"));


            orders = new ArrayList<>(orderDAO.getOrders("\"Jake\""));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String orderIds = orders.get(0).getOrderId() + ", " + orders.get(1).getOrderId() + ", " + orders.get(2).getOrderId();

        assertEquals("1, 2, 3", orderIds, "Order IDs should be unique and increment");
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

    @AfterEach
    void resetTables()  {
        clearDB();
    }



    static void clearDB()  {
        Connection dbConnection = null;
        Statement statement = null;
        String clearOrderRowsSQL = "DELETE FROM APP.Orders";
        String clearOrderItemsSQL = "DELETE FROM APP.OrderItems";
        String resetProductIDCounterSQL = "ALTER TABLE Products ALTER COLUMN productID RESTART WITH 1";
        String resetOrderIDCounterSQL = "ALTER TABLE Orders ALTER COLUMN orderID RESTART WITH 1";

        System.out.println("===============================================");
        try  {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(clearOrderRowsSQL);
            statement.execute(clearOrderItemsSQL);
            System.out.println("Tables cleared");
            statement.execute(resetProductIDCounterSQL);
            statement.execute(resetOrderIDCounterSQL);
            System.out.println("IDs Reset");
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

