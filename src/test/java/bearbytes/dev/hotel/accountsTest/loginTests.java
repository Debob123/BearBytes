package bearbytes.dev.hotel.accountsTest;

import bearbytes.dev.hotel.accounts.*;
import bearbytes.dev.hotel.controllers.AccountController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import bearbytes.dev.hotel.database.AccountAuthenticator;

public class loginTests {

    @Test
    public void testLogin() {
        Account account = new Account("username", "password");
        assertEquals("username", account.getUsername());
        assertEquals("password", account.getPassword());
    }

    @Test
    public void testGuest() {
        Guest guest = new Guest("username", "password", "name", "address", "cardNum", "cardExp");
        assertEquals("username", guest.getUsername());
        assertEquals("password", guest.getPassword());
        assertEquals("name", guest.getName());
        assertEquals("address", guest.getAddress());
        assertEquals("cardNum", guest.getCardNum());
        assertEquals("cardExp", guest.getCardExp());
    }

    @Test
    public void testClerk() {
        Clerk clerk = new Clerk("username", "password");
        assertEquals("username", clerk.getUsername());
        assertEquals("password", clerk.getPassword());
    }

    @Test
    public void testManager() {
        Manager manager = new Manager("username", "password");
        assertEquals("username", manager.getUsername());
        assertEquals("password", manager.getPassword());
    }

    @Test
    public void testAccountAuthenticator() throws Exception {
        Guest guest = new Guest("username", "password", "name", "address", "cardNum", "cardExp");
        Clerk clerk = new Clerk("username", "password");
        Manager manager = new Manager("username", "password");
        assertEquals(false, AccountAuthenticator.authGuest(guest));
        assertEquals(false, AccountAuthenticator.authClerk(clerk));
        assertEquals(true, AccountAuthenticator.authManager(manager));
    }

    @Test
    public void testAccountController() {
        AccountController accountController = new AccountController();
        Guest guest = new Guest("username", "password", "name", "address", "cardNum", "cardExp");
        Clerk clerk = new Clerk("username", "password");
        Manager manager = new Manager("username", "password");
        assertEquals(false, accountController.authGuest(guest));
        assertEquals(false, accountController.authClerk(clerk));
        assertEquals(true, accountController.authManager(manager));
    }

}
