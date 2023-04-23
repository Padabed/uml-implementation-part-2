package associations.attribute;

import java.util.HashSet;
import java.util.Set;

public class Product {

    private Set<Order> orders = new HashSet<>();

    private String name;
    private String description;

    public Product(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null value");
        }
        if (order.getProduct() != this) {
            throw new IllegalArgumentException("Order is not related to this product");
        }
        this.orders.add(order);
    }

    public void removeOrder() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Product description is required");
        }
        this.description = description;
    }
}
