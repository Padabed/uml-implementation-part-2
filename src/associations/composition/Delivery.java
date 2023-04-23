package associations.composition;

import associations.DeliveryStatus;

import java.util.HashSet;
import java.util.Set;

public class Delivery {

    private Set<Order> orders = new HashSet<>();

    public Set<Order> getOrders() {
        return orders;
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
        orders.remove(order);
    }

    public void remove() {
        for (Order order : orders) {
            order.removeOrder();
            removeOrder(order);
        }
    }

}
