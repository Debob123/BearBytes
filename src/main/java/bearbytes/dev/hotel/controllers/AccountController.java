package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.accounts.*;
import bearbytes.dev.hotel.database.AccountAuthenticator;
import bearbytes.dev.hotel.database.AccountDAO;
import bearbytes.dev.hotel.interfaces.IAccountDAO;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The AccountController class controls interactions when logging in on the
 * initial page.
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {
    // An instance of an account via a Data Access Object
    private IAccountDAO aDAO;

    // The Default Constructor for AccountController: creates a AccountDAO instance.
    public AccountController() {
        aDAO = new AccountDAO();
    }

    /**
     * Authenticates a guest login attempt.
     * 
     * @param g The guest credentials to check.
     * @return True if login successful, else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/guestlogin")
    public Boolean authGuest(@RequestBody Guest g) {
        try {
            return AccountAuthenticator.authGuest(g);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Whoops");
        }

        return false;
    }

    /**
     * Authenticates a clerk login attempt.
     * 
     * @param c The clerk credentials to check.
     * @return True if login successful, else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/clerklogin")
    public Boolean authClerk(@RequestBody Clerk c) {
        try {
            return AccountAuthenticator.authClerk(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Whoops");
        }

        return false;
    }

    /**
     * Authenticate a manager login attempt.
     * 
     * @param m The manager credentials to check.
     * @return True if login successful, else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/managerlogin")
    public Boolean authManager(@RequestBody Manager m) {
        try {
            return AccountAuthenticator.authManager(m);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Whoops");
        }

        return false;
    }

    /**
     * Creates a guest account with the given credentials.
     * 
     * @param g The guest account to create.
     * @return True if an accoutn was successfully created and added to the hotel,
     *         else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/createGuest")
    public Boolean createGuest(@RequestBody Guest g) {
        try {
            return aDAO.addGuest(g);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Whoops");
        }

        return false;
    }

    /**
     * Creates a clerk account with the given credentials.
     * 
     * @param cl The clerk account to create.
     * @return True if an account was successfully created and linked to the hotel,
     *         else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/createClerk")
    public Boolean createClerk(@RequestBody Clerk cl) {
        try {
            return aDAO.addClerk(cl);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Whoops");
        }

        return false;
    }

    /**
     * Creates a manager account with the given credentials.
     * 
     * @param m The manager accout to create.
     * @return True if an account was successfully created and linked to the hotel,
     *         else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/createManager")
    public Boolean createManager(@RequestBody Manager m) {
        try {
            return aDAO.addManager(m);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Whoops");
        }

        return false;
    }

    /**
     * Changes the password for an account.
     * 
     * @param acc The account to change the password of.
     * @param p   The new password value.
     * @return True if a new password was successfully created and linked to the
     *         account, else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/changePassword")
    public Boolean changePassword(@RequestBody Account acc, String p) {
        try {
            return aDAO.changePassword(acc, p);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Whoops");
        }

        return false;
    }

    /**
     * Checks to see if a password matches the password requirements.
     * 
     * @param password The password to check.
     * @return True if the password is valid, else false.
     */
    public static boolean goodPassword(String password) {
        // 8 or more characters
        if (password.length() < 8) {
            return false;
        }
        // first character is a letter
        if (!Character.isLetter(password.charAt(0))) {
            return false;
        }
        // has no spaces
        if (password.contains(" ")) {
            return false;
        }
        // contains a digit and a special character
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);
        // returns if it has digit and special
        return hasDigit.find() && hasSpecial.find();
    }
}
