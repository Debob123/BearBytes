package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.controllers.AccountController;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AccountControllerTests {
    // tests if passing passwords actually passes
    @ParameterizedTest
    @ValueSource(strings = {"ThisIsAGood1!", "also_0_a_0_good_password", "Z234567!"})
    void passingPasswords(String password) {
        assert(AccountController.goodPassword(password));
    }
    // tests if failing passwords actually fails
    @ParameterizedTest
    @ValueSource(strings = {"1badpassword!","short1!",
            "_badpassword1","hasNoNumber!","hasNoSpecial1","has a space1!"})
    void failingPasswords(String password) {
        assert(!AccountController.goodPassword(password));
    }
}
