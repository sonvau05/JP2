package Service;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingService {
    private List<Room> rooms;
    private List<Customer> customers;
    private List<Booking> bookings;

    public BookingService(List<Room> rooms, List<Customer> customers, List<Booking> bookings) {
        this.rooms = rooms;
        this.customers = customers;
        this.bookings = bookings;
    }

    public void bookRoom(RoomType roomType, String phoneNumber, String customerName, LocalDateTime checkOut) {
        LocalDateTime checkIn = LocalDateTime.now();

        if (checkOut.isBefore(checkIn)) {
            System.out.println("Thời gian check-out không thể trước thời gian check-in.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedCheckIn = checkIn.format(formatter);
        String formattedCheckOut = checkOut.format(formatter);

        Optional<Room> availableRoom = rooms.stream()
                .filter(room -> room.getRoomType() == roomType)
                .filter(room -> bookings.stream()
                        .noneMatch(booking -> booking.getRoomId().equals(room.getId()) &&
                                !(booking.getCheckOutDateTime().isBefore(checkIn) || booking.getCheckInDateTime().isAfter(checkOut))
                        )
                )
                .findFirst();

        if (availableRoom.isPresent()) {
            Room room = availableRoom.get();
            String roomId = room.getId();
            String customerId = "C" + (customers.size() + 1);
            customers.add(new Customer(customerId, customerName, phoneNumber));

            Booking booking = new Booking(
                    String.valueOf(bookings.size() + 1),
                    roomId,
                    customerId,
                    checkIn,
                    checkOut
            );
            bookings.add(booking);

            System.out.println("Đặt phòng thành công: " + booking.getId());
            System.out.println("Thời gian check-in: " + formattedCheckIn);
            System.out.println("Thời gian check-out: " + formattedCheckOut);
        } else {
            System.out.println("Phòng loại " + roomType + " không có sẵn trong thời gian đã chọn.");
        }
    }

    public void showRevenueByRoomType() {
        Map<RoomType, Double> revenueByRoomType = new HashMap<>();
        bookings.forEach(booking -> {
            Room room = rooms.stream()
                    .filter(r -> r.getId().equals(booking.getRoomId()))
                    .findFirst()
                    .orElseThrow();
            double revenue = room.getPricePerHour() *
                    (booking.getCheckOutDateTime().getHour() - booking.getCheckInDateTime().getHour());
            revenueByRoomType.put(room.getRoomType(),
                    revenueByRoomType.getOrDefault(room.getRoomType(), 0.0) + revenue);
        });

        revenueByRoomType.forEach((roomType, revenue) -> {
            System.out.println(roomType + ": " + revenue + "$");
        });
    }

    public void showMaxRevenueRoomType() {
        Map<RoomType, Double> revenueByRoomType = new HashMap<>();
        bookings.forEach(booking -> {
            Room room = rooms.stream()
                    .filter(r -> r.getId().equals(booking.getRoomId()))
                    .findFirst()
                    .orElseThrow();
            double revenue = room.getPricePerHour() *
                    (booking.getCheckOutDateTime().getHour() - booking.getCheckInDateTime().getHour());
            revenueByRoomType.put(room.getRoomType(),
                    revenueByRoomType.getOrDefault(room.getRoomType(), 0.0) + revenue);
        });

        RoomType maxRevenueRoomType = revenueByRoomType.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("RoomType có doanh thu lớn nhất trong năm 2023: " + maxRevenueRoomType);
    }

    public void showAvailableRooms() {
        List<Room> availableRooms = rooms.stream()
                .filter(room -> bookings.stream()
                        .noneMatch(booking -> booking.getRoomId().equals(room.getId()) &&
                                !(booking.getCheckOutDateTime().isBefore(LocalDateTime.now()) ||
                                        booking.getCheckInDateTime().isAfter(LocalDateTime.now()))
                        )
                )
                .collect(Collectors.toList());

        if (availableRooms.isEmpty()) {
            System.out.println("Không có phòng nào hiện đang có sẵn.");
        } else {
            availableRooms.forEach(room -> {
                System.out.println("Phòng ID: " + room.getId() + ", Loại: " + room.getRoomType() + ", Giá theo giờ: " + room.getPricePerHour() + "$");
            });
        }
    }

    public static void showAllBookings(List<Booking> bookings, List<Customer> customers) {
        LocalDateTime now = LocalDateTime.now();
        boolean hasBookings = false;

        System.out.println("Thông tin khách hàng đang và  ở phòng:");
        for (Booking booking : bookings) {
            if (booking.getCheckInDateTime().isBefore(now) && booking.getCheckOutDateTime().isAfter(now)) {
                Customer customer = customers.stream()
                        .filter(c -> c.getId().equals(booking.getCustomerId()))
                        .findFirst()
                        .orElse(null);

                if (customer != null) {
                    System.out.println("Phòng: " + booking.getRoomId() +
                            ", Khách hàng: " + customer.getName() +
                            ", Số điện thoại: " + customer.getPhone() +
                            ", Thời gian lưu trú: " + booking.getCheckInDateTime() + " đến " + booking.getCheckOutDateTime());
                    hasBookings = true;
                }
            }
        }

        if (!hasBookings) {
            System.out.println("Hiện tại không có khách nào đang ở phòng.");
        }

        System.out.println("\nThông tin khách hàng đã check-out:");
        hasBookings = false;
        for (Booking booking : bookings) {
            if (booking.getCheckOutDateTime().isBefore(now)) {
                Customer customer = customers.stream()
                        .filter(c -> c.getId().equals(booking.getCustomerId()))
                        .findFirst()
                        .orElse(null);

                if (customer != null) {
                    System.out.println("Phòng: " + booking.getRoomId() +
                            ", Khách hàng: " + customer.getName() +
                            ", Số điện thoại: " + customer.getPhone() +
                            ", Thời gian lưu trú: " + booking.getCheckInDateTime() + " đến " + booking.getCheckOutDateTime());
                    hasBookings = true;
                }
            }
        }

        if (!hasBookings) {
            System.out.println("Không có khách nào đã check-out.");
        }
    }
}
