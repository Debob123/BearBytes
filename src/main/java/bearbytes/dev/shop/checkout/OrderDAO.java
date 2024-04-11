package bearbytes.dev.shop.checkout;

import bearbytes.dev.shop.product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/shopping";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "csi_baylor_jake26";

    public void addOrder(Order order) throws SQLException  {
        Connection dbConnection = null;
        Statement statement = null;
        String insertOrderSQL = "INSERT INTO Orders (order_id, date, subtotal) " +
                "VALUES(" + order.getOrderId() + ", '" + order.getPurchaseDate() + "', " + order.getSubtotal() + ")";
        try {
            dbConnection = connectToDatabase();
            statement = dbConnection.createStatement();
            statement.executeUpdate(insertOrderSQL);
            System.out.println(insertOrderSQL);

            for(Product p : order.getPurchasedProducts())  {
                String insertOrderItemSQL = "INSERT INTO OrderItems (order_id, name, price) " +
                        "VALUES(" + order.getOrderId() + ", '" + p.getName() + "', " + p.getPrice() + ")";
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

    public List<Order> getOrders(Integer id) throws SQLException  {
        List<Order> orders = new ArrayList<>();
        Connection dbConnection = null;
        Statement statement = null;
        String getOrderItemsSQL = "SELECT * FROM OrderItems";
        try {
            dbConnection = connectToDatabase();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(getOrderItemsSQL);
            System.out.println(getOrderItemsSQL);

            while(rs.next())  {
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getString("date"),
                        null,
                        rs.getDouble("subtotal")
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
