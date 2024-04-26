package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.accounts.Guest;
import bearbytes.dev.hotel.controllers.ReservationController;
import bearbytes.dev.hotel.exceptions.InvalidArgumentException;
import bearbytes.dev.hotel.floor.Room;
import bearbytes.dev.hotel.reservation.Reservation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReservationTests {
    ReservationDAO res = new ReservationDAO();
    protected Reservation reservation1 = null;
    protected Reservation reservation2 = null;

    @BeforeAll
    public static void init() throws SQLException {
        AccountDAO acc = new AccountDAO();
        acc.addGuest(new Guest("testerGuest", "password", null, null, null, null));
        acc.addGuest(new Guest("testerGuest2", "password", null, null, null, null));
    }
    @AfterEach
    public void tearDown() {
        try {
            res.remove(reservation1);
            res.remove(reservation2);
        } catch(Exception e) {}
        reservation1 = null;
        reservation2 = null;
    }

    @Test
    public void addNullReservation() {
        Assertions.assertThrows(InvalidArgumentException.class, () -> res.add(null));
    }

    @Test
    public void addReservationOneRoom() throws InvalidArgumentException, SQLException {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, 1, 1, 175.25, true, Room.BedType.TWIN, Room.RoomType.SINGLE, Room.QualityLevel.ECONOMY));
        reservation1 = new Reservation(0, rooms, "2040-05-01", "2040-05-02", "testerGuest");
        res.add(reservation1);
        List<Reservation> reservations = (List<Reservation>) res.getAll("testerGuest");
        Assertions.assertEquals(reservation1, reservations.getFirst());
    }

    @Test
    public void addReservationMultipleRooms() throws InvalidArgumentException, SQLException {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, 1, 1, 175.25, true, Room.BedType.TWIN, Room.RoomType.SINGLE, Room.QualityLevel.ECONOMY));
        rooms.add(new Room(102, 1, 2, 200, true, Room.BedType.FULL, Room.RoomType.DOUBLE, Room.QualityLevel.ECONOMY));
        reservation1 = new Reservation(0, rooms, "2040-05-01", "2040-05-02", "testerGuest");
        res.add(reservation1);
        List<Reservation> reservations = (List<Reservation>) res.getAll("testerGuest");
        Assertions.assertEquals(reservation1, reservations.getFirst());
    }

    @Test
    public void addMultipleReservation() throws InvalidArgumentException, SQLException {
        List<Room> rooms = new ArrayList<>();
        List<Reservation> expectedReservations = new ArrayList<>();
        rooms.add(new Room(101, 1, 1, 175.25, true, Room.BedType.TWIN, Room.RoomType.SINGLE, Room.QualityLevel.ECONOMY));
        reservation1 = new Reservation(0, rooms, "2040-05-01", "2040-05-02", "testerGuest");
        expectedReservations.add(reservation1);
        res.add(reservation1);
        rooms = new ArrayList<>();
        rooms.add(new Room(102, 1, 2, 200, true, Room.BedType.FULL, Room.RoomType.DOUBLE, Room.QualityLevel.ECONOMY));
        reservation2 = new Reservation(0, rooms, "2040-05-01", "2040-05-02", "testerGuest");
        expectedReservations.add(reservation2);
        res.add(reservation2);
        List<Reservation> reservations = (List<Reservation>) res.getAll("testerGuest");
        Collections.sort(expectedReservations, new Comparator<Reservation>() {
            public int compare(Reservation o1, Reservation o2) {
                if(o1.getReservationID() == o2.getReservationID()) {
                    return 0;
                } else if(o1.getReservationID() > o2.getReservationID()) {
                    return -1;
                }
                return 1;
            }
        });
        Assertions.assertEquals(expectedReservations, reservations);
    }

    @Test
    public void addReservationMultipleUsers() throws InvalidArgumentException, SQLException {
        List<Room> rooms = new ArrayList<>();
        List<Reservation> expectedReservations = new ArrayList<>();
        rooms.add(new Room(101, 1, 1, 175.25, true, Room.BedType.TWIN, Room.RoomType.SINGLE, Room.QualityLevel.ECONOMY));
        reservation1 = new Reservation(0, rooms, "2040-05-01", "2040-05-02", "testerGuest");
        expectedReservations.add(reservation1);
        res.add(reservation1);
        List<Reservation> reservations = (List<Reservation>) res.getAll("testerGuest");
        Assertions.assertEquals(expectedReservations, reservations);

        rooms = new ArrayList<>();
        expectedReservations = new ArrayList<>();
        rooms.add(new Room(102, 1, 2, 200, true, Room.BedType.FULL, Room.RoomType.DOUBLE, Room.QualityLevel.ECONOMY));
        reservation2 = new Reservation(0, rooms, "2040-05-01", "2040-05-02", "testerGuest2");
        expectedReservations.add(reservation2);
        res.add(reservation2);
        reservations = (List<Reservation>) res.getAll("testerGuest2");
        Assertions.assertEquals(expectedReservations, reservations);
    }

    @Test
    public void addReservationNoRooms() {
        List<Room> rooms = new ArrayList<>();
        reservation1 = new Reservation(0, rooms, "2040-05-01", "2040-05-02", "testerGuest");
        Assertions.assertThrows(InvalidArgumentException.class, () -> res.add(reservation1));
    }

    @Test
    public void addReservationNoUser() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, 1, 1, 175.25, true, Room.BedType.TWIN, Room.RoomType.SINGLE, Room.QualityLevel.ECONOMY));
        reservation1 = new Reservation(0, rooms, "2040-05-01", "2040-05-02", null);
        Assertions.assertThrows(InvalidArgumentException.class, () -> res.add(reservation1));
    }

    @Test
    public void addReservationRoomTaken() throws InvalidArgumentException, SQLException {
        List<Room> rooms = new ArrayList<>();
        List<Reservation> expectedReservations = new ArrayList<>();
        rooms.add(new Room(101, 1, 1, 175.25, true, Room.BedType.TWIN, Room.RoomType.SINGLE, Room.QualityLevel.ECONOMY));
        reservation1 = new Reservation(0, rooms, "2040-05-01", "2040-05-02", "testerGuest");
        expectedReservations.add(reservation1);
        res.add(reservation1);
        List<Reservation> reservations = (List<Reservation>) res.getAll("testerGuest");
        Assertions.assertEquals(expectedReservations, reservations);

        reservation2 = new Reservation(0, rooms, "2040-05-01", "2040-05-02", "testerGuest2");
        Assertions.assertFalse(res.add(reservation2));
    }

    @Test
    public void removeNullReservation(){
        Assertions.assertThrows(InvalidArgumentException.class, () -> res.remove(null));
    }

    @Test
    public void removeReservation() throws InvalidArgumentException, SQLException {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, 1, 1, 175.25, true, Room.BedType.TWIN, Room.RoomType.SINGLE, Room.QualityLevel.ECONOMY));
        reservation1 = new Reservation(0, rooms, "2040-05-01", "2040-05-02", "testerGuest");
        res.add(reservation1);
        res.remove(reservation1);
        List<Reservation> reservations = (List<Reservation>) res.getAll("testerGuest");
        Assertions.assertEquals(0, reservations.size());
    }
}
