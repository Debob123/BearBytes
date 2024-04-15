package bearbytes.dev.hotel.checkout;

import bearbytes.dev.hotel.product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
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
                String insertOrderItemSQL = "INSERT INTO OrderItems (order_id, product_id, name, price, image) " +
                        "VALUES(" + order.getOrderId() + ", " + p.getId() + ", '" + p.getName() + "', " + p.getPrice() +
                        ", '" + p.getImage() + "')";
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
        String getOrders = "SELECT * FROM Orders";
        try {
            dbConnection = connectToDatabase();
            statement = dbConnection.createStatement();
            statement2 = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(getOrders);
            System.out.println(getOrders);

            while(rs.next())  {
                String getOrderItemsSQL = "SELECT * FROM OrderItems WHERE order_id = " +  rs.getInt("order_id");
                ResultSet rs2 = statement2.executeQuery(getOrderItemsSQL);
                List<Product> purchasedProducts = new ArrayList<>();
                while(rs2.next())  {
                    Product p = new Product(
                            rs2.getInt("product_id"),
                            rs2.getString("name"),
                            rs2.getDouble("price"),
                            rs2.getString("image")
                    );
                    purchasedProducts.add(p);
                }
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getString("date"),
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
