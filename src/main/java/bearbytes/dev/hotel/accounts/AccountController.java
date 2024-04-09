package bearbytes.dev.hotel.accounts;

import bearbytes.dev.hotel.interfaces.IAccountDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private AccountAuthenticator auth;
    private IAccountDAO aDAO;

    public AccountController()  {
        auth = new AccountAuthenticator();
        aDAO = new AccountDAO();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/guestlogin")
    public Boolean authAccount(@RequestBody Account acc) {
        try {
            return auth.authGuestAccount(acc);
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
            return aDAO.add(g);
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
