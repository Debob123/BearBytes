package reservation;

import floor.Room;

import java.util.Date;

public class Reservation {
    private Room room;
    private Date startDate;
    private Date endDate;
    private String guestName;

    public Reservation(Room room, Date startDate, Date endDate, String guestName) {
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestName = guestName;
    }

    public Room getRoom() { return room; }

    public Date getStartDate() { return startDate; }

    public Date getEndDate() { return endDate; }

    public String getGuestName() { return guestName; }
}