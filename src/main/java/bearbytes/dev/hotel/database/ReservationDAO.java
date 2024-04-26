package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.exceptions.InvalidArgumentException;
import bearbytes.dev.hotel.floor.Room;
import bearbytes.dev.hotel.interfaces.IReservationDAO;
import bearbytes.dev.hotel.product.Product;
import bearbytes.dev.hotel.reservation.Reservation;

import java.sql.*;
import java.text.ParseException;
import java.util.*;

public class ReservationDAO implements IReservationDAO {

    public ReservationDAO() {}

    public Collection<Integer> checkAvailability(Reservation r) throws SQLException, InvalidArgumentException {
        if(r == null) {
            throw new InvalidArgumentException("The reservation cannot be null");
        }
        // Now try to connect
        List<Integer> rooms = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = getDBConnection();
            String query = "SELECT * FROM Reservations JOIN BOOKINGS ON " +
                    "Reservations.ReservationID = Bookings.ReservationID WHERE Bookings.roomNumber = ? " +
                    "AND Reservations.STARTDATE < ? AND Reservations.ENDDATE > ?";
            ps = c.prepareStatement(query);
            ps.setString(2, r.getEndDate());
            ps.setString(3, r.getStartDate());
            for(Room room : r.getRooms()) {
                ps.setInt(1, room.getNumber());
                rs = ps.executeQuery();
                if(rs.next() && rs.getInt("reservationID") != r.getReservationID()) {
                    rooms.add(room.getNumber());
                }
            }
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }

        return rooms;
    }

    
    public boolean add(Reservation reservation) throws SQLException, InvalidArgumentException {
        if(reservation == null || reservation.getUsername() == null || (reservation.getRooms() == null || reservation.getRooms().isEmpty())) {
            throw new InvalidArgumentException("The reservation cannot be null");
        }
        // Now try to connect
        Connection c = null;
        try {
            c = getDBConnection();
            // Prepare the query's
            String checkQuery = "SELECT * FROM Reservations WHERE ReservationID = ?";
            PreparedStatement stmt = c.prepareStatement(checkQuery);
            // Check if the reservation has already been added, if yes, stop execution
            stmt.setInt(1, reservation.getReservationID());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return false;
            }
            String query = "INSERT INTO Reservations(reservationID, username, startDate, endDate) values(?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(query);
            query = "INSERT INTO Bookings(bookingID, reservationID, roomNumber) values(?,?,?)";
            PreparedStatement ps2 = c.prepareStatement(query);
            String dbStart = reservation.getStartDate();
            String dbEnd = reservation.getEndDate();

            // Add the reservation
            ps.setInt(1, reservation.getReservationID());
            ps.setString(2, reservation.getUsername());
            ps.setString(3, dbStart);
            ps.setString(4, dbEnd);
            ps.executeUpdate();

            // Add the rooms to the reservation
            for(int i = 0; i < reservation.getRooms().size(); ++i) {
                ps2.setInt(1, reservation.getReservationID() + reservation.getRooms().get(i).getNumber());
                ps2.setInt(2, reservation.getReservationID());
                ps2.setInt(3, reservation.getRooms().get(i).getNumber());
                ps2.executeUpdate();
            }
            return true;
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }
        return false;
    }

    public Collection<Integer> modify(Reservation[] reservations) throws SQLException, InvalidArgumentException {
        List<Integer> rooms = (ArrayList)checkAvailability(reservations[1]);
        if(rooms.isEmpty()) {
            remove(reservations[0]);
            add(reservations[1]);
        }

        return rooms;
    }

    public boolean remove(Reservation r) throws SQLException, InvalidArgumentException {
        if(r == null) {
            throw new InvalidArgumentException("The reservation cannot be null");
        }
        Connection c = getDBConnection();
        PreparedStatement ps = null;

        try {
            // Apache Derby does not support deleting from multiple tables, so must use two statements
            // Delete from reservations table
            String query = "DELETE FROM APP.Reservations r WHERE r.reservationID = ?";
            ps = c.prepareStatement(query);
            ps.setInt(1, r.getReservationID());
            ps.executeUpdate();
            // Delete from Bookings table
            query = "DELETE FROM APP.Bookings b WHERE b.reservationID = ?";
            ps = c.prepareStatement(query);
            ps.setInt(1, r.getReservationID());
            ps.executeUpdate();
        } catch( SQLException e) {
            e.printStackTrace();
            return false;
        } finally{
            if(c != null) {
                c.close();
            }
            if(ps != null) {
                ps.close();
            }
        }

        return true;
    }

    public Collection<Reservation> getAll(String username) throws SQLException {
        Connection c = getDBConnection();
        PreparedStatement ps = null;
        List<Reservation> reservations = new ArrayList<>();
        if(username.startsWith("\"") && username.endsWith("\"") || username.startsWith("\'") && username.endsWith("\'")) {
            username = username.substring(1, username.length() - 1);
        }

        try {
            // Prepare the query's
            String query = "SELECT * FROM Reservations r JOIN Bookings b ON r.reservationID = b.reservationID " +
                    "JOIN Rooms rm ON rm.roomNumber = b.roomNumber WHERE r.username = ? ORDER BY b.reservationID DESC";
            ps = c.prepareStatement(query);

            // Retrieve the reservations
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            // Add the rooms to the reservation
            Map<Integer, List<Room>> resRooms = new HashMap<>();
            Integer prevResID;
            Integer reservationID = null;
            String prevStart;
            String prevEnd;
            boolean hasReservations = false;
            String start = null, end = null;
            while (rs.next()) {
                prevResID = reservationID;
                prevStart = start;
                prevEnd = end;
                reservationID = rs.getInt("reservationID");
                start = rs.getString("startDate");
                end = rs.getString("endDate");
                if(prevResID == null && prevStart == null && prevEnd == null) {
                    prevResID = reservationID;
                    prevStart = start;
                    prevEnd = end;
                    hasReservations = true;
                }
                // Get room information
                Integer roomNumber = rs.getInt("roomNumber");
                Integer floor = rs.getInt("floor");
                Integer numBeds = rs.getInt("numBeds");
                Double dailyRate = rs.getDouble("dailyRate");
                Boolean smokingAllowed = rs.getBoolean("smokingAllowed");
                String bedSize = rs.getString("bedSize");
                String type = rs.getString("type");
                String quality = rs.getString("quality");

                List<Room> rooms = resRooms.getOrDefault(reservationID, new ArrayList<Room>());
                rooms.add(new Room(roomNumber, floor, numBeds, dailyRate, smokingAllowed,
                        Room.BedType.getEnum(bedSize), Room.RoomType.getEnum(type), Room.QualityLevel.getEnum(quality)));
                resRooms.put(reservationID, rooms);

                if(!prevResID.equals(reservationID)) {
                    reservations.add(new Reservation(prevResID, resRooms.get(prevResID), prevStart, prevEnd, username));
                }
            }
            if(hasReservations) {
                reservations.add(new Reservation(reservationID, resRooms.get(reservationID), start, end, username));
            }
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
            if(ps != null) {
                ps.close();
            }
        }

        return reservations;
    }

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

    public static class ProductDAO  {
        private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
        private static final String DB_CONNECTION = "jdbc:derby:myDB";
        private static final String DB_USER = "";
        private static final String DB_PASSWORD = "";

        public Collection<Product> getProducts() throws SQLException  {
            Connection dbConnection = null;
            Statement statement = null;
            String getListedProductsSQL = "SELECT * FROM Products";
            List<Product> products = new ArrayList<Product>();
            try  {
                dbConnection = connectToDatabase();
                statement = dbConnection.createStatement();
                ResultSet rs = statement.executeQuery(getListedProductsSQL);
                while(rs.next())  {
                    Product p = new Product(
                            rs.getInt("productID"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("image")
                    );
                    products.add(p);
                }
            } catch(SQLException e)  {
                e.printStackTrace();
                return null;
            } finally  {
                if(statement != null)  {
                    statement.close();
                }
                if(dbConnection != null)  {
                    dbConnection.close();
                }
            }
            return products;
        }
        private static Connection connectToDatabase () throws SQLException {
            Connection dbConnection = null;
            try {
                Class.forName(DB_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            try {
                dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                System.out.println("Connected");
                return dbConnection;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return dbConnection;
        }

    }
}