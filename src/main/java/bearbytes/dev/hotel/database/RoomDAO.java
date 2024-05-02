package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.floor.Room;
import bearbytes.dev.hotel.interfaces.IRoomDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * The RoomDAO class provides data access operations for Rooms in the hotel.
 * This includes adding, modifying and removing rooms from the hotel's system.
 */
public class RoomDAO implements IRoomDAO {

    // The Default Constructor for RoomDAO
    public RoomDAO() {
    }

    /**
     * Adds a room to the database if it doesn't already exist.
     * 
     * @param room The room to be added.
     * @return True if the room was successfully added, else false.
     * @throws SQLException If a database access error occurs.
     */
    public boolean add(Room room) throws SQLException {

        Connection c = null;

        try {
            c = getDBConnection();
            // Retrieve rooms from the database
            String query = "SELECT roomNumber FROM Rooms";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Check if the room number is taken
            while (rs.next()) {
                if (rs.getInt("roomNumber") == room.getNumber()) {
                    return false;
                }
            }

            // Add the room to the database
            String query2 = "INSERT INTO Rooms(roomNumber, floor, numBeds, dailyRate, " +
                    "smokingAllowed, bedSize, type, quality) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(query2);

            ps.setInt(1, room.getNumber());
            ps.setInt(2, room.getFloor());
            ps.setInt(3, room.getNumBeds());
            ps.setDouble(4, room.getDailyRate());
            ps.setBoolean(5, room.getSmokingAllowed());
            ps.setString(6, Room.BedType.toString(room.getBedSize()));
            ps.setString(7, Room.RoomType.toString(room.getType()));
            ps.setString(8, Room.QualityLevel.toString(room.getQuality()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return true;
    }

    /**
     * Modifies all rooms given in the database.
     * 
     * @param rooms A list of all rooms in the database.
     * @return True if the room was successfully modified, else false.
     * @throws ClassNotFoundException If a Room object was not found.
     * @throws SQLException           If a database access error occurs.
     */
    public boolean modify(Room[] rooms) throws ClassNotFoundException, SQLException {
        PreparedStatement ps = null;
        Connection c = null;

        // Now try to connect
        try {

            Collection<Room> r = getAll();

            // Check if the room to change is valid
            if (!r.contains(rooms[0])) {
                System.out.println(rooms[0]);
                System.out.println("Room not present in database");
                return false;
            }

            // Check if the room number is available to change to
            if (rooms[0].getNumber() != rooms[1].getNumber()) {
                for (Room room : r) {
                    if (room.getNumber() == rooms[1].getNumber()) {
                        System.out.println("This room number is already taken");
                        return false;
                    }
                }
            }

            c = getDBConnection();

            // Query for the rooms
            String query = "UPDATE APP.Rooms SET roomNumber = ?, floor = ?," +
                    " numBeds = ?, dailyRate = ?, smokingAllowed = ?, bedSize = ?," +
                    " type = ?, quality = ? WHERE roomNumber = ?";
            ps = c.prepareStatement(query);
            ps.setInt(1, rooms[1].getNumber());
            ps.setInt(2, rooms[1].getFloor());
            ps.setInt(3, rooms[1].getNumBeds());
            ps.setDouble(4, rooms[1].getDailyRate());
            ps.setBoolean(5, rooms[1].getSmokingAllowed());
            ps.setString(6, Room.BedType.toString(rooms[1].getBedSize()));
            ps.setString(7, Room.RoomType.toString(rooms[1].getType()));
            ps.setString(8, Room.QualityLevel.toString(rooms[1].getQuality()));
            ps.setInt(9, rooms[0].getNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }

        return true;
    }

    /**
     * Removes a room from the database.
     * 
     * @param r The room to remove.
     * @return True if the room was removed successfully, else false.
     */
    public boolean remove(Room r) {
        return true;
    }

    /**
     * Gets all the rooms in the hotel's database.
     * 
     * @return A collection of rooms that make up the hotel.
     * @throws ClassNotFoundException If a Room is not found.
     * @throws SQLException           If a database access error occurs.
     */
    public Collection<Room> getAll() throws ClassNotFoundException, SQLException {
        List<Room> rooms = new ArrayList<>();

        Connection c = null;
        try {
            c = getDBConnection();
            // Query for the rooms
            Statement stmt = c.createStatement();
            String query = "SELECT * FROM APP.Rooms";
            ResultSet rs = stmt.executeQuery(query);
            // Iterate over the returned rooms

            while (rs.next()) {
                Room r = extractRoom(rs);
                rooms.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return rooms;
    }

    /**
     * Gets all available rooms from the hotel's database.
     * 
     * @param dates The dates available rooms must be available for.
     * @return A collection of all rooms that are available over the given dates.
     * @throws SQLException If a database access occurs.
     */
    public Collection<Room> getAvailable(String[] dates) throws SQLException {
        List<Room> rooms = new ArrayList<>();

        Connection c = null;
        try {
            c = getDBConnection();
            // Query for the rooms
            Statement stmt = c.createStatement();
            String query = "SELECT * FROM APP.Rooms";
            ResultSet rs = stmt.executeQuery(query);
            // Iterate over the returned rooms

            while (rs.next()) {
                Room r = extractRoom(rs);
                rooms.add(r);
            }

            checkAvailability(rooms, dates);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return rooms;
    }

    /**
     * Checks the availability of all rooms over the given dates.
     * 
     * @param rooms The rooms to check for availability.
     * @param dates The time period the rooms are wanted to be available for.
     * @throws SQLException If a database access error occurs.
     */
    public void checkAvailability(List<Room> rooms, String[] dates) throws SQLException {
        Connection c = null;
        try {
            String wantedStart = dates[0];
            String wantedEnd = dates[1];
            c = getDBConnection();
            // Query for the rooms
            String query = "SELECT * FROM APP.Rooms JOIN APP.Bookings ON APP.Bookings.roomNumber = APP.Rooms.roomNumber"
                    + " JOIN APP.Reservations ON APP.Bookings.reservationID = APP.Reservations.reservationID " +
                    "WHERE  APP.Reservations.status != ?";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, "CANCELLED");
            ResultSet rs = stmt.executeQuery();
            // Iterate over the returned rooms

            while (rs.next()) {
                String startDate = rs.getString("startDate");
                String endDate = rs.getString("endDate");
                // Compare whether the start comes after the end of the end comes before the
                // start
                if (!((wantedStart.compareTo(endDate) >= 0) || (wantedEnd.compareTo(startDate) <= 0))) {
                    Room r = extractRoom(rs);
                    rooms.remove(r);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    /**
     * Extracts the details for a room from the result set.
     * 
     * @param rs The result set to extract the data out of.
     * @return The resulting room information found.
     * @throws SQLException If a database access error occurs.
     */
    private Room extractRoom(ResultSet rs) throws SQLException {
        int roomNum = rs.getInt("roomNumber");
        int numBeds = rs.getInt("numBeds");
        int floor = rs.getInt("floor");
        double dailyRate = rs.getDouble("dailyRate");
        boolean smokingAllowed = rs.getBoolean("smokingAllowed");
        Room.BedType bedSize = Room.BedType.valueOf(rs.getString("bedSize"));
        Room.RoomType roomType = Room.RoomType.valueOf(rs.getString("type"));
        Room.QualityLevel quality = Room.QualityLevel.valueOf(rs.getString("quality"));
        return new Room(roomNum, numBeds, floor, dailyRate, smokingAllowed, bedSize, roomType, quality);
    }

    @Override
    public String roomStatus(int roomNumber) throws SQLException {
        Connection c = null;
        try {
            c = getDBConnection();
            String query = "SELECT APP.Reservations.startDate, APP.Reservations.endDate FROM APP.Rooms JOIN APP.Bookings ON APP.Bookings.roomNumber = APP.Rooms.roomNumber"
                    + " JOIN APP.Reservations ON APP.Bookings.reservationID = APP.Reservations.reservationID "
                    + "WHERE APP.Rooms.roomNumber = ? AND APP.Reservations.status != ?";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1, roomNumber);
            stmt.setString(2, "CANCELLED");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String startDate = rs.getString("startDate");
                String endDate = rs.getString("endDate");
                return "Room is occupied from " + startDate + " to " + endDate;
            } else {
                return "Room is not occupied";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred while checking room status";
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    /**
     * Creates a connection to the hotel's database.
     * 
     * @return The connection to the database.
     */
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
