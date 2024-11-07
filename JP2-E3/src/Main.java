import Entity.*;
import Service.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("001", "Mr.Linus Tovaldo", "84125325346457"));
        customers.add(new Customer("002", "Mr.Bill", "91124235346467"));
        customers.add(new Customer("003", "Mr.Turing", "91142353464667"));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking("1", "RS001", "001", LocalDateTime.of(2023, 11, 7, 14, 0), LocalDateTime.of(2023, 11, 7, 16, 0)));
        bookings.add(new Booking("2", "RD001", "002", LocalDateTime.of(2023, 11, 8, 10, 0), LocalDateTime.of(2023, 11, 8, 12, 0)));
        bookings.add(new Booking("3", "RQ002", "003", LocalDateTime.of(2023, 11, 6, 9, 0), LocalDateTime.of(2023, 11, 6, 12, 0)));

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("RS001", RoomType.SINGLE, 8));
        rooms.add(new Room("RD001", RoomType.DOUBLE, 12));
        rooms.add(new Room("RQ002", RoomType.QUEEN, 35));

        BookingService bookingService = new BookingService(rooms, customers, bookings);
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        int option;
        do {
            System.out.println("\n1. Đặt phòng");
            System.out.println("2. Thống kê doanh thu theo RoomType");
            System.out.println("3. Hiển thị RoomType có doanh thu cao nhất");
            System.out.println("4. Xem thông tin phòng hiện có");
            System.out.println("5. Xem thông tin khách đang ở phòng");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng (1-6): ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Chọn loại phòng: ");
                    String roomTypeInput = scanner.nextLine();
                    RoomType roomType = RoomType.valueOf(roomTypeInput.toUpperCase());

                    System.out.print("Nhập thời gian check-in (yyyy-MM-dd HH:mm): ");
                    String checkInInput = scanner.nextLine();
                    LocalDateTime checkIn = LocalDateTime.parse(checkInInput, formatter);

                    System.out.print("Nhập thời gian check-out (yyyy-MM-dd HH:mm): ");
                    String checkOutInput = scanner.nextLine();
                    LocalDateTime checkOut = LocalDateTime.parse(checkOutInput, formatter);

                    bookingService.bookRoom(roomType, checkIn, checkOut);
                    break;

                case 2:
                    System.out.println("\nDoanh thu theo từng RoomType:");
                    bookingService.showRevenueByRoomType();
                    break;

                case 3:
                    bookingService.showMaxRevenueRoomType();
                    break;

                case 4:
                    System.out.println("\nThông tin phòng hiện có:");
                    bookingService.showAvailableRooms();
                    break;

                case 5:
                    System.out.println("\nThông tin khách hàng:");
                    bookingService.showAllBookings(bookings, customers);
                    break;

                case 6:
                    System.out.println("Thoát chương trình.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (option != 6);
    }
}
