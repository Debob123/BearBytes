package bearbytes.dev.hotel.accounts;

/**
 * The Manager class extends the Account class, so that specific capabilities
 * related to being a Manager can be instilled on this Account.
 */
public class Manager extends Account {
    /**
     * The default constructor for Manager: sets a username and password.
     * 
     * @param username The value to set username to.
     * @param password The value to set password to.
     */
    public Manager(String username, String password) {
        super(username, password);
    }

    /**
     * This method allows for a manager to create a clerk account.
     * 
     * @param username The value to set the clerk's username to.
     * @param password The value to set the clerk's password to.
     */
    public void createClerkAccount(String username, String password) {
    }

    /**
     * This method allows a Manager to reset the password on an account.
     * 
     * @param acc      The account to reset the password of.
     * @param password The new password to reset the password to.
     */
    public void resetAccountPassword(Account acc, String password) {
    }
}
