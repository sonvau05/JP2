import Entity.Customer;
import Entity.OrderDetail;
import Entity.Order;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Read data in file
        String sysPath = System.getProperty("user.dir");
        String customerFilePath = sysPath.replace("/", "\\") + "/data/customer.in.txt";
        String orderDetailFilePath = sysPath.replace("/", "\\") + "/data/orderDetail.in.txt";

        List<Customer> customers = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();

        // Reading customers data from file
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(customerFilePath));
            String lineData;
            while ((lineData = bufferedReader.readLine()) != null) {
                Customer customer = new Customer();
                if (!lineData.isEmpty()) {
                    String[] arData = lineData.split(";");
                    customer.setId(Integer.parseInt(arData[0]));
                    customer.setCode(arData[1]);
                    customer.setName(arData[2]);
                }
                customers.add(customer);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Reading order details data from file
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(orderDetailFilePath))) {
            String lineData;
            while ((lineData = bufferedReader.readLine()) != null) {
                if (!lineData.isEmpty()) {
                    String[] arData = lineData.split(";");
                    int id = Integer.parseInt(arData[0]);
                    int orderId = Integer.parseInt(arData[1]);
                    int productId = Integer.parseInt(arData[2]);
                    int quantity = Integer.parseInt(arData[3]);
                    double price = Double.parseDouble(arData[4]);
                    LocalDateTime dateTime = LocalDateTime.parse(arData[5], formatter);

                    OrderDetail orderDetail = new OrderDetail(id, orderId, productId, quantity, price, dateTime);
                    orderDetails.add(orderDetail);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Sorting and displaying customers
        Set<Customer> setCustomer = customers.stream()
                .sorted(Comparator.comparing(Customer::getName).reversed())
                .collect(Collectors.toSet());
        setCustomer.forEach(c -> System.out.println(c.toString("~")));

        // Writing data to customer file
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("customer.out.txt"));
            setCustomer.stream()
                    .peek(c -> {
                        try {
                            String line2Write = c.toString("~");
                            bufferedWriter.write(line2Write);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }).collect(Collectors.toSet());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        displayBillingForCustomer(1, orderDetails, customers);
        displayBillingForCustomer(2, orderDetails, customers);
    }

    private static void displayBillingForCustomer(int customerId, List<OrderDetail> orderDetails, List<Customer> customers) {
        double totalAmount = 0;
        Customer customer = customers.stream().filter(c -> c.getId() == customerId).findFirst().orElse(null);
        if (customer != null) {
            System.out.println("Billing for Customer: " + customer.getName());
            System.out.println("Id|Name|OrderId|ProductId|Quantity|Price|Time");
            for (OrderDetail detail : orderDetails) {
                if (detail.getOrderId() == customerId) {
                    double amount = detail.getQuantity() * detail.getPrice();
                    totalAmount += amount;

                    System.out.println(detail.getOrderId() + "|" +
                            detail.getOrderId() + "|" + detail.getProductId() + "|" +
                            detail.getQuantity() + "|" + detail.getPrice() + "|" +
                            detail.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
            }
            System.out.println("Total Amount: " + totalAmount);
        } else {
            System.out.println("Customer not found!");
        }
    }
}
