package bearbytes.dev.hotel.UseCases.Admin;

import bearbytes.dev.hotel.accounts.*;
import bearbytes.dev.hotel.controllers.AccountController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import bearbytes.dev.hotel.database.AccountAuthenticator;

/*
 * 
 * The admin user should be able to:
 * Log in to the system using a username and a password.
 * Create a hotel clerk account that contains a username and a default password.
 */

public class adminTests {
    @Test
    public void testLogin() {
        Account account = new Manager("username", "password");
        assertEquals("username", account.getUsername());
        assertEquals("password", account.getPassword());
    }

    @Test
    public void testCreateClerkAccount() {
        AccountController accountController = new AccountController();
        Manager manager = new Manager("username", "password");
        accountController.createManager(manager);
        manager.createClerkAccount("clerkUsername", "clerkPassword");
        assertEquals(true, accountController.authClerk(new Clerk("clerkUsername", "clerkPassword")));
    }

}