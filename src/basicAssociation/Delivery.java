package basicAssociation;

import java.time.LocalDateTime;

public class Delivery {

    private Vehicle vehicle;

    private String destination; // mandatory
    private LocalDateTime departureTime; // optional
    private LocalDateTime arrivalTime; //optional
    private DeliveryStatus status; // mandatory

    public Delivery(String destination, LocalDateTime departureTime,
                    LocalDateTime arrivalTime, DeliveryStatus status, Vehicle vehicle) {
        setDestination(destination);
        setArrivalTime(arrivalTime);
        setDepartureTime(departureTime);
        setStatus(status);
        setVehicle(vehicle);
    }

    public Delivery(String destination, DeliveryStatus status, Vehicle vehicle) {
        this(destination, null,null, status, vehicle);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("It should exist at least one vehicle for delivery");
        }
        if (this.vehicle == vehicle) {
            return;
        }
        this.vehicle = vehicle;
    }

    public void removeVehicle() {
        
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
        if (arrivalTime.isAfter(LocalDateTime.now())) {
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
