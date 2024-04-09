package bearbytes.dev.hotel.accounts;

import bearbytes.dev.hotel.interfaces.IAccountDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class AccountDAO implements IAccountDAO {
    private Properties p;
    private Connection c;
    AccountAuthenticator auth;
    public AccountDAO() {
        // Properties for user and password. Here the user
        // is root and the password is password
        p = new Properties();
        p.put("user", "root");
        p.put("password", "password");
        c = null;
        auth = new AccountAuthenticator();
    }

    public Boolean add( Guest g) throws SQLException, ClassNotFoundException {
        Class.forName(dbClassName);
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        // Now try to connect
        try {
            c = DriverManager.getConnection(CONNECTION, p);

            // Check if the username is already in use
            Boolean usernameTaken = auth.validateGuestUsername(g);

            // if the username is available, add the guest account
            if(!usernameTaken) {
                String query = "INSERT INTO myDB.GuestAccounts(username, password) values(?,?)";
                ps = c.prepareStatement(query);
                query = "INSERT INTO myDB.GuestInfo(name, address, username) values(?, ?, ?)";
                ps2 = c.prepareStatement(query);

                // Execute GuestAccounts table
                ps.setString(1, g.getUsername());
                ps.setString(2, g.getPassword());

                ps.executeUpdate();

                // update GuestInfo table
                ps2.setString(1, g.getName());
                ps2.setString(2, g.getAddress());
                ps2.setString(3, g.getUsername());
                ps2.executeUpdate();

                // indicate the creation succeeded
                return true;
            }
        } catch( SQLException e) {
            e.printStackTrace();
        } finally {
            // Close any opened connection or statements
            if (c != null) {
                c.close();
            }
            if(ps != null) {
                ps.close();
            }
            if(ps2 != null) {
                ps2.close();
            }
        }

        // indicate creation failed
        return false;
    }

    public boolean remove(Guest g) {
        return true;
    }

    public boolean add(Clerk c) {
        return false;
    }

    public Collection<Account> getAll() {
        return new ArrayList<Account>();
    }

    public boolean verify() {
        return false;
    }

    public boolean checkAvailability() {
        return false;
    }

    public boolean add(Account a) {
        return false;
    }

    public boolean remove(Account a) {
        return false;
    }
}
