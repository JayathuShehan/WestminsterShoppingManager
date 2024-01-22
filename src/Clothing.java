public class Clothing extends Product {
    private String size;
    private String colour;

    public Clothing(String productID, String productName, int quantity, double price, String category, String size, String colour) {
        super(productID, productName, quantity, price, category);
        this.size = size;
        this.colour = colour;
    }

    // Getters
    public String getSize() {
        return size;
    }
    public String getColour() {
        return colour;
    }

    // Setters
    public void setSize(String size) {
        this.size = size;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "\nProduct ID = " + getProductID() + '\n' +
                "Product Name = "+ getProductName() + '\n' +
                "Product Quantity = " + getQuantity() + '\n' +
                "Product Price = " + getPrice() + '\n' +
                "Product Category = " + getCategory() + '\n' +
                "Size = " + getSize() + '\n' +
                "Colour = " + getColour();
    }

    public String getProductDetails() {
        return getProductID() + ", " +
                getProductName() + ", " +
                getQuantity() + ", " +
                getPrice() + ", " +
                getCategory() + ", " +
                getSize() + ", " +
                getColour() ;
    }
}
