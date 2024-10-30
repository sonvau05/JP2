package Controller;

import Entity.Booking;
import Service.BookingService;
import Service.CustomerService;
import Service.RoomService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookingController {
    private BookingService bookingService;
    private CustomerService customerService;
    private RoomService roomService;

    public BookingController(BookingService bookingService, CustomerService customerService, RoomService roomService) {
        this.bookingService = bookingService;
        this.customerService = customerService;
        this.roomService = roomService;
    }

    public void createBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter booking ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter room ID: ");
        String roomId = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter check-in date (YYYY-MM-DD HH:mm:ss): ");
        String checkIn = scanner.nextLine();
        System.out.print("Enter check-out date (YYYY-MM-DD HH:mm:ss): ");
        String checkOut = scanner.nextLine();

        Booking booking = new Booking(id, roomId, customerId, checkIn, checkOut);
        bookingService.addBooking(booking);
        System.out.println("Booking created successfully!");
    }

    public void displayBookingInfo() {
        List<Booking> bookings = bookingService.getBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            bookings.forEach(booking ->
                    System.out.println("ID: " + booking.getId() + ", Room ID: " + booking.getRoomId() + ", Customer ID: " + booking.getCustomerId()));
        }
    }

    public void displayRevenueByRoomType() {
        Map<String, Double> revenueByRoomType = bookingService.calculateRevenueByRoomType();
        revenueByRoomType.forEach((roomType, revenue) ->
                System.out.println("Room Type: " + roomType + ", Total Revenue: " + revenue));
    }

    public void displayTopRoomType() {
        String topRoomType = bookingService.getTopRoomTypeFor2023();
        System.out.println(topRoomType);
    }
}








