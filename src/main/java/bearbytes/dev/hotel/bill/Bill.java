package bearbytes.dev.hotel.bill;

import bearbytes.dev.hotel.checkout.Order;
import bearbytes.dev.hotel.reservation.Reservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Bill {
    private Integer billID;
    private double reservationTotal = 0.0;
    private double shoppingTotal = 0.0;
    private double shoppingTax = 0.0;
    private double shoppingSubTotal = 0.0;
    private double billTotal = 0.0;
    private String username;
    private List<Reservation> reservations;
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

    public Bill(List<Reservation> reservations, List<Order> orders, String username, Integer billID)  {
        this.billID = billID;
        this.username = username;
        this.reservations = reservations;
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
        billTotal = shoppingTotal + reservationTotal;
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
}


