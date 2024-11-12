package Entity;

import java.time.LocalDate;

public class Product {
    private int productId;
    private int clicks;
    private int addToCart;
    private int checkout;
    private LocalDate date;

    public Product(){;}
    public Product(int productId, int clicks, int addToCart, int checkout, LocalDate date) {
        this.productId = productId;
        this.clicks = clicks;
        this.addToCart = addToCart;
        this.checkout = checkout;
        this.date = date;
    }

    public int getProductId() {
        return productId;
    }

    public int getClicks() {
        return clicks;
    }

    public int getAddToCart() {
        return addToCart;
    }

    public int getCheckout() {
        return checkout;
    }

    public LocalDate getDate() {
        return date;
    }
}
