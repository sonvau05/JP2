package Service;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingService {
    private List<Booking> bookings = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    private Room findRoomById(String roomId) {
        return rooms.stream()
                .filter(room -> room.getId().equals(roomId))
                .findFirst()
                .orElse(null);
    }

    private Room findAvailableRoom(RoomType roomType) {
        return rooms.stream()
                .filter(room -> room.getRoomType() == roomType)
                .findFirst()
                .orElse(null);
    }

    public void displayBookingInfo(String cusName, String cusPhone, String roomId) {
        Optional<Customer> customer = customers.stream()
                .filter(c -> c.getCusName().equals(cusName) && c.getCusPhone().equals(cusPhone))
                .findFirst();

        if (customer.isPresent()) {
            bookings.stream()
                    .filter(b -> b.getCustomerId().equals(customer.get().getId()) && b.getRoomId().equals(roomId))
                    .forEach(b -> System.out.println("Booking ID: " + b.getId() + ", Room ID: " + b.getRoomId() +
                            ", Check-in: " + b.getCheckIn() + ", Check-out: " + b.getCheckOut()));
        } else {
            System.out.println("Không tìm thấy booking nào cho khách hàng này.");
        }
    }

    public void createBooking(String cusName, String cusPhone, RoomType roomType, LocalDateTime checkIn, LocalDateTime checkOut) {
        Room room = findAvailableRoom(roomType);
        if (room == null) {
            System.out.println("Không có phòng trống cho loại phòng: " + roomType);
            return;
        }

        Customer customer = customers.stream()
                .filter(c -> c.getCusName().equals(cusName) && c.getCusPhone().equals(cusPhone))
                .findFirst()
                .orElseGet(() -> {
                    Customer newCustomer = new Customer(String.valueOf(customers.size() + 1), cusName, cusPhone);
                    customers.add(newCustomer);
                    return newCustomer;
                });

        Booking booking = new Booking(String.valueOf(bookings.size() + 1), room.getId(), customer.getId(), checkIn, checkOut);
        bookings.add(booking);

        System.out.println("Đặt phòng thành công! Thông tin booking:");
        System.out.println("Booking ID: " + booking.getId() + ", Room ID: " + booking.getRoomId() + ", Customer ID: " + booking.getCustomerId());
    }

    public double calculateRevenue(RoomType roomType) {
        return bookings.stream()
                .map(b -> findRoomById(b.getRoomId()))
                .filter(room -> room != null && room.getRoomType() == roomType)
                .mapToDouble(room -> {
                    Booking booking = bookings.stream().filter(b -> b.getRoomId().equals(room.getId())).findFirst().orElse(null);
                    if (booking != null) {
                        long hours = Duration.between(booking.getCheckIn(), booking.getCheckOut()).toHours();
                        return hours * room.getPricePerHour();
                    }
                    return 0;
                })
                .sum();
    }

    public RoomType getHighestRevenueRoomType(int year) {
        return bookings.stream()
                .filter(b -> b.getCheckIn().getYear() == year)
                .map(b -> findRoomById(b.getRoomId()))
                .filter(room -> room != null)
                .collect(Collectors.groupingBy(Room::getRoomType, Collectors.summingDouble(room -> {
                    Booking booking = bookings.stream().filter(b -> b.getRoomId().equals(room.getId())).findFirst().orElse(null);
                    if (booking != null) {
                        long hours = Duration.between(booking.getCheckIn(), booking.getCheckOut()).toHours();
                        return hours * room.getPricePerHour();
                    }
                    return 0;
                })))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public List<Room> getAvailableRooms() {
        List<String> bookedRoomIds = bookings.stream()
                .map(Booking::getRoomId)
                .collect(Collectors.toList());

        return rooms.stream()
                .filter(room -> !bookedRoomIds.contains(room.getId()))
                .collect(Collectors.toList());
    }
}
