package bearbytes.dev.hotel.billing;

import java.util.Date;
import java.util.List;

public class Receipt {

    private int receiptId;
    private Double amount;
    private Date date;
    private List<Object> items;

    public Receipt(int receiptId, Double amount, Date date, List<Object> items) {
        this.receiptId = receiptId;
        this.amount = amount;
        this.date = date;
        this.items = items;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

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
