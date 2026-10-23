package cartItem;

import product.Product;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class CartItem {
    private final UUID cartItemId;
    private final Product product;
    private final BigDecimal unitPrice;
    private int qty;
    private static final int MAX_QTY = 1000;

    public CartItem(Product product, int qty) {
        this.cartItemId = UUID.randomUUID();
        this.product = validateProduct(product);
        this.unitPrice = this.product.getPrice();
        this.qty = validateQty(qty);
    }

    public UUID getCartItemId() {
        return cartItemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public Product getProduct() {
        return product;
    }

    public void updateQty(int qty) {
        int quantity = validateQty(qty);
        this.qty = quantity;
    }

    public void increaseQty(int qty) {
        int increseQtyBy = validateQty(qty);

        if (increseQtyBy > MAX_QTY - this.qty) {
            throw new IllegalArgumentException("Quantity must not exceed " + MAX_QTY);
        }

        this.qty += increseQtyBy;
    }

    public void decreaseQty(int qty) {
        int decreaseQtyBy = validateQty(qty);

        if (decreaseQtyBy >= this.qty) {
            throw new IllegalArgumentException("Quantity must remain at least 1.");
        }

        this.qty -= decreaseQtyBy;
    }

    public BigDecimal calculateSubtotal() {
        return this.unitPrice.multiply(BigDecimal.valueOf(this.qty));
    }

    private Product validateProduct(Product product) {
        Objects.requireNonNull(product, "Product cannot be null.");
        return product;
    }

    private int validateQty(int qty) {
        if (qty <= 0 || qty > MAX_QTY) {
            throw new IllegalArgumentException(
                    "Quantity must be between 1 and " + MAX_QTY + "."
            );
        }

        return qty;
    }
}
