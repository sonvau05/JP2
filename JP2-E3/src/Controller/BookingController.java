package Controller;

import Service.BookingService;
import Entity.RoomType;

import java.time.LocalDateTime;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void bookRoom(RoomType roomType, LocalDateTime checkIn, LocalDateTime checkOut) {
        bookingService.bookRoom(roomType, checkIn, checkOut);
    }

    public void showRevenueByRoomType() {
        bookingService.showRevenueByRoomType();
    }

    public void showMaxRevenueRoomType() {
        bookingService.showMaxRevenueRoomType();
    }

    public void showAvailableRooms() {
        bookingService.showAvailableRooms();
    }
}
