import associations.DeliveryStatus;
import associations.basicAssociation.Delivery;
import associations.basicAssociation.FuelConsumption;
import associations.basicAssociation.Vehicle;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

public class BasicAssociationTest {

    private Delivery delivery;
    private Vehicle vehicle;
    private FuelConsumption fuelConsumption;
    private Set<String> drivers;

    @BeforeEach
    public void load() {
        fuelConsumption = new FuelConsumption(1, 1);
        drivers = new HashSet<>();
        drivers.add("Alex");
        drivers.add("");
    }

    @Test
    public void test() {
        try {
            delivery = new Delivery("Arabika", DeliveryStatus.InPreparation);
            vehicle = new Vehicle("Volvo", null,  fuelConsumption, 2, 1, drivers);

            delivery.setVehicle(vehicle);
            vehicle.removeDelivery(delivery);

            if (delivery.getVehicle() != null && delivery.getVehicle().equals(vehicle)) {
                fail();
            }

            delivery.setVehicle(vehicle);

            if (delivery.getVehicle() != null && !delivery.getVehicle().equals(vehicle)) {
                fail();
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
