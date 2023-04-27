package associations.qualifiedAssociation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Product {

    private Map<String, Item> items = new HashMap<>();

    private String name;

    public Product(String name) {
        setName(name);
    }

    public Map<String, Item> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("New item cannot be a null value");
        }
        // recheck this
        if (items.containsKey(item.getName())) {
            return;
        }
        this.items.put(item.getName(), item);
        item.setProduct(this);
    }

    public void removeItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be a null value");
        }
        if (!this.items.containsKey(item.getName())) {
            return;
        }
        items.remove(item.getName());
        item.setProduct(null);
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


}
