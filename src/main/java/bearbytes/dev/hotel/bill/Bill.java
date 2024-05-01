package bearbytes.dev.hotel.bill;

import bearbytes.dev.hotel.checkout.Order;
import bearbytes.dev.hotel.reservation.Reservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * This class holds all information in order to charge an account
 * for a purchase that was made.
 */
public class Bill {
    private Integer billID;
    private double reservationTotal = 0.0;
    private double shoppingTotal = 0.0;
    private double shoppingTax = 0.0;
    private double shoppingSubTotal = 0.0;
    private double cancelationTotal = 0.0;
    private double billTotal = 0.0;
    private String username;
    private List<Reservation> reservations;
    private List<Reservation> cancelledReservations;
    private List<Order> orders;

    public Integer getBillID()  {
        return billID;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public double getReservationTotal() {
        return reservationTotal;
    }

    public double getShoppingTotal() {
        return shoppingTotal;
    }

    public double getShoppingTax() {
        return shoppingTax;
    }

    public double getShoppingSubTotal() {
        return shoppingSubTotal;
    }

    public double getBillTotal() {
        return billTotal;
    }

    public String getUsername() {
        return username;
    }

    public List<Reservation> getCancelledReservations() {
        return cancelledReservations;
    }

    public double getCancelationTotal() {
        return cancelationTotal;
    }

    /**
     * The Default Constructor for a Bill: stores a unique id, customer info, and
     * payment details
     *
     * @param reservations List of current reservations
     * @param orders       List of completed orders.
     * @param username     Username of user whose bill is being generated.
     * @param billID       The unique id of this bill.
     * @param cancelledReservations     List of cancelled reservations
     */
    public Bill(List<Reservation> reservations, List<Order> orders, String username, Integer billID, List<Reservation> cancelledReservations)  {
        this.billID = billID;
        this.username = username;
        this.reservations = reservations;
        this.cancelledReservations = cancelledReservations;
        this.orders = orders;

        for(Order o : orders)  {
            shoppingSubTotal += o.getSubtotal();
        }
        shoppingTax = shoppingSubTotal * 0.08;
        shoppingTotal = shoppingSubTotal + shoppingTax;

        for(Reservation reservation : reservations)  {
            Date end;
            Date start;
            try {
                end = new SimpleDateFormat("yyyy-MM-dd").parse(reservation.getEndDate());
                start = new SimpleDateFormat("yyyy-MM-dd").parse(reservation.getStartDate());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Long daysStayed = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
            reservationTotal += reservation.getRate() * daysStayed;
        }

        for(Reservation reservation : cancelledReservations)  {
            cancelationTotal += reservation.getCancellationFee();
        }


        billTotal = shoppingTotal + reservationTotal + cancelationTotal;
    }

    @Override
    public String toString() {
        return "Bill{" +
                ", reservationTotal=" + reservationTotal +
                ", shoppingTotal=" + shoppingTotal +
                ", shoppingTax=" + shoppingTax +
                ", shoppingSubTotal=" + shoppingSubTotal +
                ", billTotal=" + billTotal +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Double.compare(reservationTotal, bill.reservationTotal) == 0 && Double.compare(shoppingTotal, bill.shoppingTotal) == 0 && Double.compare(shoppingTax, bill.shoppingTax) == 0 && Double.compare(shoppingSubTotal, bill.shoppingSubTotal) == 0 && Double.compare(cancelationTotal, bill.cancelationTotal) == 0 && Double.compare(billTotal, bill.billTotal) == 0 && Objects.equals(billID, bill.billID) && Objects.equals(username, bill.username) && Objects.equals(reservations, bill.reservations) && Objects.equals(cancelledReservations, bill.cancelledReservations) && Objects.equals(orders, bill.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billID, reservationTotal, shoppingTotal, shoppingTax, shoppingSubTotal, cancelationTotal, billTotal, username, reservations, cancelledReservations, orders);
    }
}


