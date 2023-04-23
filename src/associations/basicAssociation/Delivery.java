package associations.basicAssociation;

import associations.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Delivery {

    private Set<Vehicle> vehicles = new HashSet<>();

    private String destination; // mandatory
    private LocalDateTime departureTime; // optional
    private LocalDateTime arrivalTime; //optional
    private DeliveryStatus status; // mandatory

    public Delivery(String destination, LocalDateTime departureTime,
                    LocalDateTime arrivalTime, DeliveryStatus status) {
        setDestination(destination);
        setArrivalTime(arrivalTime);
        setDepartureTime(departureTime);
        setStatus(status);
    }

    public Delivery(String destination, DeliveryStatus status) {
        this(destination, null,null, status);
    }

    public Set<Vehicle> getVehicles() {
        return Collections.unmodifiableSet(vehicles);
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be a null value");
        }
        if (vehicles.contains(vehicle)) {
            return;
        }
        vehicles.add(vehicle);
        vehicle.addDelivery(this);
    }

    public void removeVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be a null value");
        }
        if (!vehicles.contains(vehicle)) {
            throw new IllegalArgumentException("Vehicle does not exist");
        }
        this.vehicles.remove(vehicle);
        vehicle.removeDelivery(this);
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
