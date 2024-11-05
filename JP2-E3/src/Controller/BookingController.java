package Controller;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Service.BookingService;

import java.time.LocalDateTime;
import java.util.List;

public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void addRoom(Room room) {
        bookingService.addRoom(room);
    }

    public void addCustomer(Customer customer) {
        bookingService.addCustomer(customer);
    }

    public void addBooking(Booking booking) {
        bookingService.addBooking(booking);
    }

    public void createBooking(String cusName, String cusPhone, RoomType roomType, LocalDateTime checkIn, LocalDateTime checkOut) {
        bookingService.createBooking(cusName, cusPhone, roomType, checkIn, checkOut);
    }

    public void displayBookingInfo(String cusName, String cusPhone, String roomId) {
        bookingService.displayBookingInfo(cusName, cusPhone, roomId);
    }

    public double calculateRevenue(RoomType roomType) {
        return bookingService.calculateRevenue(roomType);
    }

    public RoomType getHighestRevenueRoomType(int year) {
        return bookingService.getHighestRevenueRoomType(year);
    }

    public List<Room> getAvailableRooms() {
        return bookingService.getAvailableRooms();
    }
}
