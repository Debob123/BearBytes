package bearbytes.dev.hotel.floor;

/**
 * The Room class contains all information about an individual room in the
 * hotel.
 */
public class Room {
    // The room number of the room.
    private int number;

    // The number of beds contained in the room.
    private int numBeds;

    // The floor this room is on.
    private int floor;

    // The price of staying in this room for one night.
    private double dailyRate;

    // Whether or not smoking is allowed in this room.
    private boolean smokingAllowed;

    // The type of beds in this room.
    private BedType bedSize;

    // The type of room this room is.
    private RoomType type;

    // The quality level of this room.
    private QualityLevel quality;

    /**
     * The Default Constructor for a Room: sets all information about a room to
     * default values.
     */
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

    /**
     * Alternate Constructor for a Room: sets all information of a room to the given
     * values.
     * 
     * @param roomNumber     The number of the room in the hotel.
     * @param numBeds        The number of beds in the room.
     * @param floor          The floor the room is on.
     * @param dailyRate      The price of the room per night.
     * @param smokingAllowed Whether or not smoking is allowed.
     * @param bedSize        The size of the beds in the room.
     * @param type           The type of room this room is.
     * @param quality        The quality level of the room.
     */
    public Room(int roomNumber, int numBeds, int floor, double dailyRate, boolean smokingAllowed,
            BedType bedSize, RoomType type, QualityLevel quality) {
        this.number = roomNumber;
        this.numBeds = numBeds;
        this.floor = floor;
        this.dailyRate = dailyRate;
        this.smokingAllowed = smokingAllowed;
        this.bedSize = bedSize;
        this.type = type;
        this.quality = quality;
    }

    /**
     * Gets the room number for this room.
     * 
     * @return The number of this room.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets the number of beds in the room.
     * 
     * @return The number of beds in the room.
     */
    public int getNumBeds() {
        return numBeds;
    }

    /**
     * Gets the floor this room is on.
     * 
     * @return The floor this room is located on.
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Gets the daily price of this room.
     * 
     * @return The price for living in the room for one day.
     */
    public double getDailyRate() {
        return dailyRate;
    }

    /**
     * Gets the size of the beds in this room.
     * 
     * @return The type of beds this room has.
     */
    public BedType getBedSize() {
        return bedSize;
    }

    /**
     * The type of room this room is.
     * 
     * @return The room type of the room.
     */
    public RoomType getType() {
        return type;
    }

    /**
     * Gets the quality level of this room.
     * 
     * @return The quality level of the room.
     */
    public QualityLevel getQuality() {
        return quality;
    }

    /**
     * Returns whether or not smoking is allowed.
     * 
     * @return True if smoking is allowed, else false.
     */
    public boolean getSmokingAllowed() {
        return smokingAllowed;
    }

    /**
     * Sets the room number of this room.
     * 
     * @param roomNumber The new number of this room.
     */
    public void setNumber(int roomNumber) {
        number = roomNumber;
    }

    /**
     * Sets the floor number of this room.
     * 
     * @param floor The new floor this room is on.
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * Sets the daily rate of the room.
     * 
     * @param dailyRate The new price of staying in the room for one night.
     */
    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    /**
     * Sets the number of beds in the room.
     * 
     * @param numBeds The new number of beds in the room.
     */
    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    /**
     * Sets the size of the beds in the room.
     * 
     * @param bedSize The new size of the beds in the room.
     */
    public void setBedSize(BedType bedSize) {
        this.bedSize = bedSize;
    }

    /**
     * Sets the room type of this room.
     * 
     * @param type The new type of this room.
     */
    public void setRoomType(RoomType type) {
        this.type = type;
    }

    /**
     * Sets the quality of the room.
     * 
     * @param quality The new quality level of this room.
     */
    public void setQuality(QualityLevel quality) {
        this.quality = quality;
    }

    /**
     * Sets whether or not smoking is allowed.
     * 
     * @param smokingAllowed Whether or not smoking will now be allowed in this
     *                       room.
     */
    public void setSmokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }

    /**
     * Places all the information of this room into a string.
     * 
     * @return A string consisting of all the information of this room.
     */
    public String toString() {
        return number + ", " + floor + ", " + numBeds + ", " + dailyRate + ", " +
                smokingAllowed + ", " + bedSize.toString() + ", " + type.toString() +
                ", " + quality.toString();
    }

    /**
     * Determines if this room is the same as another room.
     * 
     * @param o The object to compare this room to.
     * @return True if the other object is an equivalent room, else false.
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Room r = (Room) o;
        return (this.number == r.number && this.numBeds == r.numBeds && this.dailyRate == r.dailyRate
                && this.smokingAllowed == r.smokingAllowed && this.bedSize == r.bedSize && this.type == r.type
                && this.quality == r.quality);
    }

    /**
     * The BedType enum contains all the possible types of beds that a room can
     * have.
     */
    public enum BedType {
        // The types of rooms.
        TWIN,
        FULL,
        QUEEN,
        KING;

        /**
         * Gets a String of the bed type.
         * 
         * @param type The new type of bed.
         * @return A string of the new bed type.
         */
        public static String toString(BedType type) {
            return switch (type) {
                case TWIN -> "TWIN";
                case FULL -> "FULL";
                case QUEEN -> "QUEEN";
                case KING -> "KING";
            };
        }

        /**
         * Converts a string from the database to an enum value.
         * 
         * @param s The string to convert.
         * @return The bed type "s" represents.
         */
        public static BedType getEnum(String s) {
            return switch (s) {
                case "TWIN" -> TWIN;
                case "FULL" -> FULL;
                case "QUEEN" -> QUEEN;
                case "KING" -> KING;
                default -> null;
            };
        }
    }

    // The RoomType enum contains all of the possible types a room can be.
    public enum RoomType {
        // The types of rooms.
        SINGLE,
        DOUBLE,
        FAMILY,
        SUITE,
        DELUXE,
        STANDARD;

        /**
         * Converts the type of room into a string.
         * 
         * @param type The new type of room.
         * @return A String representation of the RoomType.
         */
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

        /**
         * Converts a string from the database into its enum value.
         * 
         * @param s The string to convert.
         * @return The RoomType "s" represents.
         */
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

    /**
     * The QualityLevel enum contains all the different levels of quality a room can
     * be.
     */
    public enum QualityLevel {
        // All of the quality levels.
        EXECUTIVE,
        BUSINESS,
        COMFORT,
        ECONOMY;

        // for database, converting enum to store as string
        /**
         * Converts enum value to String to be stored by the database.
         * 
         * @param q The QualityLevel to convert to a String.
         * @return A String representation of "q".
         */
        public static String toString(QualityLevel q) {
            return switch (q) {
                case EXECUTIVE -> "EXECUTIVE";
                case BUSINESS -> "BUSINESS";
                case COMFORT -> "COMFORT";
                case ECONOMY -> "ECONOMY";
            };
        }

        // for database, converting string from database to enum
        /**
         * Converts the String from the database to an enum value.
         * 
         * @param s The String value to convert.
         * @return The QualityLevel of "s".
         */
        public static QualityLevel getEnum(String s) {
            return switch (s) {
                case "EXECUTIVE" -> EXECUTIVE;
                case "BUSINESS" -> BUSINESS;
                case "COMFORT" -> COMFORT;
                case "ECONOMY" -> ECONOMY;
                default -> null;
            };
        }
    }
}
