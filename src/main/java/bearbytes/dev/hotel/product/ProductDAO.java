package bearbytes.dev.hotel.product;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.*;

public class ProductDAO  {
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CONNECTION = "jdbc:derby:myDB";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public Collection<Product> getProducts() throws SQLException  {
        Connection dbConnection = null;
        Statement statement = null;
        String getListedProductsSQL = "SELECT * FROM Products";
        List<Product> products = new ArrayList<Product>();
        try  {
            dbConnection = connectToDatabase();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(getListedProductsSQL);
            while(rs.next())  {
                Product p = new Product(
                        rs.getInt("productID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("image")
                );
                products.add(p);
            }
        } catch(SQLException e)  {
            e.printStackTrace();
            return null;
        } finally  {
            if(statement != null)  {
                statement.close();
            }
            if(dbConnection != null)  {
                dbConnection.close();
            }
        }
        return products;
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
