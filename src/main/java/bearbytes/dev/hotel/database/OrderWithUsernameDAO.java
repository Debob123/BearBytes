package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.checkout.Order;
import bearbytes.dev.hotel.checkout.OrderWithUsername;
import bearbytes.dev.hotel.product.Product;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class OrderWithUsernameDAO {
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CONNECTION = "jdbc:derby:myDB";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public void addOrder(OrderWithUsername order) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        Statement statement2 = null;

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);

        String insertOrderSQL = "INSERT INTO Orders (purchaseDate, subtotal, username) " +
                "VALUES('" + strDate + "', " + order.getSubtotal() + ", '" + order.getUsername() + "')";
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

    public Collection<OrderWithUsername> getOrders(String username) throws SQLException  {
        username = username.substring(1, username.length() - 1);
        List<OrderWithUsername> orders = new ArrayList<>();
        Connection dbConnection = null;
        Statement statement = null;
        Statement statement2 = null;
        Statement statement3 = null;
        String getOrdersSQL = "SELECT * FROM Orders WHERE username = '" + username + "'";
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
                OrderWithUsername order = new OrderWithUsername(
                        rs.getInt("orderID"),
                        rs.getString("purchaseDate"),
                        purchasedProducts,
                        rs.getString("username")
                );
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
