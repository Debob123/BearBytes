package floor;

public class Room {
    private int number;
    private int numBeds;
    private double dailyRate;
    private boolean smokingAllowed;
    private BedType bedSize;
    private RoomType type;
    private QualityLevel quality;

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
    }

    public int getNumber() {
        return number;
    }

    public int getNumBeds() {
        return numBeds;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public BedType getBedSize() {
        return bedSize;
    }

    public RoomType getType() {
        return type;
    }

    public QualityLevel getQuality() {
        return quality;
    }

    public boolean getSmokingAllowed() {
        return smokingAllowed;
    }

    public void setNumber(int roomNumber) {
        number = roomNumber;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    public void setBedSize(BedType bedSize) {
        this.bedSize = bedSize;
    }

    public void setRoomType(RoomType type) {
        this.type = type;
    }

    public void setQuality(QualityLevel quality) {
        this.quality = quality;
    }

    public void setSmokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }

    public enum BedType {TWIN, FULL, QUEEN, KING}
    public enum RoomType {SINGLE, DOUBLE, FAMILY, SUITE, DELUXE, STANDARD}
    public enum QualityLevel {EXECUTIVE, BUSINESS, COMFORT, ECONOMY}
}

