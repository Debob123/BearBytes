package bearbytes.dev.hotel.accounts;

import bearbytes.dev.hotel.floor.Room;
import bearbytes.dev.hotel.reservation.Reservation;

import java.util.Date;

/**
 * The Guest class extends the Account class, so that specific capabilities
 * related to being a guest can be instilled on this account.
 */
public class Guest extends Account {

    // The name of the guest.
    private String name;

    // The address of the guest.
    private String address;

    // The credit card number of the guest.
    private String cardNum;

    // The credit card expiration date of the guest's credit card.
    private String cardExp;

    /**
     * Default constructor for Guest: takes a username, password, contact
     * information, shipping address, and payment information.
     * 
     * @param username The value to set the guest's username to.
     * @param password The value to set the guest's password to.
     * @param name     The value to set the guest's name to.
     * @param address  The value to set the guest's address to.
     * @param cardNum  The value to set the guest's credit card number to.
     * @param cardExp  The value to set the guest's credit card expiration date to.
     */
    public Guest(String username, String password, String name, String address, String cardNum, String cardExp) {
        super(username, password);
        this.name = name;
        this.address = address;
        this.cardNum = cardNum;
        this.cardExp = cardExp;
    }

    /**
     * Returns the guest's name.
     * 
     * @return The name of the guest.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the guest's address.
     * 
     * @return The address of the guest.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the guest's credit card number.
     * 
     * @return The credit card number of the guest.
     */
    public String getCardNum() {
        return cardNum;
    }

    /**
     * Returns the guest's credit card expiration date.
     * 
     * @return The expiration date of the guest's credit card.
     */
    public String getCardExp() {
        return cardExp;
    }
}
