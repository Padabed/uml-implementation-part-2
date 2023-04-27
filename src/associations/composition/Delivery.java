package associations.composition;

import associations.DeliveryStatus;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Delivery {

    private Set<Order> orders = new HashSet<>();
    private String name;

    public Delivery(String name) {
        setName(name);
    }

    public Set<Order> getOrders() {
        return Collections.unmodifiableSet(orders);
    }

    public void addOrder(String destination, DeliveryStatus deliveryStatus) {
        Order order = new Order(destination, deliveryStatus, this);
        if (orders.contains(order)) {
            order = null;
            return;
        }
        orders.add(order);
    }

    public void removeOrder(Order order) {
        if (!orders.contains(order)) {
            return;
        }
        orders.remove(order);
    }

    public void remove() {
        for (Order order : orders) {
            order.remove();
            removeOrder(order);
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        this.name = name;
    }
}
