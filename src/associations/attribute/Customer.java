package associations.attribute;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Customer {

    private Set<Order> orders = new HashSet<>();

    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    public Set<Order> getOrders() {
        return Collections.unmodifiableSet(orders);
    }

    public void addOrder(Order o) {
        if (o == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (o.getCustomer() != null && o.getCustomer() != this) {
            throw new IllegalArgumentException("Order is not related to this customer");
        }
        this.orders.add(o);
    }

    public void removeOrder(Order o) {
        if (o == null) {
            throw new IllegalArgumentException("Order cannot be a null value");
        }
        if (o.getCustomer() != this) {
            throw new IllegalArgumentException("Order is not related to this customer");
        }
        this.orders.remove(o);
        o.remove();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name is required");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name is required");
        }
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        this.email = email;
    }

}
