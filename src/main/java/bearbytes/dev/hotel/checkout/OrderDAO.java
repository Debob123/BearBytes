package bearbytes.dev.hotel.checkout;

import bearbytes.dev.hotel.product.Product;

import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class OrderDAO {
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CONNECTION = "jdbc:derby:myDB";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public void addOrder(Order order) throws SQLException  {
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


            for(Product p : order.getPurchasedProducts())  {
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

    public Collection<Order> getOrders() throws SQLException  {
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
                String getOrderItemsSQL = "SELECT * FROM OrderItems WHERE orderID = " +  rs.getInt("orderID");
                ResultSet rs2 = statement2.executeQuery(getOrderItemsSQL);
                List<Product> purchasedProducts = new ArrayList<>();
                while(rs2.next())  {
                    String getPurchasedProductsSQL = "SELECT * FROM Products WHERE productID = " + rs2.getInt("productID");
                    ResultSet rs3 = statement3.executeQuery(getPurchasedProductsSQL);
                    Product p = new Product(
                            rs3.getInt("productID"),
                            rs3.getString("name"),
                            rs3.getDouble("price"),
                            rs3.getString("image")
                    );
                    purchasedProducts.add(p);
                }
                Order order = new Order(
                        rs.getInt("orderID"),
                        rs.getString("purchaseDate"),
                        purchasedProducts
                );
                orders.add(order);
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
        return orders;
    }

    private static Connection connectToDatabase () throws SQLException {
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
