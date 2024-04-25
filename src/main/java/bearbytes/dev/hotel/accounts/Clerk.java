package bearbytes.dev.hotel.accounts;

/**
 * This class extends the Account class, so that specific
 * capabilities related to being a clerk can be given to this account.
 */
public class Clerk extends Account {
    /**
     * Default constructor for Clerk: sets a username and password.
     * 
     * @param username The value to set username to.
     * @param password The value to set password to.
     */
    public Clerk(String username, String password) {
        super(username, password);
    }
}
