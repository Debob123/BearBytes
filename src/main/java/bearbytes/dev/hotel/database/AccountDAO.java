package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.accounts.Account;
import bearbytes.dev.hotel.database.AccountAuthenticator;
import bearbytes.dev.hotel.accounts.Clerk;
import bearbytes.dev.hotel.accounts.Guest;
import bearbytes.dev.hotel.interfaces.IAccountDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AccountDAO implements IAccountDAO {
    AccountAuthenticator auth;
    public AccountDAO() {
        auth = new AccountAuthenticator();
    }

    public boolean add( Guest g) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        // Now try to connect
        try {
            c = getDBConnection();

            // Check if the username is already in use
            Boolean usernameTaken = auth.validateGuestUsername(g);

            // if the username is available, add the guest account
            if(!usernameTaken) {
                String query = "INSERT INTO APP.GuestAccounts(username, password) values(?,?)";
                ps = c.prepareStatement(query);
                query = "INSERT INTO APP.GuestInfo(name, address, username) values(?, ?, ?)";
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