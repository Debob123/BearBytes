package bearbytes.dev.hotel.controllers;

import bearbytes.dev.hotel.accounts.*;
import bearbytes.dev.hotel.database.AccountAuthenticator;
import bearbytes.dev.hotel.database.AccountDAO;
import bearbytes.dev.hotel.interfaces.IAccountDAO;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private IAccountDAO aDAO;

    public AccountController()  {
        aDAO = new AccountDAO();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/guestlogin")
    public Boolean authGuest(@RequestBody Guest g) {
        try {
            return AccountAuthenticator.authGuest(g);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Woops");
        }

        return false;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/clerklogin")
    public Boolean authClerk(@RequestBody Clerk c) {
        try {
            return AccountAuthenticator.authClerk(c);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Woops");
        }

        return false;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/managerlogin")
    public Boolean authManager(@RequestBody Manager m) {
        try {
            return AccountAuthenticator.authManager(m);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Woops");
        }

        return false;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/createGuest")
    public Boolean createGuest(@RequestBody Guest g) {
        try {
            return aDAO.addGuest(g);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Woops");
        }

        return false;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/createClerk")
    public Boolean createClerk(@RequestBody Clerk cl) {
        try {
            return aDAO.addClerk(cl);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Woops");
        }

        return false;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/createManager")
    public Boolean createManager(@RequestBody Manager m) {
        try {
            return aDAO.addManager(m);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Woops");
        }

        return false;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/changePassword")
    public Boolean changePassword(@RequestBody Account acc, String p) {
        try {
            return aDAO.changePassword(acc, p);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Woops");
        }

        return false;
    }

    // checks if password meets requirements
    public static boolean goodPassword(String password) {
        // 8 or more characters
        if (password.length() < 8) { return false; }
        // first character is a letter
        if (!Character.isLetter(password.charAt(0))) { return false; }
        // has no spaces
        if (password.contains(" ")) { return false; }
        // contains a digit and a special character
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);
        // returns if it has digit and special
        return hasDigit.find() && hasSpecial.find();
    }
}
