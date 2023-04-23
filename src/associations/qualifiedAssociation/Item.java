package associations.qualifiedAssociation;

public class Item {

    private Product product;
    private String name;
    private String description;

    public Item(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public void remove() {
        product.removeItem(this);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        if (this.product != null) {
            this.product.removeItem(this);
        }
        this.product = product;
        if (product != null) {
            product.addItem(this);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Item description is required");
        }
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Item name is required");
        }
        this.name = name;
    }
}
