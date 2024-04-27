package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.accounts.*;
import bearbytes.dev.hotel.database.AccountAuthenticator;
import bearbytes.dev.hotel.database.AccountDAO;
import bearbytes.dev.hotel.interfaces.IAccountDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
     * Changes the username of the given guest account to the new username.
     *
     * @param g           The guest account whose username is being changed.
     * @param newUsername The new username to assign to the guest's account.
     * @return True if the username was changed successfully, else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/changeGuestUsername")
    public Boolean changeGuestUsername(@RequestBody Guest g, @RequestParam("newUsername") String newUsername) {
        try {
            return aDAO.changeGuestUsername(g, newUsername);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Woops");
        }

        return false;
    }

    /**
     * Changes the password of the given guest account to the new password.
     *
     * @param g           The guest account whose password is being changed.
     * @param newPassword The new password to assign to the guest's account.
     * @return True if the password was successfully changed, else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/changeGuestPassword")
    public Boolean changeGuestPassword(@RequestBody Guest g, @RequestParam("newPassword") String newPassword) {
        try {
            return aDAO.changeGuestPassword(g, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Woops");
        }

        return false;
    }

    /**
     * Changes the username of the given clerk account to the new username.
     *
     * @param cl          The clerk account whose username is being changed.
     * @param newUsername The new username to assign to the clerk's account.
     * @return True if the username was successfully changed, else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/changeClerkUsername")
    public Boolean changeClerkUsername(@RequestBody Clerk cl, @RequestParam("newUsername") String newUsername) {
        System.out.println("Gets to changeClerkUsername - AccountController with u and cl.getUsername:");
        System.out.println(newUsername + " " + cl.getUsername());
        try {
            return aDAO.changeClerkUsername(cl, newUsername);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Woops");
        }

        return false;
    }

    /**
     * Changes the password of the given clerk account to the new password.
     *
     * @param cl          The clerk account whose password is being changed.
     * @param newPassword The new password to assign to the clerk's account.
     * @return True if the password was successfully changed, else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/changeClerkPassword")
    public Boolean changeClerkPassword(@RequestBody Clerk cl, @RequestParam("newPassword") String newPassword) {
        try {
            return aDAO.changeClerkPassword(cl, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Woops");
        }

        return false;
    }

    /**
     * Changes the username of the given manager account to the new username.
     *
     * @param m           The manager account whose username is being changed.
     * @param newUsername The new username to assign to the manager's account.
     * @return True if the username is changed successfully, else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/changeManagerUsername")
    public Boolean changeManagerUsername(@RequestBody Manager m, @RequestParam("newUsername") String newUsername) {
        try {
            return aDAO.changeManagerUsername(m, newUsername);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Woops");
        }

        return false;
    }

    /**
     * Changes the password of the manager account to the given password.
     *
     * @param m           The manager account whose password is being changed.
     * @param newPassword The new password to assign to the manager's account.
     * @return True if the password is successfully changed, else false.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/changeManagerPassword")
    public Boolean changeManagerPassword(@RequestBody Manager m, @RequestParam("newPassword") String newPassword) {
        try {
            return aDAO.changeManagerPassword(m, newPassword);
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
