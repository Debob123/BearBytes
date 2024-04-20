package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.floor.Room;
import bearbytes.dev.hotel.interfaces.IReservationDAO;
import bearbytes.dev.hotel.product.Product;
import bearbytes.dev.hotel.reservation.Reservation;

import java.sql.*;
import java.text.ParseException;
import java.util.*;

public class ReservationDAO implements IReservationDAO {

    public ReservationDAO() {}

    public boolean checkAvailability(int roomNumber, String start, String end) throws ClassNotFoundException, SQLException {
        // Now try to connect
        Connection c = null;
        try {
            c = getDBConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Reservations");
            while (rs.next()) {
                int id = rs.getInt("reservationID");
                String name = rs.getString("username");
            }
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }

        return true;
    }

    
    public boolean add(Reservation reservation) throws SQLException,  ParseException {
        // Now try to connect
        Connection c = null;
        try {
            c = getDBConnection();
            // Prepare the query's
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

    public boolean remove(Reservation r) throws SQLException {
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
        username = username.substring(1, username.length() - 1);

        try {
            // Prepare the query's
            String query = "SELECT * FROM Reservations r JOIN Bookings b ON r.reservationID = b.reservationID " +
                    "JOIN Rooms rm ON rm.roomNumber = b.roomNumber WHERE r.username = ?";
            ps = c.prepareStatement(query);

            // Retrieve the reservations
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            // Add the rooms to the reservation
            Map<Integer, List<Room>> resRooms = new HashMap<>();
            Integer prevResID;
            Integer reservationID = null;
            boolean hasReservations = false;
            String start = null, end = null;
            while (rs.next()) {
                prevResID = reservationID;
                reservationID = rs.getInt("reservationID");
                if(prevResID == null) {
                    prevResID = reservationID;
                    hasReservations = true;
                }
                start = rs.getString("startDate");
                end = rs.getString("endDate");
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
                    reservations.add(new Reservation(resRooms.get(prevResID), start, end, username));
                }
            }
            if(hasReservations) {
                reservations.add(new Reservation(resRooms.get(reservationID), start, end, username));
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