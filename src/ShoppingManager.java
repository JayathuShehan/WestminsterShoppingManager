import java.util.List;

public interface ShoppingManager {
    void addProduct();
    void removeProduct();
    void printProductList();
    void saveInFile();
    List<Product> getProductsFromFile();
}