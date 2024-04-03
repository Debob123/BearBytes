package bearbytes.dev.hotel.accounts;

import bearbytes.dev.hotel.floor.Room;
import bearbytes.dev.hotel.reservation.Reservation;

import java.util.Date;

public class Guest extends Account {

    private String name;
    private String address;
    private String cardNum;
    private String cardExp;
    public Guest(String username, String password, String name, String address, String cardNum, String cardExp) {
        super(username, password);
        this.name = name;
        this.address = address;
        this.cardNum = cardNum;
        this.cardExp = cardExp;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getCardExp() {
        return cardExp;
    }
}
