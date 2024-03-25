package billing;

import java.util.Date;
import java.util.List;

public class Bill {
    private int billId;
    private int customerId;
    private Double amount;
    private Date date;
    // List of items/services
    private List<Object> items;
    private String status;

    public Bill(int billId, int customerId, Double amount, Date date, String status) {
        this.billId = billId;
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public void addItem(Object item) {
        this.items.add(item);
    }

}
