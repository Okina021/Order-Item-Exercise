package entities;

import entities.enums.OrderStatus;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
    public static DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Instant instant;
    private OrderStatus orderStatus;
    private Client client;
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Order(Instant instant, OrderStatus orderStatus, Client client) {
        this.instant = instant;
        this.orderStatus = orderStatus;
        this.client = client;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public void removeItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
    }

    public Double total() {
        double total = 0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getPrice()*orderItem.getQuantity();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order moment: " + dateTimeFormatter.format(getInstant())+"\n");
        stringBuilder.append("Order Status: " + orderStatus+"\n");
        stringBuilder.append("Client: " + client.getName() + " " + dateTimeFormatter2.format(client.getBirthDate()) + " " + client.getEmail()+"\n");

        for (OrderItem orderItem : orderItems) {
            stringBuilder.append(orderItem.getProduct().getName()+", "+orderItem.getPrice()+", "+orderItem.getQuantity()+", Subtotal: "+orderItem.subTotal()+"\n");
        }
        stringBuilder.append("Total price: " + total()+"\n");

        return stringBuilder.toString();
    }
}
