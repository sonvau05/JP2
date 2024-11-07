package Entity;

import java.time.LocalDateTime;

public class Pricing {
    private int id;
    private int tickerId;
    private LocalDateTime dateTime;
    private double openPrice;
    private double closePrice;
    private double currentPrice;

    public Pricing(int id, int tickerId, LocalDateTime dateTime, double openPrice, double closePrice, double currentPrice) {
        this.id = id;
        this.tickerId = tickerId;
        this.dateTime = dateTime;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.currentPrice = currentPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTickerId() {
        return tickerId;
    }

    public void setTickerId(int tickerId) {
        this.tickerId = tickerId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "Pricing{" +
                "id=" + id +
                ", tickerId=" + tickerId +
                ", dateTime=" + dateTime +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
