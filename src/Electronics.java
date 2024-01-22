public class Electronics extends Product{
    private String brand;
    private int warrantyPeriod;

    public Electronics(String productID, String productName, int quantity, double price, String category, String brand, int warrantyPeriod) {
        super(productID, productName, quantity, price, category);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    // Getters
    public String getBrand() {
        return brand;
    }
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    // Setters
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "\nProduct ID = " + getProductID() + '\n' +
                "Product Name = "+ getProductName() + '\n' +
                "Product Quantity = " + getQuantity() + '\n' +
                "Product Price = " + getPrice() + '\n' +
                "Product Category = " + getCategory() + '\n' +
                "brand = " + getBrand() + '\n' +
                "warrantyPeriod = " + getWarrantyPeriod();
    }

    public String getProductDetails() {
        return getProductID() + ", " +
                getProductName() + ", " +
                getQuantity() + ", " +
                getPrice() + ", " +
                getCategory() + ", " +
                getBrand() + ", " +
                getWarrantyPeriod();
    }
}
