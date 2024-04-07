package bearbytes.dev.hotel.accounts;

import org.mindrot.jbcrypt.BCrypt;

import bearbytes.dev.hotel.interfaces.GenericDAO;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.*;
import java.util.Properties;

public class AccountAuthenticator implements GenericDAO {

    private Properties p;
    private Connection c;

    public AccountAuthenticator() {
        p = new Properties();
        p.put("user", "root");
        p.put("password", "password");
        c = null;
    }

    //hashes a password using BCrypt
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
     //checks a password against the hash using BCrypt
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public Boolean authGuestAccount(Account acc) throws ClassNotFoundException, SQLException {
        // Class.forName(xxx) loads the jdbc classes and
        // creates a drivermanager class factory
        Class.forName(dbClassName);

        // Now try to connect
        try {
            c = DriverManager.getConnection(CONNECTION, p);
            // Add the reservation to the database
            String query = "SELECT * FROM myDB.GuestAccounts WHERE username LIKE ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, acc.getUsername());
            ResultSet rs = ps.executeQuery();
            while (rs.next() ) {
                // Check if the user is in the database and if the password is correct
                String username = rs.getString("username");
                String password = rs.getString("password");
                if(username.equals(acc.getUsername()) && password.equals(acc.getPassword())) {
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

    public Boolean validateGuestUsername(Account acc) throws ClassNotFoundException, SQLException {
        // Class.forName(xxx) loads the jdbc classes and
        // creates a drivermanager class factory
        Class.forName(dbClassName);

        PreparedStatement ps = null;
        // Now try to connect
        try {
            c = DriverManager.getConnection(CONNECTION, p);
            // Retrieve all accounts from
            String query = "SELECT * FROM myDB.GuestAccounts WHERE username LIKE ?";
            ps = c.prepareStatement(query);
            ps.setString(1, acc.getUsername());
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
}
