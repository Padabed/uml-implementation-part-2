import associations.qualifiedAssociation.Item;
import associations.qualifiedAssociation.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class QualifiedAssociationTest {

    private Item item1;
    private Item item2;
    private Product product;

    @Test
    public void test() {
        try {
            item1 = new Item("Item1", "Desc1");
            item2 = new Item("Item2", "Desc2");
            product = new Product("Product1");

            product.addItem(item1);
            product.addItem(item2);

            product.removeItem(item1);

            if (product.getItems().containsKey(item1.getName())) {
                fail();
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
