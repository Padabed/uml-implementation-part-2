package associations.attribute;

import associations.DeliveryStatus;

import java.time.LocalDateTime;

public class Order {
    
    private Customer customer;
    private Product product;

    private String destination; // mandatory
    private LocalDateTime departureTime; // optional
    private LocalDateTime arrivalTime; //optional
    private DeliveryStatus status; // mandatory

    public Order(String destination, LocalDateTime departureTime,
                 LocalDateTime arrivalTime, DeliveryStatus status, Customer customer, Product product) {
        setCustomer(customer);
        setProduct(product);

        setDestination(destination);
        setArrivalTime(arrivalTime);
        setDepartureTime(departureTime);
        setStatus(status);
    }

    public Order(String destination, DeliveryStatus status, Customer customer, Product product) {
        this(destination, null, null, status, customer, product);
    }

    public void remove() {
        if (this.customer != null && this.customer.getOrders().contains(this)) {
            customer.removeOrder(this);
        }
        if (this.product != null && this.product.getOrders().contains(this)) {
            this.product.removeOrder(this);
        }
        this.customer = null;
        this.product = null;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer is required to create an order");
        }
        if (this.customer == null) {
            this.customer = customer;
            this.customer.addOrder(this);
            return;
        }
        this.customer.removeOrder(this);
        this.customer = customer;
        this.customer.addOrder(this);
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product is required to create an order");
        }
        if (this.product == null) {
            this.product = product;
            this.product.addOrder(this);
            return;
        }
        this.product.removeOrder(this);
        this.product = product;
        this.product.addOrder(this);
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        if (destination == null || destination.isBlank()) {
            throw new IllegalArgumentException("Destination address should be provided");
        }
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        if (arrivalTime != null && departureTime.isAfter(arrivalTime)) {
            throw new IllegalArgumentException("Departure time cannot be after arrival time");
        }
        // can be set anytime
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        if (arrivalTime != null && arrivalTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Arrival time cannot be predicted");
        }
        if (departureTime != null && arrivalTime.isBefore(departureTime)) {
            throw new IllegalArgumentException("Arrival time cannot be before departure time");
        }
        this.arrivalTime = arrivalTime;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status is required");
        }
        this.status = status;
    }

}
