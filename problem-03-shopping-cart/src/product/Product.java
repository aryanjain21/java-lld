package product;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Product {
    private final UUID id;
    private String productName;
    private BigDecimal price;

    public Product(String productName, BigDecimal price) {
        this.id = UUID.randomUUID();
        this.productName = validateName(productName);
        this.price = validatePrice(price);
    }

    public UUID getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setProductName(String productName) {
        this.productName = validateName(productName);
    }

    public void setPrice(BigDecimal price) {
        this.price = validatePrice(price);
    }

    private String validateName(String name) {
        Objects.requireNonNull(name, "Product name should not be empty.");

        String trimmedProductName = name.trim();

        if (trimmedProductName.isBlank()) {
            throw new IllegalArgumentException(
                    "Product name cannot be blank."
            );
        }
        return trimmedProductName;
    }

    private BigDecimal validatePrice(BigDecimal price) {
        Objects.requireNonNull(price, "Price can't be null");

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Price cannot be negative."
            );
        }

        return price;
    }
}
