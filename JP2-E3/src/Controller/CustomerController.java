package Controller;

import Entity.Customer;
import Service.CustomerService;

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }

    public void displayAllCustomers() {
        customerService.getCustomers().forEach(customer ->
                System.out.println("ID: " + customer.getId() + ", Name: " + customer.getCusName() + ", Phone: " + customer.getCusPhone()));
    }
}









