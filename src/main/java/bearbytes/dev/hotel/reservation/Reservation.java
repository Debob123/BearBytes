package bearbytes.dev.hotel.reservation;

import bearbytes.dev.hotel.floor.Room;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The Reservation class holds all information needed for a customer to complete
 * a reservation with the hotel. This includes a start and end date, a fee, and
 * more.
 */
public class Reservation {
    /**
     * The Default Constructor for Reservation: sets a reservation id, a fee, the
     * dates, who it was reserved by, and the rooms that were reserved.
     *
     * @param reservationID The unique id of this reservation.
     * @param rooms         The rooms that make up the reservation.
     * @param startDate     The date the reservation starts on.
     * @param endDate       The date the reservation ends on.
     * @param username      The name of the user who the reservation is for.
     */
    public Reservation(@JsonProperty("reservationID") int reservationID, @JsonProperty("rooms") Collection<Room> rooms,
            @JsonProperty("startDate") String startDate, @JsonProperty("endDate") String endDate,
            @JsonProperty("username") String username) {
        this.rate = 0;
        this.rooms = new ArrayList<>(rooms);
        for (Room room : rooms) {
            rate += room.getDailyRate();
        }
        this.cancellationFee = rate * .8;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ReservationStatus.CONFIRMED;
        if (reservationID == 0) {
            this.reservationID = hashCode();
        } else {
            this.reservationID = reservationID;
        }
        this.username = username;
    }

    // The fee for cancelling the reservation.
    private double cancellationFee;

    // The price of the room per night.
    private double rate;

    // The unique id of this reservation.
    private int reservationID;

    // The list of rooms that have been reserved.
    private List<Room> rooms;

    // The starting date of the reservation.
    private String startDate;

    // The end date of the reservtion.
    private String endDate;

    // The name of the user who the reservation is for.
    private String username;

    // The completion status of the reservation.
    private ReservationStatus status;

    /**
     * Gets the fee for cancelling the reservation.
     *
     * @return The cancellation fee for the reservation.
     */
    public double getCancellationFee() {
        return cancellationFee;
    }

    /**
     * Gets the rate per night of the reservation.
     *
     * @return The price of the reservation per night.
     */
    public double getRate() {
        return rate;
    }

    /**
     * Gets the unique reservation id of this reservation.
     *
     * @return The id of this reservation.
     */
    public int getReservationID() {
        return reservationID;
    }

    /**
     * Gets the list of rooms in the reservation.
     *
     * @return The list of rooms in the reservation.
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Gets the starting date of the reservation.
     *
     * @return The start date of the reservation.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Gets the ending date of the reservation.
     *
     * @return The end date of the reservation.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Gets the username of the user who the reservation is for.
     *
     * @return The username of the user who the reservation is for.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the current completion status of the reservation.
     *
     * @return The current completion status of the reservation.
     */
    public ReservationStatus getReservationStatus() {
        return status;
    }

    /**
     * Sets the list of rooms in the reservation.
     *
     * @param rooms The new list of rooms that the reservation is for.
     */
    public void setRooms(Collection<Room> rooms) {
        this.rooms = new ArrayList<>(rooms);
        for (Room room : rooms) {
            rate += room.getDailyRate();
        }
        this.cancellationFee = rate * .8;
    }

    /**
     * The ReservationStatus enum contains all of the possible completion statuses
     * for a reservation.
     */
    public enum ReservationStatus {
        // Reservation statuses.
        CONFIRMED,
        CANCELLED,
        MODIFIED,
        CHECKED_IN,
        CHECKED_OUT;

        /**
         * Creates a String representation of the current reservation status.
         *
         * @param status The status to convert to a String.
         * @return A String representation of the current reservation status.
         */
        public static String toString(ReservationStatus status) {
            String result = null;
            switch (status) {
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

    /**
     * A hash code function for mapping reservations.
     *
     * @return A hashed integer representation of the reservation.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) cancellationFee;
        result = prime * result + (int) rate;
        result = prime * result + rooms.size();
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());

        return result;
    }

    /**
     * An equals function for checking for reservation equality
     *
     * @param o the object to compare this to for equality
     * @return A boolean whether the two reservations are equal by ID
     */
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if( o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Reservation r = (Reservation) o;
        return reservationID == r.reservationID;
    }

    /**
     * Creates a String representation of all of the data contained in the
     * reservation.
     *
     * @return A String representation of the current reservation.
     */
    public String toString() {
        StringBuilder result = new StringBuilder(
                reservationID + " " + startDate + " " + endDate + " " + username + " " + rate + " " + cancellationFee +
                        " " + ReservationStatus.toString(status) + " ");
        for (Room room : rooms) {
            result.append(room.toString()).append(" ");
        }

        return result.toString();
    }
}