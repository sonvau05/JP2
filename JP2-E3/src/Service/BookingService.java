package Service;

import Entity.Booking;
import Entity.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {
    private List<Booking> bookings = new ArrayList<>();
    private RoomService roomService;

    public BookingService(RoomService roomService) {
        this.roomService = roomService;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public Map<String, Double> calculateRevenueByRoomType() {
        Map<String, Double> revenueMap = new HashMap<>();
        for (Booking booking : bookings) {
            Room room = roomService.getAll().stream()
                    .filter(r -> r.getId().equals(booking.getRoomId()))
                    .findFirst()
                    .orElse(null);
            if (room != null) {
                double revenue = room.getPricePerHour();
                String roomType = room.getRoomType().toString();
                revenueMap.put(roomType, revenueMap.getOrDefault(roomType, 0.0) + revenue);
            }
        }
        return revenueMap;
    }

    public String getTopRoomTypeFor2023() {
        Map<String, Double> revenueMap = calculateRevenueByRoomType();
        return revenueMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> "Room Type: " + entry.getKey() + ", Revenue: " + entry.getValue())
                .orElse("No revenue data available.");
    }
}










