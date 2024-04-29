package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.checkout.Order;
import bearbytes.dev.hotel.product.Product;

import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * The OrderDAO Class provides data access operations for Orders in the hotel.
 * This means adding an order to the database, and getting all orders from the
 * database.
 */
public class OrderDAO {
    // The driver of the connected database.
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    // The connection string of the database.
    private static final String DB_CONNECTION = "jdbc:derby:myDB";

    // The user String for the database.
    private static final String DB_USER = "";

    // The password String for the database.
    private static final String DB_PASSWORD = "";

    /**
     * Adds an order to the hotel's database.
     * 
     * @param order The order to add to the database.
     * @throws SQLException If a database access error occurs.
     */
    public void addOrder(Order order) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        Statement statement2 = null;

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);

        String insertOrderSQL = "INSERT INTO Orders (purchaseDate, subtotal) " +
                "VALUES('" + strDate + "', " + order.getSubtotal() + ")";
        try {
            dbConnection = connectToDatabase();
            statement = dbConnection.createStatement();
            statement.executeUpdate(insertOrderSQL);
            System.out.println(insertOrderSQL);

            String getLatestOrderSQL = "SELECT MAX(orderID) FROM Orders";
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(getLatestOrderSQL);
            rs.next();
            Integer mostRecentOrderID = rs.getInt(1);

            for (Product p : order.getPurchasedProducts()) {
                String insertOrderItemSQL = "INSERT INTO OrderItems (orderID, productID) VALUES("
                        + mostRecentOrderID + ", " + p.getId() + ")";
                System.out.println(insertOrderItemSQL);
                statement.executeUpdate(insertOrderItemSQL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Gets all the orders in the hotel's database.
     * 
     * @return A collection of all orders in the database.
     * @throws SQLException If a database access error occurs.
     */
    public Collection<Order> getOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        Connection dbConnection = null;
        Statement statement = null;
        Statement statement2 = null;
        Statement statement3 = null;
        String getOrdersSQL = "SELECT * FROM Orders";
        try {
            dbConnection = connectToDatabase();
            statement = dbConnection.createStatement();
            statement2 = dbConnection.createStatement();
            statement3 = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(getOrdersSQL);
            System.out.println(getOrdersSQL);

            while(rs.next())  {
                String getOrderItemsSQL = "SELECT * FROM OrderItems WHERE orderID = " +  rs.getInt(1);
                ResultSet rs2 = statement2.executeQuery(getOrderItemsSQL);
                System.out.println(getOrderItemsSQL);
                List<Product> purchasedProducts = new ArrayList<>();
                while(rs2.next())  {
                    String getPurchasedProductsSQL = "SELECT * FROM Products WHERE productID = " + rs2.getInt(2);
                    ResultSet rs3 = statement3.executeQuery(getPurchasedProductsSQL);
                    System.out.println(getPurchasedProductsSQL);
                    if(rs3.next()) {
                        Product p = new Product(
                                rs3.getInt(1),
                                rs3.getString(2),
                                rs3.getDouble(3),
                                rs3.getString(4),
                                0
                        );
                        purchasedProducts.add(p);
                    }
                }
                Order order = new Order(
                        rs.getInt("orderID"),
                        rs.getString("purchaseDate"),
                        purchasedProducts);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (statement2 != null) {
                statement2.close();
            }
            if (statement3 != null) {
                statement3.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return orders;
    }

    /**
     * Gets the connection to the hotel's database.
     * 
     * @return The connection to the database.
     * @throws SQLException If a database access error occurs.
     */
    private static Connection connectToDatabase() throws SQLException {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            System.out.println("Connected");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
