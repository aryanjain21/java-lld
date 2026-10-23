package service;

import cartItem.CartItem;
import product.Product;

import java.math.BigDecimal;
import java.util.*;

public class ShoppingCart {
    private final Map<UUID, CartItem> items = new HashMap<>();

    public boolean addProduct(Product product, int qty) {
        Objects.requireNonNull(product, "Product should not be null.");
        for (CartItem existingItem : items.values()) {
            // What conditions should you check here?
            if (existingItem.getProduct().getId().equals(product.getId()) && existingItem.getUnitPrice().compareTo(product.getPrice()) == 0) {
                existingItem.increaseQty(qty);
                return true;
            }
        }

        CartItem cartItem = new CartItem(product, qty);
        items.putIfAbsent(cartItem.getCartItemId(), cartItem);

        return true;
    }

    public void updateQty(OperationType type, UUID cartItemId, int qty) {
        Objects.requireNonNull(cartItemId, "Cart item ID cannot be null.");
        Objects.requireNonNull(type, "Operation type cannot be null.");
        CartItem item = items.get(cartItemId);
        if (item == null) {
            throw new IllegalArgumentException("Item not exist.");
        }
        switch (type) {
            case OperationType.INC -> item.increaseQty(qty);
            case OperationType.DEC -> item.decreaseQty(qty);
            case OperationType.DEFAULT -> item.updateQty(qty);
        }
    }

    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for(CartItem item: items.values()) {
            total = total.add(item.calculateSubtotal());
        }

        return total;
    }

    public CartItem removeItem(UUID cartItemId) {
        Objects.requireNonNull(cartItemId, "Cart item ID cannot be null.");
        CartItem removedItem = items.remove(cartItemId);

        if (removedItem == null) {
            throw new IllegalArgumentException("Item does not exist in cart.");
        }

        return removedItem;
    }

    public Map<UUID, CartItem> getItems() {
        return Collections.unmodifiableMap(items);
    }
}
