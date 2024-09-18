package application;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

public class Program {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter cliente data:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Birth date (DD/MM/YYYY): ");
        String birth = scanner.nextLine();
        int day = Integer.parseInt(birth.substring(0, 2));
        int month = Integer.parseInt(birth.substring(3, 5));
        int year = Integer.parseInt(birth.substring(6));
        System.out.println(day + " " + month + " " + year + " ");
        LocalDate bithDate = LocalDate.of(year, month, day);
        Client client = new Client(name, email, bithDate);
        Order order = null;
        System.out.println("Enter order data:");
        System.out.println("""
                    PENDING_PAYMENT(1)
                    PROCESSING(2)
                    SHIPPED(3)
                    DELIVERED(4)\
                """);
        String status = scanner.nextLine();
        OrderStatus orderStatus = OrderStatus.valueOf(status);
        Instant instant = Instant.now();
        order = new Order(instant, orderStatus, client);
        System.out.print("How many items to this order?");
        int count = scanner.nextInt();
        scanner.nextLine();
        Product product = null;
        OrderItem orderItem = null;
        for (int i = 0; i < count; i++) {
            System.out.print("Enter #"+(i + 1)+" item data:\n");
            System.out.print("Product name: ");
            String productName = scanner.nextLine();
            System.out.print("Product price: ");
            Double productPrice = scanner.nextDouble();
            System.out.print("Quantity: ");
            int productQuantity = scanner.nextInt();
            scanner.nextLine();
            product = new Product(productName,productPrice);
            orderItem = new OrderItem(productQuantity,productPrice,product);
            order.addItem(orderItem);
        }
        System.out.println("ORDER SUMMARY");
        System.out.println(order);
    }
}
