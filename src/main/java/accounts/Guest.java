package accounts;

import floor.Room;
import reservation.Reservation;

import java.util.Date;

public class Guest extends Account {
    public Guest(String username, String password) {
        super(username, password);
    }

    public void reserveRoom(Room room, Date startDate, Date endDate) {
        Reservation reservation = new Reservation(room, startDate, endDate, getUsername());
    }
}
