package floor;

import reservation.Reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Room {
    private int number;
    private int numBeds;
    private double dailyRate;
    private boolean smokingAllowed;
    private BedType bedSize;
    private RoomType type;
    private QualityLevel quality;

    private List<Reservation> reservations;

    public Room() {
        this.number = 0;
        this.numBeds = 0;
        dailyRate = 0;
        smokingAllowed = false;
        bedSize = BedType.TWIN;
        type = RoomType.SINGLE;
        quality = QualityLevel.ECONOMY;
    }

    public Room(int roomNumber, int numBeds, double dailyRate, boolean smokingAllowed,
                BedType bedSize, RoomType type, QualityLevel quality) {
        this.number = roomNumber;
        this.numBeds = numBeds;
        this.dailyRate = dailyRate;
        this.smokingAllowed = smokingAllowed;
        this.bedSize = bedSize;
        this.type = type;
        this.quality = quality;
        this.reservations = new ArrayList<>();
    }

    public int getNumber() { return number; }

    public int getNumBeds() { return numBeds; }

    public double getDailyRate() { return dailyRate; }

    public BedType getBedSize() { return bedSize; }

    public RoomType getType() { return type; }

    public QualityLevel getQuality() { return quality; }

    public boolean getSmokingAllowed() { return smokingAllowed; }

    public void setNumber(int roomNumber) { number = roomNumber; }

    public void setDailyRate(double dailyRate) { this.dailyRate = dailyRate; }

    public void setNumBeds(int numBeds) { this.numBeds = numBeds; }

    public void setBedSize(BedType bedSize) { this.bedSize = bedSize; }

    public void setRoomType(RoomType type) { this.type = type; }

    public void setQuality(QualityLevel quality) { this.quality = quality; }

    public void setSmokingAllowed(boolean smokingAllowed) { this.smokingAllowed = smokingAllowed; }

    public List<Reservation> getReservations() { return reservations; }

    public void addReservation(Reservation reservation) { reservations.add(reservation); }

    public boolean isAvailable(Date date) {
        // checks each reservation, sees is reserved on date
        // TODO: check for a range of days maybe?
        for (Reservation reservation : reservations) {
            if (date.after(reservation.getStartDate()) && date.before(reservation.getEndDate())) {
                return false;
            }
        }
        return true;
    }

    public enum BedType {TWIN, FULL, QUEEN, KING}
    public enum RoomType {SINGLE, DOUBLE, FAMILY, SUITE, DELUXE, STANDARD}
    public enum QualityLevel {EXECUTIVE, BUSINESS, COMFORT, ECONOMY}
}

