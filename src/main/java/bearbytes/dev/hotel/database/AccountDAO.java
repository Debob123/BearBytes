package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.accounts.*;
import bearbytes.dev.hotel.interfaces.IAccountDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The AccountDAO class provides data access operations for accounts in the
 * hotel. It interacts with the database to add guests, changing passwords, and
 * more.
 */
public class AccountDAO implements IAccountDAO {

    /**
     * Adds a guest to the database.
     * 
     * @param g The guest to add.
     * @return True if the account was successfully added, else false.
     * @throws SQLException If a database access error occurs.
     */
    public boolean addGuest(Guest g) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        // Now try to connect
        try {
            c = getDBConnection();

            // Check if the username is already in use
            Boolean usernameTaken = AccountAuthenticator.validateGuestUsername(g);

            // if the username is available, add the guest account
            if (!usernameTaken) {
                String query = "INSERT INTO APP.GuestAccounts(username, password) values(?,?)";
                ps = c.prepareStatement(query);
                // Execute GuestAccounts table
                ps.setString(1, g.getUsername());
                ps.setString(2, AccountAuthenticator.hashPassword(g.getPassword()));
                ps.executeUpdate();

                query = "INSERT INTO APP.GuestInfo(name, address, username) values(?, ?, ?)";
                ps2 = c.prepareStatement(query);
                // update GuestInfo table
                ps2.setString(1, g.getName());
                ps2.setString(2, g.getAddress());
                ps2.setString(3, g.getUsername());
                ps2.executeUpdate();

                // indicate the creation succeeded
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close any opened connection or statements
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (ps2 != null) {
                ps2.close();
            }
        }

        // indicate creation failed
        return false;
    }

    /**
     * Adds a clerk to the database.
     * 
     * @param cl The glerk to add.
     * @return True if the account was successfully added, else false.
     * @throws SQLException If a database access error occurs.
     */
    public boolean addClerk(Clerk cl) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        // Now try to connect
        try {
            c = getDBConnection();

            // Check if the username is already in use
            Boolean usernameTaken = AccountAuthenticator.validateClerkUsername(cl);

            // if the username is available, add the guest account
            if (!usernameTaken) {
                String query = "INSERT INTO APP.ClerkAccounts(username, password) values(?,?)";
                ps = c.prepareStatement(query);
                // Execute GuestAccounts table
                ps.setString(1, cl.getUsername());
                ps.setString(2, AccountAuthenticator.hashPassword(cl.getPassword()));
                ps.executeUpdate();

                // indicate the creation succeeded
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close any opened connection or statements
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        // indicate creation failed
        return false;
    }

    /**
     * Adds a manager to the database.
     * 
     * @param m The manager to add.
     * @return True if the account was successfully added, else false.
     * @throws SQLException If a database access error occurs.
     */
    public boolean addManager(Manager m) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        // Now try to connect
        try {
            c = getDBConnection();

            // Check if the username is already in use
            Boolean usernameTaken = AccountAuthenticator.validateManagerUsername(m);

            // if the username is available, add the guest account
            if (!usernameTaken) {
                String query = "INSERT INTO APP.ManagerAccounts(username, password) values(?,?)";
                ps = c.prepareStatement(query);
                // Execute GuestAccounts table
                ps.setString(1, m.getUsername());
                ps.setString(2, AccountAuthenticator.hashPassword(m.getPassword()));
                ps.executeUpdate();

                // indicate the creation succeeded
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close any opened connection or statements
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        // indicate creation failed
        return false;
    }

    /**
     * Changes the username of a guest to the given username.
     * 
     * @param g The guest account whose username is being changed.
     * @param u The new username to set for the guest's account.
     * @return True if the username is successfully changed, else false.
     */
    public boolean changeGuestUsername(Guest g, String u) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = getDBConnection();
            String tableName;
            Boolean usernameTaken = AccountAuthenticator.validateGuestUsername(new Guest(u, "", "", "", "", ""));
            if (usernameTaken) {
                System.out.println("Username taken");
            } else {
                System.out.println("Username available");
            }

            if (!usernameTaken) {
                String query = "UPDATE APP.GuestAccounts SET username = ? WHERE username = ?";
                ps = c.prepareStatement(query);
                ps.setString(1, u);
                ps.setString(2, g.getUsername());

                int rowsUpdated = ps.executeUpdate();

                // check if any rows were affected
                if (rowsUpdated > 0) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Changes the password of a guest account to the given password.
     * 
     * @param g The guest account whose password is being changed.
     * @param p The new password to change on the guest's account.
     * @return True if the password is successfully changed, else false.
     */
    public boolean changeGuestPassword(Guest g, String p) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = getDBConnection();

            String query = "UPDATE APP.GuestAccounts SET password = ? WHERE username = ?";
            ps = c.prepareStatement(query);
            ps.setString(1, AccountAuthenticator.hashPassword(p));
            ps.setString(2, g.getUsername());

            int rowsUpdated = ps.executeUpdate();

            // check if any rows were affected
            if (rowsUpdated > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Changes the username of a clerk account to the given username.
     * 
     * @param cl The clerk account whose username is being changed.
     * @param u  The new username to change on the clerk's account.
     * @return True if the username is changed successfully, else false.
     */
    public boolean changeClerkUsername(Clerk cl, String u) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = getDBConnection();
            String tableName;
            Boolean usernameTaken = AccountAuthenticator.validateClerkUsername(new Clerk(u, ""));
            if (usernameTaken) {
                System.out.println("Username taken");
            } else {
                System.out.println("Username available");
            }

            if (!usernameTaken) {
                String query = "UPDATE APP.ClerkAccounts SET username = ? WHERE username = ?";
                ps = c.prepareStatement(query);
                ps.setString(1, u);
                ps.setString(2, cl.getUsername());

                int rowsUpdated = ps.executeUpdate();

                // check if any rows were affected
                if (rowsUpdated > 0) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Changes the password of a clerk account to the given password.
     * 
     * @param cl The clerk account whose password is being changed.
     * @param p  The new password to change on the clerk's account.
     * @return True if the password is changed successfully, else false.
     */
    public boolean changeClerkPassword(Clerk cl, String p) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = getDBConnection();

            String query = "UPDATE APP.ClerkAccounts SET password = ? WHERE username = ?";
            ps = c.prepareStatement(query);
            ps.setString(1, AccountAuthenticator.hashPassword(p));
            ps.setString(2, cl.getUsername());

            int rowsUpdated = ps.executeUpdate();

            // check if any rows were affected
            if (rowsUpdated > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Changes the username of a manager account to the given username.
     * 
     * @param m The manager account whose username is being changed.
     * @param u The new username to assign to the manager's account.
     * @return True if the username is successfully changed, else false.
     */
    public boolean changeManagerUsername(Manager m, String u) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = getDBConnection();
            String tableName;
            Boolean usernameTaken = AccountAuthenticator.validateManagerUsername(new Manager(u, ""));
            if (usernameTaken) {
                System.out.println("Username taken");
            } else {
                System.out.println("Username available");
            }

            if (!usernameTaken) {
                String query = "UPDATE APP.ManagerAccounts SET username = ? WHERE username = ?";
                ps = c.prepareStatement(query);
                ps.setString(1, u);
                ps.setString(2, m.getUsername());

                int rowsUpdated = ps.executeUpdate();

                // check if any rows were affected
                if (rowsUpdated > 0) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Changes the password of a manager account to the given password.
     * 
     * @param m The manager account whose password is being changed.
     * @param p The new password to assign to the manager's account.
     * @return True if the password was successfully changed, else false.
     */
    public boolean changeManagerPassword(Manager m, String p) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = getDBConnection();

            String query = "UPDATE APP.ManagerAccounts SET password = ? WHERE username = ?";
            ps = c.prepareStatement(query);
            ps.setString(1, AccountAuthenticator.hashPassword(p));
            ps.setString(2, m.getUsername());

            int rowsUpdated = ps.executeUpdate();

            // check if any rows were affected
            if (rowsUpdated > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes an account from the system.
     * 
     * @param acc The account to remove.
     * @return True if the account is successfully removed, else false.
     */
    public boolean remove(Account acc) {
        return true;
    }

    /**
     * Gets all accounts in the database.
     * 
     * @return A collection of all accounts in the database.
     */
    public Collection<Account> getAll() {
        return new ArrayList<Account>();
    }

    /**
     * Verifies if an accoutn is valid.
     * 
     * @return True if the account is valid, else false.
     */
    public boolean verify() {
        return false;
    }

    /**
     * Checks the availability of a username.
     * 
     * @return True if the username is available, else false.
     */
    public boolean checkAvailability() {
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
