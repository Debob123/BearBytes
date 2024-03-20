package accounts;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AccountAuthenticatorTest {

    // tests if strings passed to hash are correctly verified
    @ParameterizedTest
    @ValueSource(strings = {"password", "a", "z", "0", "ThisIsAStrongPassword900!"})
    void hashToVerify(String password) {
        String hashed = AccountAuthenticator.hashPassword(password);
        assert(AccountAuthenticator.verifyPassword(password, hashed));
    }
    // tests if passing passwords actually passes
    @ParameterizedTest
    @ValueSource(strings = {"ThisIsAGood1!", "also_0_a_0_good_password", "Z234567!"})
    void passingPasswords(String password) {
        assert(AccountAuthenticator.goodPassword(password));
    }
    // tests if failing passwords actually fails
    @ParameterizedTest
    @ValueSource(strings = {"1badpassword!","short1!",
            "_badpassword1","hasNoNumber!","hasNoSpecial1","has a space1!"})
    void failingPasswords(String password) {
        assert(!AccountAuthenticator.goodPassword(password));
    }
}