package accounts;

public class Manager extends Account {
    public Manager(String username, String password) {
        super(username, password);
    }

    public void createClerkAccount(String username, String password) {}
    public void resetAccountPassword(Account acc, String password) {}
}
