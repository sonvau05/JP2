import Controller.BookingController;
import Controller.CustomerController;
import Controller.RoomController;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Entity.Booking;
import Service.BookingService;
import Service.CustomerService;
import Service.RoomService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RoomService roomService = new RoomService();
        CustomerService customerService = new CustomerService();
        BookingService bookingService = new BookingService(roomService);

        RoomController roomController = new RoomController(roomService);
        CustomerController customerController = new CustomerController(customerService);
        BookingController bookingController = new BookingController(bookingService, customerService, roomService);

        roomController.addRoom(new Room("RS001", RoomType.SINGLE, 8.0));
        roomController.addRoom(new Room("RD001", RoomType.DOUBLE, 12.0));
        roomController.addRoom(new Room("RQ002", RoomType.QUEEN, 35.0));
        roomController.addRoom(new Room("RT001", RoomType.TRIPLE, 12.5));
        roomController.addRoom(new Room("RQ001", RoomType.QUAD, 20.5));

        customerController.addCustomer(new Customer("001", "Mr.Linus Tovaldo", "84125325346457"));
        customerController.addCustomer(new Customer("002", "Mr.Bill", "91124235346467"));
        customerController.addCustomer(new Customer("003", "Mr.Turing", "911423534646"));

        bookingService.addBooking(new Booking("1", "RS001", "001", "2023-03-15 09:30:15", "2023-03-16 12:30:45"));
        bookingService.addBooking(new Booking("2", "RS001", "002", "2023-06-09 19:30:25", "2023-06-10 11:25:15"));
        bookingService.addBooking(new Booking("3", "RD001", "002", "2023-03-11 10:10:05", "2023-03-13 11:05:10"));
        bookingService.addBooking(new Booking("4", "RT001", "003", "2023-11-11 11:11:15", "2023-11-13 11:15:15"));
        bookingService.addBooking(new Booking("5", "RT001", "001", "2023-10-25 09:20:25", "2023-10-26 12:25:30"));
        bookingService.addBooking(new Booking("6", "RQ001", "001", "2023-08-18 18:25:35", "2023-08-19 11:35:20"));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Create Booking");
            System.out.println("2. Display Booking Information");
            System.out.println("3. List Customers");
            System.out.println("4. List Rooms");
            System.out.println("5. Display Revenue by Room Type");
            System.out.println("6. Display Top Room Type for 2023");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> bookingController.createBooking();
                case 2 -> bookingController.displayBookingInfo();
                case 3 -> customerController.displayAllCustomers();
                case 4 -> roomController.listRooms();
                case 5 -> bookingController.displayRevenueByRoomType();
                case 6 -> bookingController.displayTopRoomType();
                case 7 -> running = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}







