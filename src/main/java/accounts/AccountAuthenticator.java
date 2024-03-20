package accounts;

import org.mindrot.jbcrypt.BCrypt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountAuthenticator {
    public AccountAuthenticator() {}

    // hashes a password using BCrypt
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    // checks a password against the hash using BCrypt
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
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
