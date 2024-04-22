package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.accounts.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Properties;

public class AccountAuthenticator {

    static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    static final String DB_CONNECTION = "jdbc:derby:myDB";
    static final String DB_USER = "";
    static final String DB_PASSWORD = "";

    public AccountAuthenticator() {}

    //hashes a password using BCrypt
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
     //checks a password against the hash using BCrypt
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public Boolean authGuest(Guest g) throws SQLException {
        Connection c = null;

        // Now try to connect
        try {
            c = getDBConnection();
            // Add the reservation to the database
            String query = "SELECT * FROM APP.GuestAccounts WHERE username = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, g.getUsername());
            ResultSet rs = ps.executeQuery();
            while (rs.next() ) {
                // Check if the user is in the database and if the password is correct
                String username = rs.getString("username");
                String password = rs.getString("password");
                if(username.equals(g.getUsername()) && password.equals(g.getPassword())) {
                    return true;
                }
            }
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }
        return false;
    }
    public Boolean authClerk(Clerk cl) throws SQLException {
        Connection c = null;

        // Now try to connect
        try {
            c = getDBConnection();
            // Add the reservation to the database
            String query = "SELECT * FROM APP.ClerkAccounts WHERE username = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, cl.getUsername());
            ResultSet rs = ps.executeQuery();
            while (rs.next() ) {
                // Check if the user is in the database and if the password is correct
                String username = rs.getString("username");
                String password = rs.getString("password");
                if(username.equals(cl.getUsername()) && password.equals(cl.getPassword())) {
                    return true;
                }
            }
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }
        return false;
    }
    public Boolean authManager(Manager m) throws SQLException {
        Connection c = null;

        // Now try to connect
        try {
            c = getDBConnection();
            // Add the reservation to the database
            String query = "SELECT * FROM APP.ManagerAccounts WHERE username = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, m.getUsername());
            ResultSet rs = ps.executeQuery();
            while (rs.next() ) {
                // Check if the user is in the database and if the password is correct
                String username = rs.getString("username");
                String password = rs.getString("password");
                if(username.equals(m.getUsername()) && password.equals(m.getPassword())) {
                    return true;
                }
            }
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }
        return false;
    }

    public Boolean validateGuestUsername(Guest g) throws SQLException {
        Connection c = null;

        PreparedStatement ps = null;
        // Now try to connect
        try {
            c = getDBConnection();
            // Retrieve all accounts from
            String query = "SELECT * FROM APP.GuestAccounts WHERE username = ?";
            ps = c.prepareStatement(query);
            ps.setString(1, g.getUsername());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }
        return false;
    }
    public Boolean validateClerkUsername(Clerk cl) throws SQLException {
        Connection c = null;

        PreparedStatement ps = null;
        // Now try to connect
        try {
            c = getDBConnection();
            // Retrieve all accounts from
            String query = "SELECT * FROM APP.ClerkAccounts WHERE username = ?";
            ps = c.prepareStatement(query);
            ps.setString(1, cl.getUsername());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }
        return false;
    }
    public Boolean validateManagerUsername(Manager m) throws SQLException {
        Connection c = null;

        PreparedStatement ps = null;
        // Now try to connect
        try {
            c = getDBConnection();
            // Retrieve all accounts from
            String query = "SELECT * FROM APP.ManagerAccounts WHERE username = ?";
            ps = c.prepareStatement(query);
            ps.setString(1, m.getUsername());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }
        return false;
    }

    private static Connection getDBConnection() {
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
}
