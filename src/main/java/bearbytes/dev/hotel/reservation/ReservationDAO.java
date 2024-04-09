package bearbytes.dev.hotel.reservation;

import bearbytes.dev.hotel.interfaces.InterfaceDAO;

import java.io.*;
import java.nio.file.Paths;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Properties;

public class ReservationDAO implements InterfaceDAO<Reservation> {
    private SimpleDateFormat simpleDateFormat;

    public ReservationDAO() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public boolean checkAvailability(int roomNumber, String start, String end) throws ClassNotFoundException, SQLException {
        // Class.forName(xxx) loads the jdbc classes and
        // creates a drivermanager class factory
        Class.forName(dbClassName);

        // Properties for user and password. Here the user
        // is root and the password is password
        Properties p = new Properties();
        p.put("user", "root");
        p.put("password", "password");

        // Now try to connect
        Connection c = null;
        try {
            c = DriverManager.getConnection(CONNECTION, p);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM myDB.Reservations");
            while (rs.next()) {
                int id = rs.getInt("reservationID");
                String name = rs.getString("username");
                System.out.println(id + " " + name);
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

    
    public boolean add(Reservation reservation) throws SQLException, ClassNotFoundException, ParseException {
        // Class.forName(xxx) loads the jdbc classes and
        // creates a drivermanager class factory
        Class.forName(dbClassName);

        // Properties for user and password. Here the user
        // is root and the password is password
        Properties p = new Properties();
        p.put("user", "root");
        p.put("password", "password");

        // Now try to connect
        Connection c = null;
        try {
            c = DriverManager.getConnection(CONNECTION, p);
            // Prepare the query's
            String query = "INSERT INTO myDB.Reservations(reservationID, username, start, end) values(?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(query);
            query = "INSERT INTO myDB.Bookings(bookingID, reservationID, roomNumber) values(?,?,?)";
            PreparedStatement ps2 = c.prepareStatement(query);
            java.sql.Date dbStart = new java.sql.Date(reservation.getStartDate().getTime());
            java.sql.Date dbEnd = new java.sql.Date(reservation.getEndDate().getTime());

            // Add the reservation
            ps.setInt(1, reservation.getReservationID());
            ps.setString(2, reservation.getUsername());
            ps.setDate(3, dbStart);
            ps.setDate(4, dbEnd);
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

    public boolean remove(Reservation r) {
        return true;
    }

    public Collection<Reservation> getAll() {
        return new ArrayList<Reservation>();
    }
}