package bearbytes.dev.hotel.billing;

import java.util.Date;
import java.util.List;

/**
 * The Receipt class acts as a receipt, keeping track of the objects purchased
 * as well as the cost and date the purchase was made on.
 */
public class Receipt {
    // The unique id of this receipt.
    private int receiptId;

    // The price of the payment this receipt is based on.
    private Double amount;

    // The date the purchase was made on.
    private Date date;

    // The list of items made during this purchase.
    private List<Object> items;

    /**
     * The Default Constructor for the Receipt class: Creates a unique id, and
     * stores details of a purchase.
     * 
     * @param receiptId The unique id of this receipt.
     * @param amount    The price paid.
     * @param date      The date of the payment.
     * @param items     The List of items making up the purchase.
     */
    public Receipt(int receiptId, Double amount, Date date, List<Object> items) {
        this.receiptId = receiptId;
        this.amount = amount;
        this.date = date;
        this.items = items;
    }

    /**
     * Gets the unique id of this receipt.
     * 
     * @return The id of this receipt.
     */
    public int getReceiptId() {
        return receiptId;
    }

    /**
     * Sets the id of this receipt.
     * 
     * @param receiptId The new id of this receipt.
     */
    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    /**
     * Gets the price of the payment.
     * 
     * @return The price from this receipt.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the price of the receipt.
     * 
     * @param amount The new price of the receipt.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets the date of the payment.
     * 
     * @return The date of the receipt.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the receipt.
     * 
     * @param date The new date of the receipt.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the list of items that make up the receipt.
     * 
     * @return The list of items on the receipt.
     */
    public List<Object> getItems() {
        return items;
    }

    /**
     * Sets the list of items in the receipt to the given list.
     * 
     * @param items The new list of items that make up the receipt.
     */
    public void setItems(List<Object> items) {
        this.items = items;
    }

    // Prints the information contained within this receipt.
    public void printReceipt() {
        System.out.println("Receipt ID: " + receiptId);
        System.out.println("Amount: " + amount);
        System.out.println("Date: " + date);
        System.out.println("Items: ");
        for (Object item : items) {
            System.out.println(item);
        }
    }

}
