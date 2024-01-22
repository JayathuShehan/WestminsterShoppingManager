public abstract class Product {
    private String productID;
    private String productName;
    private int quantity;
    private double price;
    private String category;

    public Product(String productID, String productName, int quantity, double price, String category) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    // Getters
    public String getProductID() {
        return productID;
    }
    public String getProductName() {
        return productName;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }

    // Setters
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "productID = '" + productID + '\'' +
                "productName = '" + productName + '\'' +
                "quantity = " + quantity +
                "price = " + price +
                "Category = " + category;
    }

    public String getProductDetails() {
        return getProductID() + ", " +
                getProductName() + ", " +
                getQuantity() + ", " +
                getPrice() + ", " +
                getCategory() + "\n";
    }
}
