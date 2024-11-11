package Entity;

import java.time.LocalDateTime;

public class Order {
    private int Id;
    private String CustomerId;
    private LocalDateTime DateTime;

    public Order() {;}
    public Order(int id, LocalDateTime dateTime, String customerId) {
        this.Id = id;
        this.DateTime = dateTime;
        this.CustomerId = customerId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public LocalDateTime getDateTime() {
        return DateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        DateTime = dateTime;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Id=" + Id +
                ", CustomerId='" + CustomerId + '\'' +
                ", DateTime=" + DateTime +
                '}';
    }
}
