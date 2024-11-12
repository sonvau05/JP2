package Entity;

public class ProductStatistics {
    private int productId;
    private int totalClicks;
    private int totalAddToCart;
    private int totalCheckouts;

    public ProductStatistics(){;}
    public ProductStatistics(int productId) {
        this.productId = productId;
    }

    public void updateStatistics(Product behavior) {
        totalClicks += behavior.getClicks();
        totalAddToCart += behavior.getAddToCart();
        totalCheckouts += behavior.getCheckout();
    }

    public int getProductId() {
        return productId;
    }

    public int getTotalClicks() {
        return totalClicks;
    }

    public double getClickPercentage(int total) {
        return (double) totalClicks / total * 100;
    }

    public double getAddToCartPercentage(int total) {
        return (double) totalAddToCart / total * 100;
    }

    public double getCheckoutPercentage(int total) {
        return (double) totalCheckouts / total * 100;
    }

    public String toString(int totalClicks) {
        return String.format("%d; %.2f%%; %d; %.2f%%", productId, getClickPercentage(totalClicks), totalAddToCart, getCheckoutPercentage(totalClicks));
    }
}
