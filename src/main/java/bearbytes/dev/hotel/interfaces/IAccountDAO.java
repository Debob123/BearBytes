package bearbytes.dev.hotel.interfaces;

import bearbytes.dev.hotel.accounts.Account;

public interface IAccountDAO extends InterfaceDAO<Account> {
    boolean verify();

    boolean checkAvailability();
}
