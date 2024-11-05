package Entity;

import java.time.LocalDateTime;

public class Booking {
    private String id;
    private String roomId;
    private String customerId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;


    public Booking(String id, String roomId, String customerId, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.id = id;
        this.roomId = roomId;
        this.customerId = customerId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

}










