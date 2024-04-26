package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.accounts.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Properties;

/**
 * The AccountAuthenticator class performs all authentification needed for any
 * account.
 */
public class AccountAuthenticator {
    // The driver of the connected database.
    static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    // The connection string of the database.
    static final String DB_CONNECTION = "jdbc:derby:myDB";

    // The user String for the database.
    static final String DB_USER = "";

    // The password String for the database.
    static final String DB_PASSWORD = "";

    // The Default Constructor for AccountAuthenticator.
    public AccountAuthenticator() {
    }

    /**
     * Hashes a password using BCrypt
     * 
     * @param password The password to hash
     * @return The encrypted password.
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Checks a password against the hash using BCrypt.
     * 
     * @param password       The inputted password needing to be validated.
     * @param hashedPassword The correct password to compare to.
     * @return True if the password is correct, else false.
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    /**
     * Attempts to authenticate a guest login.
     * 
     * @param g The guest credentials to verify.
     * @return True if the guest's login attempt is valid, else false.
     * @throws SQLException If a database access error occurs.
     */
    public static Boolean authGuest(Guest g) throws SQLException {
        Connection c = null;

        // Now try to connect
        try {
            c = getDBConnection();
            // Add the reservation to the database
            String query = "SELECT * FROM APP.GuestAccounts WHERE username = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, g.getUsername());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Check if the user is in the database and if the password is correct
                String username = rs.getString("username");
                String password = rs.getString("password");
                if (username.equals(g.getUsername()) && verifyPassword(g.getPassword(), password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return false;
    }

    /**
     * Attemps to authenticate a clerk login.
     * 
     * @param cl The clerk credentials to verify.
     * @return True if the login attempt is successful, else false.
     * @throws SQLException If a database access error occurs.
     */
    public static Boolean authClerk(Clerk cl) throws SQLException {
        Connection c = null;

        // Now try to connect
        try {
            c = getDBConnection();
            // Add the reservation to the database
            String query = "SELECT * FROM APP.ClerkAccounts WHERE username = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, cl.getUsername());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Check if the user is in the database and if the password is correct
                String username = rs.getString("username");
                String password = rs.getString("password");
                if (username.equals(cl.getUsername()) && verifyPassword(cl.getPassword(), password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return false;
    }

    /**
     * Attemps to authenticate a manager login.
     * 
     * @param m The manager credentials to validate.
     * @return True if the login attempt is successful, else false.
     * @throws SQLException If a database access error occus.
     */
    public static Boolean authManager(Manager m) throws SQLException {
        Connection c = null;

        // Now try to connect
        try {
            c = getDBConnection();
            // Add the reservation to the database
            String query = "SELECT * FROM APP.ManagerAccounts WHERE username = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, m.getUsername());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Check if the user is in the database and if the password is correct
                String username = rs.getString("username");
                String password = rs.getString("password");
                if (username.equals(m.getUsername()) && verifyPassword(m.getPassword(), password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return false;
    }

    /**
     * Checks to see if a guest username is valid.
     * 
     * @param g The guest whose credentials are to be checked.
     * @return True if the username is valid, else false.
     * @throws SQLException If a database access error occurs.
     */
    public static Boolean validateGuestUsername(Guest g) throws SQLException {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return false;
    }

    /**
     * Checks to see if a clerk's username is valid.
     * 
     * @param cl The clerk whose credentials are to be checked.
     * @return True if the username is valid, else false.
     * @throws SQLException If a database access error occurs.
     */
    public static Boolean validateClerkUsername(Clerk cl) throws SQLException {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return false;
    }

    /**
     * Checks to see if a manager's username is valid.
     * 
     * @param m The manager whose credentials are to be checked.
     * @return True if the username if valid, else false.
     * @throws SQLException If a database access error occurs.
     */
    public static Boolean validateManagerUsername(Manager m) throws SQLException {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return false;
    }

    /**
     * Establishes a connection to the database.
     * 
     * @return The connection to the database.
     */
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
