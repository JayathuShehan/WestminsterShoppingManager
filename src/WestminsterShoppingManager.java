import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


class WestminsterShoppingManager implements ShoppingManager {
    static Scanner input = new Scanner(System.in);
    public static List<Product> products;

    //Variables to get Product details
    //COMMON
    static String productID, productName, productBrand, size, colour;
    static int productQuantity, warrantyPeriod;
    static double productPrice;

    public WestminsterShoppingManager() {
        products = new ArrayList<>();
    }

    public List getList() {
        return products;
    }

    //return the selected number. (Main class)
    public int shoppingManagerMenu() {
        System.out.println("\n1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print the list of the products");
        System.out.println("4. Save in a file");
        System.out.println("5. Get product list from file");
        System.out.println("6. Exit");
        System.out.print("Enter your choice : ");
        int option = (int) numberValidation(new int[]{1, 2, 3, 4, 5, 6, 0});
        System.out.println();
        return option;
    }

    // add product
    @Override
    public void addProduct() {
        int productType;
        Product newProduct;
        System.out.println("What is the product type");
        System.out.println("\t1. Electronic");
        System.out.println("\t2. Clothing ");
        System.out.print("Choose one: ");
        productType = (int) numberValidation(new int[]{1, 2});
        System.out.print("\nProduct ID \t\t:");
        productID = input.next();
        System.out.print("Product Name \t:");
        productName = input.next();
        System.out.print("Product Quantity:");
        productQuantity = (int) numberValidation();
        System.out.print("Product Price \t:");
        productPrice = numberValidation();
        if (productType == 1) {
            electronics();
            newProduct = new Electronics(productID, productName, productQuantity, productPrice, "Electronics", productBrand, warrantyPeriod);
        }
        else {
            clothing();
            newProduct = new Clothing(productID, productName, productQuantity, productPrice, "Clothing", size, colour);
        }
        System.out.println(newProduct);
        products.add(newProduct);
        System.out.println("\nProduct added successfully.");
    }

    // These methods (Electronics, clothing) are using for only in addProduct() class.
    private static void electronics() {
        System.out.print("Product Brand \t:");
        productBrand = input.next();
        System.out.print("Warranty Period (months):");
        warrantyPeriod = (int) numberValidation();
    }
    private static void clothing() {
        System.out.print("Cloth Size \t\t:");
        size = input.next();
        System.out.print("Colour \t\t\t:");
        colour = input.next();
    }



    // remove product
    @Override
    public void removeProduct() {
        System.out.print("\nEnter product ID :");
        String productID = input.next();
        String confirmation;
        for (Product product : products) {
            if (Objects.equals(product.getProductID(), productID)) {
                System.out.println(product);
                System.out.println("Are you sure ? (y/n)");
                confirmation = input.next().toLowerCase().trim();
                if (confirmation.equals("y")) {
                    products.remove(product);
                    System.out.println("Product removed successful.");
                    System.out.println("Total product count is now " + products.size());
                } else {
                    System.out.println("Cancelled.");
                }
                return;
            }
        }
        System.out.println("There are no any product with this ID");
    }

    @Override
    public void printProductList() {
        List<Product> sortedProductList = getSortedProducts();
        for (Product product : sortedProductList) {
            System.out.println(product);
        }
    }

    @Override
    public void saveInFile() {
        try {
            FileWriter file = new FileWriter("Products.txt");
            for (Product product : products) {
                file.write(product.getProductDetails());
                file.write("\n");
            }
            file.close();
            System.out.print("Successfully wrote to the file. ");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // 5. Get product list from file
    @Override
    public List<Product> getProductsFromFile() {
        try {
            File file = new File("Products.txt");
            Scanner line = new Scanner(file);
            String[] product;
            Product newProduct;
            while (line.hasNextLine()) {
                product = line.nextLine().split("\\s*,\\s*");
                if (Objects.equals(product[4], "Electronics")) {
                    newProduct = new Electronics(
                            product[0], // productID
                            product[1], // productName
                            (int) Double.parseDouble(product[2]), // quantity
                            Double.parseDouble(product[3]), // price
                            product[4], // category (Electronic or Clothing)
                            product[5], // Brand
                            (int) Double.parseDouble(product[6]) // warrantyPeriod
                    );
                } else {
                    newProduct = new Clothing(
                            product[0], // productID
                            product[1], // productName
                            (int) Double.parseDouble(product[2]), // quantity
                            Double.parseDouble(product[3]), // price
                            product[4], // category (Electronic or Clothing)
                            product[5], // Colour
                            product[6] // Size
                    );
                }
                products.add(newProduct);
            }
            line.close();
            return products;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    /* Assign the product list to the local list after get product list from file.
    * this getProductFromFile() method is useful in Customer class*/
    public void assignProductList() {
        products = getProductsFromFile();
        System.out.println("Successfully assigned.");
    }

// Sort Products by Name
    private static List<Product> getSortedProducts() {
        // Sort the products based on productID
        products.sort(new Comparator<>() {
            @Override
            public int compare(Product p1, Product p2) {
                // Assuming productID is a String, adjust the comparison accordingly if it's another type
                return p1.getProductID().compareTo(p2.getProductID());
            }
        });

        return products;
    }

    public static double numberValidation(int[] choices) { // for validate menu numbers
        double number;
        do {
            try {
                number = input.nextInt();
                for (int choice : choices) {
                    if (choice == number)
                        return number;
                }
                System.out.print("Please enter menu numbers only : ");
                input.next();
            } catch (InputMismatchException e) {
                System.out.print("Please enter numbers only : ");
                input.next();
            }
        } while (true);
    }
    public static double numberValidation() { //for check numbers
        double number;
        do {
            try {
                number = input.nextDouble();
                return number;
            } catch (InputMismatchException e) {
                System.out.print("Please enter numbers only : ");
                input.next();
            }
        } while (true);
    }
}