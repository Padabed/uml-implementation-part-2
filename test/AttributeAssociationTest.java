import associations.DeliveryStatus;
import associations.attribute.Customer;
import associations.attribute.Order;
import associations.attribute.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class AttributeAssociationTest {

    private Customer customer;
    private Order order;
    private Product product;

    @Test
    public void test() {
        try {
            customer = new Customer("nikita", "padabed", "email");
            product = new Product("Wheel", "one fancy wheel");

            order = new Order("Minsk", null, null,
                    DeliveryStatus.OnTheWay, customer, product);

            customer.removeOrder(order);

            if (customer.getOrders().contains(order) || product.getOrders().contains(order)) {
                fail();
            }

            if (order.getCustomer() != null || order.getProduct() != null) {
                fail();
            }

            order = new Order("Minsk", null, null,
                    DeliveryStatus.OnTheWay, customer, product);

            order.remove();

            if (customer.getOrders().contains(order) || product.getOrders().contains(order)) {
                fail();
            }

            if (order.getCustomer() != null || order.getProduct() != null) {
                fail();
            }

            customer.addOrder(order);

            if (order.getCustomer() != null && !order.getCustomer().equals(customer)) {
                fail();
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
