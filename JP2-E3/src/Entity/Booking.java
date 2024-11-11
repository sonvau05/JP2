package Entity;

import java.time.LocalDateTime;

public class Booking {
    private String id;
    private String roomId;
    private String customerId;
    private LocalDateTime checkInDateTime;
    private LocalDateTime checkOutDateTime;

    public Booking(String id, String roomId, String customerId, LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime) {
        this.id = id;
        this.roomId = roomId;
        this.customerId = customerId;
        this.checkInDateTime = checkInDateTime;
        this.checkOutDateTime = checkOutDateTime;
    }

    public String getId() {
        return id;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    public LocalDateTime getCheckOutDateTime() {
        return checkOutDateTime;
    }
}
