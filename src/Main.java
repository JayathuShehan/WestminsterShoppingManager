import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static WestminsterShoppingManager manager = new WestminsterShoppingManager();
    static int choice;

    public static void main(String[] args) {
        System.out.println("---------------------------------------");
        System.out.println("WESTMINSTER SHOPPING MANAGER");
        System.out.println("---------------------------------------");
        do {
            System.out.println("\n1. Manager");
            System.out.println("2. Customer (Open in Graphical User Interface)");
            System.out.println("3. Exit");
            System.out.print("Who are you ? : ");
            choice = (int) WestminsterShoppingManager.numberValidation(new int[]{1, 2, 3});
            if (choice == 1)
                manager();
            else if (choice == 2) {
                customer();
            } else
                System.exit(0);
        } while (true);
    }

    private static void customer() {
        new Customer();
    }

    private static void manager() {
        int maximumProducts = 50;
        while (true) {
            choice = manager.shoppingManagerMenu();
            int productCount = manager.getList().size();
            switch (choice) {
                case 1:
                    if (productCount < maximumProducts)
                        manager.addProduct();
                    else
                        System.out.println("Maximum product quantity(50) is exceeded.");
                    break;
                case 2:
                    manager.removeProduct();
                    break;
                case 3:
                    manager.printProductList();
                    break;
                case 4:
                    manager.saveInFile();
                    break;
                case 5:
                    manager.assignProductList();
                    break;
                case 6:
                    if (!manager.getList().isEmpty())
                        askingSaveFile();
                    System.out.println("Exiting..."); // output is "Successfully wrote to the file.  And Exit..."
                    System.exit(0);
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }

    // When choose "Exit" the system asking save to the file if added at least one product
    public static void askingSaveFile() {
        System.out.print("Do you need to save as a file (y or n)?");
        String yORn = input.next().toLowerCase().strip();
        do {
            if (yORn.equals("y")) {
                manager.saveInFile();
                return;
            } else if (!yORn.equals("n")) {
                System.out.print("Please enter y or n only :");
                yORn = input.next();
                continue;
            }
            return;
        } while (true);
    }
}
