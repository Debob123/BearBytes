package bearbytes.dev.hotel.interfaces;

import bearbytes.dev.hotel.accounts.Account;
import bearbytes.dev.hotel.floor.Room;
import bearbytes.dev.hotel.reservation.Reservation;

import java.sql.SQLException;
import java.util.Collection;

public interface IReservationDAO extends InterfaceDAO<Reservation>{
    Collection<Reservation> getAll(String username) throws ClassNotFoundException, SQLException;
}

