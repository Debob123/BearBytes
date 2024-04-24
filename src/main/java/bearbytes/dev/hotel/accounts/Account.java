package bearbytes.dev.hotel.accounts;

/**
 * The Account class stores all detail needed for an account in order to be
 * able to log in (i.e. username and password).
 */
public class Account {
    /**
     * The username and password for the account.
     */
    private String username, password;

    /**
     * Default Constructor for Account: sets a username and password.
     * 
     * @param username The username for the account.
     * @param password The password for the account.
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the value of the username.
     * 
     * @return The account's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the value of the password.
     * 
     * @return The account's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the username.
     * 
     * @param username The new value to change username to.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the value of the password.
     * 
     * @param password The new value to change password to.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
