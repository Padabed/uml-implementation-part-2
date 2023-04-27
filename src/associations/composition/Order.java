package associations.composition;

import associations.DeliveryStatus;

import java.time.LocalDateTime;

public class Order {

    private Delivery delivery;

    private String destination; // mandatory
    private LocalDateTime departureTime; // optional
    private LocalDateTime arrivalTime; //optional
    private DeliveryStatus status; // mandatory

    public Order(String destination, LocalDateTime departureTime,
                 LocalDateTime arrivalTime, DeliveryStatus status, Delivery delivery) {
        //setDelivery(delivery);

        setDestination(destination);
        setArrivalTime(arrivalTime);
        setDepartureTime(departureTime);
        setStatus(status);
    }

    public Order(String destination, DeliveryStatus status, Delivery delivery) {
        this(destination, null,null, status, delivery);
    }

    public void remove() {
        if (this.delivery == null) {
            return;
        }
        this.delivery.removeOrder(this);
        setDelivery(null);
    }

    public Delivery getDelivery() {
        return delivery;
    }

    private void setDelivery(Delivery delivery) {
        this.delivery = delivery;
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
