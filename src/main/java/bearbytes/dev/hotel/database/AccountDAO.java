package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.accounts.*;
import bearbytes.dev.hotel.interfaces.IAccountDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AccountDAO implements IAccountDAO {

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
            if(!usernameTaken) {
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
            if(!usernameTaken) {
                String query = "INSERT INTO APP.ClerkAccounts(username, password) values(?,?)";
                ps = c.prepareStatement(query);
                // Execute GuestAccounts table
                ps.setString(1, cl.getUsername());
                ps.setString(2, AccountAuthenticator.hashPassword(cl.getPassword()));
                ps.executeUpdate();

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
        }

        // indicate creation failed
        return false;
    }
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
            if(!usernameTaken) {
                String query = "INSERT INTO APP.ManagerAccounts(username, password) values(?,?)";
                ps = c.prepareStatement(query);
                // Execute GuestAccounts table
                ps.setString(1, m.getUsername());
                ps.setString(2, AccountAuthenticator.hashPassword(m.getPassword()));
                ps.executeUpdate();

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
        }

        // indicate creation failed
        return false;
    }
    public boolean changeGuestUsername(Guest g, String u) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = getDBConnection();
            String tableName;
            Boolean usernameTaken = AccountAuthenticator.validateGuestUsername(new Guest(u, "","","","",""));
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

    public boolean remove(Account acc) {
        return true;
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
