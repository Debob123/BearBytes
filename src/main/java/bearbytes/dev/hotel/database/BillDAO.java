package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.bill.Bill;
import bearbytes.dev.hotel.checkout.Order;
import bearbytes.dev.hotel.reservation.Reservation;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class BillDAO {
    // The driver of the connected database.
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    // The connection string of the database.
    private static final String DB_CONNECTION = "jdbc:derby:myDB";

    // The user String for the database.
    private static final String DB_USER = "";

    // The password String for the database.
    private static final String DB_PASSWORD = "";


    /**
     * Generates a bill for a guest.
     *
     * @return A collection of all orders in the database.
     * @throws SQLException If a database access error occurs.
     */
    public Bill generateBill(List<Reservation> reservations, List<Order> orders, String username, List<Reservation> cancelledReservations) throws SQLException {
        Random rand = new Random();
        username = username.substring(1, username.length() - 1);
        Bill bill = new Bill(reservations, orders, username, rand.nextInt(), cancelledReservations);

        Connection dbConnection = null;
        Statement statement = null;
        String generateBillSQL;

        if(hasBill(username))  {
            generateBillSQL = "UPDATE Bills SET billTotal = " + bill.getBillTotal() + " WHERE username = '" + bill.getUsername() + "'";
        }
        else {
            generateBillSQL = "INSERT INTO Bills (billTotal, username) VALUES(" + bill.getBillTotal() + ", '" + bill.getUsername() + "')";
        }
        try {
            dbConnection = connectToDatabase();
            statement = dbConnection.createStatement();

            statement.executeUpdate(generateBillSQL);
            System.out.println(generateBillSQL);
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
        return bill;
    }

    public Boolean hasBill(String username) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String getByUsernameSQL = "SELECT * FROM Bills WHERE username = '" + username + "'";
        try {
            dbConnection = connectToDatabase();
            statement = dbConnection.createStatement();

            ResultSet rs = statement.executeQuery(getByUsernameSQL);
            if(rs.next())  {
                return true;
            }
            System.out.println(getByUsernameSQL);
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
        return false;
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
