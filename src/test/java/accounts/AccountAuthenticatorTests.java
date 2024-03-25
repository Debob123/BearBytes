package accounts;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AccountAuthenticatorTests {

    // tests if strings passed to hash are correctly verified
    @ParameterizedTest
    @ValueSource(strings = {"password", "a", "z", "0", "ThisIsAStrongPassword900!"})
    void hashToVerify(String password) {
        String hashed = AccountAuthenticator.hashPassword(password);
        assert(AccountAuthenticator.verifyPassword(password, hashed));
    }
}