import Controller.BookingController;
import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Service.BookingService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BookingService bookingService = new BookingService();
        BookingController bookingController = new BookingController(bookingService);

        bookingController.addRoom(new Room("RS001", RoomType.SINGLE, 8));
        bookingController.addRoom(new Room("RD001", RoomType.DOUBLE, 12));
        bookingController.addRoom(new Room("RQ002", RoomType.QUEEN, 35));
        bookingController.addRoom(new Room("RT001", RoomType.TRIPLE, 12.5));
        bookingController.addRoom(new Room("RQ001", RoomType.QUAD, 20.5));

        bookingController.addCustomer(new Customer("001", "Mr.Linus Tovaldo", "84125325346457"));
        bookingController.addCustomer(new Customer("002", "Mr.Bill", "91124235346467"));
        bookingController.addCustomer(new Customer("003", "Mr.Turing", "911423534646"));

        bookingController.addBooking(new Booking("1", "RS001", "001", LocalDateTime.of(2023, 3, 15, 9, 30, 15), LocalDateTime.of(2023, 3, 16, 12, 30, 45)));
        bookingController.addBooking(new Booking("2", "RS001", "002", LocalDateTime.of(2023, 6, 9, 19, 30, 25), LocalDateTime.of(2023, 6, 10, 11, 25, 15)));
        bookingController.addBooking(new Booking("3", "RD001", "002", LocalDateTime.of(2023, 3, 11, 10, 10, 5), LocalDateTime.of(2023, 3, 13, 11, 5, 10)));
        bookingController.addBooking(new Booking("4", "RT001", "003", LocalDateTime.of(2023, 11, 11, 11, 11, 15), LocalDateTime.of(2023, 11, 13, 11, 15, 15)));
        bookingController.addBooking(new Booking("5", "RT001", "001", LocalDateTime.of(2023, 10, 25, 9, 20, 25), LocalDateTime.of(2023, 10, 26, 12, 25, 30)));
        bookingController.addBooking(new Booking("6", "RQ001", "001", LocalDateTime.of(2023, 8, 18, 18, 25, 35), LocalDateTime.of(2023, 8, 19, 11, 35, 20)));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Chọn chức năng:");
            System.out.println("1. Đặt phòng");
            System.out.println("2. Hiển thị thông tin đặt phòng");
            System.out.println("3. Xem danh sách phòng còn trống");
            System.out.println("4. Tính doanh thu theo loại phòng");
            System.out.println("5. Xem loại phòng có doanh thu cao nhất trong năm");
            System.out.println("0. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập tên khách hàng: ");
                    String cusName = scanner.nextLine();
                    System.out.print("Nhập số điện thoại khách hàng: ");
                    String cusPhone = scanner.nextLine();
                    System.out.print("Nhập loại phòng (SINGLE, DOUBLE, QUEEN, TRIPLE, QUAD): ");
                    RoomType roomType = RoomType.valueOf(scanner.nextLine().toUpperCase()); // Chuyển đổi chuỗi thành enum
                    System.out.print("Nhập thời gian check-in (yyyy-mm-ddThh:mm): ");
                    LocalDateTime checkIn = LocalDateTime.parse(scanner.nextLine());
                    System.out.print("Nhập thời gian check-out (yyyy-mm-ddThh:mm): ");
                    LocalDateTime checkOut = LocalDateTime.parse(scanner.nextLine());
                    bookingController.createBooking(cusName, cusPhone, roomType, checkIn, checkOut);
                    break;
                case 2:
                    System.out.print("Nhập tên khách hàng: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập số điện thoại khách hàng: ");
                    String phone = scanner.nextLine();
                    System.out.print("Nhập ID phòng: ");
                    String roomIdForDisplay = scanner.nextLine();
                    bookingController.displayBookingInfo(name, phone, roomIdForDisplay);
                    break;
                case 3:
                    List<Room> availableRooms = bookingController.getAvailableRooms();
                    System.out.println("Phòng còn trống:");
                    availableRooms.forEach(room -> System.out.println("Room ID: " + room.getId() + ", Type: " + room.getRoomType()));
                    break;
                case 4:
                    System.out.print("Nhập loại phòng (SINGLE, DOUBLE, QUEEN, TRIPLE, QUAD): ");
                    RoomType type = RoomType.valueOf(scanner.nextLine().toUpperCase());
                    double revenue = bookingController.calculateRevenue(type);
                    System.out.println("Doanh thu cho loại phòng " + type + ": " + revenue);
                    break;
                case 5:
                    System.out.print("Nhập năm: ");
                    int year = scanner.nextInt();
                    RoomType highestRevenueRoomType = bookingController.getHighestRevenueRoomType(year);
                    System.out.println("Loại phòng có doanh thu cao nhất trong năm " + year + ": " + highestRevenueRoomType);
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
}


