import associations.DeliveryStatus;
import associations.composition.Delivery;
import associations.composition.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class CompositionTest {

    private Order order;
    private Order order1;
    private Delivery delivery;

    @Test
    public void test() {
        try {
            delivery = new Delivery("Delivery");

            delivery.addOrder("Brest", DeliveryStatus.Delivered);

            delivery.remove();

            if (delivery.getOrders().isEmpty()) {
                fail();
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
