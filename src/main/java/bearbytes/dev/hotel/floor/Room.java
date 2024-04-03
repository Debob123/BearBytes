package bearbytes.dev.hotel.floor;

public class Room {
    private int number;
    private int numBeds;
    private int floor;
    private double dailyRate;
    private boolean smokingAllowed;
    private BedType bedSize;
    private RoomType type;
    private QualityLevel quality;

    public Room() {
        this.number = 0;
        this.numBeds = 0;
        dailyRate = 0;
        floor = 0;
        smokingAllowed = false;
        bedSize = BedType.TWIN;
        type = RoomType.SINGLE;
        quality = QualityLevel.ECONOMY;
    }

    public Room(int roomNumber, int numBeds, int floor, double dailyRate, boolean smokingAllowed,
                BedType bedSize, RoomType type, QualityLevel quality) {
        System.out.println("Invoking Room constructor");
        System.out.println(roomNumber + " " + numBeds + " " + floor + " " + dailyRate + " " + smokingAllowed + " " +
                           bedSize + " "  + type + " " + quality);
        this.number = roomNumber;
        this.numBeds = numBeds;
        this.floor = floor;
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

    public int getFloor() {
        return floor;
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

    public void setFloor(int floor) {
        this.floor = floor;
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

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if( o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Room r = (Room) o;
        return (this.number == r.number && this.numBeds == r.numBeds && this.dailyRate == r.dailyRate
                && this.smokingAllowed == r.smokingAllowed && this.bedSize == r.bedSize && this.type == r.type
                && this.quality == r.quality);
    }

    public enum BedType {
        TWIN,
        FULL,
        QUEEN,
        KING;
        public static String toString(BedType type) {
            return switch (type) {
                case TWIN -> "TWIN";
                case FULL -> "FULL";
                case QUEEN -> "QUEEN";
                case KING -> "KING";
            };
        }

        // for database, converting string from database to enum
        public static BedType getEnum(String s) {
            return switch(s) {
                case "TWIN" -> TWIN;
                case "FULL" -> FULL;
                case "QUEEN" -> QUEEN;
                case "KING" -> KING;
                default -> null;
            };
        }
    }
    public enum RoomType {
        SINGLE,
        DOUBLE,
        FAMILY,
        SUITE,
        DELUXE,
        STANDARD;
        public static String toString(RoomType type) {
            return switch (type) {
                case SINGLE -> "SINGLE";
                case DOUBLE -> "DOUBLE";
                case FAMILY -> "FAMILY";
                case SUITE -> "SUITE";
                case DELUXE -> "DELUXE";
                case STANDARD -> "STANDARD";
            };
        }

        // for database, converting string from database to enum
        public static RoomType getEnum(String s) {
            return switch (s) {
                case "SINGLE" -> SINGLE;
                case "DOUBLE" -> DOUBLE;
                case "FAMILY" -> FAMILY;
                case "SUITE" -> SUITE;
                case "DELUXE" -> DELUXE;
                case "STANDARD" -> STANDARD;
                default -> null;
            };
        }
    }
    public enum QualityLevel {
        EXECUTIVE,
        BUSINESS,
        COMFORT,
        ECONOMY;
        // for database, converting enum to store as string
        public static String toString(QualityLevel q) {
            return switch (q) {
                case EXECUTIVE -> "EXECUTIVE";
                case BUSINESS -> "BUSINESS";
                case COMFORT -> "COMFORT";
                case ECONOMY -> "ECONOMY";
            };
        }

        // for database, converting string from database to enum
        public static QualityLevel getEnum(String s) {
            return switch(s) {
                case "EXECUTIVE" -> EXECUTIVE;
                case "BUSINESS" -> BUSINESS;
                case "COMFORT" -> COMFORT;
                case "ECONOMY" -> ECONOMY;
                default -> null;
            };
        }
    }
}
