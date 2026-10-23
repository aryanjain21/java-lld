
package service;

import cartItem.CartItem;
import product.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();

        // 1. Create products
        Product laptop = new Product(
                "Laptop", new BigDecimal("100.00")
        );

        Product phone = new Product(
                "Phone", new BigDecimal("50.00")
        );

        // 2. Add products
        cart.addProduct(laptop, 2);
        cart.addProduct(laptop, 3); // Should merge with existing item
        cart.addProduct(phone, 1);

        System.out.println("After adding products:");
        printCart(cart);
        System.out.println("Total: " + cart.calculateTotal());
        // Expected total: 550.00

        // 3. Find the laptop cart item
        CartItem laptopItem = cart.getItems()
                .values()
                .stream()
                .filter(item ->
                        item.getProduct().getId().equals(laptop.getId()))
                .findFirst()
                .orElseThrow();

        UUID laptopCartItemId = laptopItem.getCartItemId();

        // 4. Increase quantity
        cart.updateQty(OperationType.INC, laptopCartItemId, 1);

        System.out.println("\nAfter increasing laptop quantity:");
        printCart(cart);
        System.out.println("Total: " + cart.calculateTotal());
        // Laptop: 6 * 100 = 600; Phone: 50
        // Expected total: 650.00

        // 5. Decrease quantity
        cart.updateQty(OperationType.DEC, laptopCartItemId, 2);

        System.out.println("\nAfter decreasing laptop quantity:");
        printCart(cart);
        System.out.println("Total: " + cart.calculateTotal());
        // Laptop: 4 * 100 = 400; Phone: 50
        // Expected total: 450.00

        // 6. Set quantity directly
        cart.updateQty(OperationType.DEFAULT, laptopCartItemId, 3);

        System.out.println("\nAfter setting laptop quantity to 3:");
        printCart(cart);
        System.out.println("Total: " + cart.calculateTotal());
        // Expected total: 350.00

        // 7. Remove phone
        CartItem phoneItem = cart.getItems()
                .values()
                .stream()
                .filter(item ->
                        item.getProduct().getId().equals(phone.getId()))
                .findFirst()
                .orElseThrow();

        cart.removeItem(phoneItem.getCartItemId());

        System.out.println("\nAfter removing phone:");
        printCart(cart);
        System.out.println("Total: " + cart.calculateTotal());
        // Expected total: 300.00

        // 8. Verify quantity limit
        try {
            cart.updateQty(
                    OperationType.DEFAULT,
                    laptopCartItemId,
                    1000
            );

            cart.addProduct(laptop, 1);

        } catch (IllegalArgumentException e) {
            System.out.println("\nExpected validation error: "
                    + e.getMessage());
        }

        System.out.println("\nFinal cart:");
        printCart(cart);
        System.out.println("Total: " + cart.calculateTotal());
    }

    private static void printCart(ShoppingCart cart) {
        for (CartItem item : cart.getItems().values()) {
            System.out.println(
                    "Product: " + item.getProduct().getProductName()
                            + " | Unit price: " + item.getUnitPrice()
                            + " | Quantity: " + item.getQty()
                            + " | Subtotal: " + item.calculateSubtotal()
                            + " | Cart item ID: " + item.getCartItemId()
            );
        }
    }
}
