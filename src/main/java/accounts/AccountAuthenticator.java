package accounts;

import org.mindrot.jbcrypt.BCrypt;

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
}
