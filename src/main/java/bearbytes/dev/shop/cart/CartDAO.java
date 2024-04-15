package bearbytes.dev.shop.cart;

import bearbytes.dev.hotel.product.Product;

import java.sql.*;

/*
    Probably not going to use, too inefficient to make api call
    after every Product is added to cart
 */

public class CartDAO {
    final static String DB_CONNECTION = "jdbc:mysql://localhost:3306/shopping";
    final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String DB_USER = "root";
    final static String DB_PASSWORD = "csi_baylor_jake26";

    public static void main(String[] args)  {

    }

    public void addToCart(Product product)  {
        Connection dbConnection = null;
        Statement statement = null;
        String getProductSQL = "SELECT * FROM Products WHERE " + product.getId() + " = " + "PRODUCT_ID";

        try  {
            dbConnection = getDbConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(getProductSQL);
        } catch(SQLException e)  {
            e.getMessage();
        }
    }

    public static Connection getDbConnection()  {
        Connection dbConnection = null;
        try  {
            Class.forName(DB_DRIVER);
            System.out.println("ok");
        } catch(ClassNotFoundException e)  {
            e.getMessage();
        }
        try  {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            System.out.println("okay");
            return dbConnection;
        } catch(SQLException e)  {
            e.getMessage();
        }

        return dbConnection;
    }
}
