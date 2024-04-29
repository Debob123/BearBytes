package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The ProductDAO class provides data access operations for Products in the
 * hotel. This largely means getting a collection of all the products in the
 * hotel.
 */
public class ProductDAO {
    // The driver of the connected database.
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    // The connection string of the database.
    private static final String DB_CONNECTION = "jdbc:derby:myDB";

    // The user String for the database.
    private static final String DB_USER = "";

    // The password String for the database.
    private static final String DB_PASSWORD = "";

    /**
     * Gets all products in the hotel's database.
     *
     * @return A collection of all the products the hotel carries.
     * @throws SQLException If a database access error occurs.
     */
    public Collection<Product> getProducts() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String getListedProductsSQL = "SELECT * FROM Products";
        List<Product> products = new ArrayList<Product>();
        try {
            dbConnection = connectToDatabase();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(getListedProductsSQL);
            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("productID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("image"),
                        0);
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return products;
    }

    /**
     * Creates a connection to the hotel's database.
     *
     * @return A connection to the database.
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
