package Controller;

import Service.BookingService;
import Entity.RoomType;
import java.time.LocalDateTime;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void bookRoom(RoomType roomType, LocalDateTime checkOut) {
        LocalDateTime checkIn = LocalDateTime.now();
        if (checkOut.isBefore(checkIn)) {
            System.out.println("Thời gian check-out không thể trước thời gian check-in.");
            return;
        }
        bookingService.bookRoom(roomType, checkOut); // Không còn cần tham số phoneNumber và customerName
    }
}
