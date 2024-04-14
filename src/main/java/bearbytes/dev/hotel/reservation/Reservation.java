package bearbytes.dev.hotel.reservation;

import bearbytes.dev.hotel.floor.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Reservation {


    public Reservation(Collection<Room> rooms, String startDate, String endDate, String username) {
        this.rate = 0;
        this.rooms = new ArrayList<>(rooms);
        for(Room room : rooms) {
            rate += room.getDailyRate();
        }
        this.cancellationFee = rate * .8;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ReservationStatus.CONFIRMED;
        this.reservationID = hashCode();
        this.username = username;
    }
    private double cancellationFee;
    private double rate;
    private int reservationID;
    private List<Room> rooms;
    private String startDate;
    private String endDate;
    private String username;
    private ReservationStatus status;

    public double getCancellationFee() {
        return cancellationFee;
    }

    public double getRate() {
        return rate;
    }

    public int getReservationID() {
        return reservationID;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate()  {
        return endDate;
    }

    public String getUsername() {
        return username;
    }

    public ReservationStatus getReservationStatus() {
        return status;
    }

    public void setRooms(Collection<Room> rooms) {
        this.rooms = new ArrayList<>(rooms);
        for(Room room : rooms) {
            rate += room.getDailyRate();
        }
        this.cancellationFee = rate * .8;
    }

    public enum ReservationStatus {
        CONFIRMED,
        CANCELLED,
        MODIFIED,
        CHECKED_IN,
        CHECKED_OUT;

        public static String toString(ReservationStatus status) {
            String result = null;
            switch(status) {
                case CONFIRMED:
                    result = "CONFIRMED";
                    break;
                case CANCELLED:
                    result = "CANCELLEd";
                    break;
                case MODIFIED:
                    result = "MODIFIED";
                    break;
                case CHECKED_IN:
                    result = "CHECKED_IN";
                    break;
                case CHECKED_OUT:
                    result = "CHECKED_OUT";
                    break;
            }

            return result;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)cancellationFee;
        result = prime * result + (int)rate;
        result = prime * result + rooms.size();
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());

        return result;
    }
}