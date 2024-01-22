import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer {
    static Scanner input = new Scanner(System.in);
    public static List<User> users = new ArrayList<>();

    public Customer() {
        do {
            System.out.print("\nPlease Enter Your Username : ");
            String username = input.next();
            System.out.print("Please Enter Your Password : ");
            String password = input.next();
            boolean isUsernamePasswordMatched = checkUsernameAndPassword(username, password);
            if (isUsernamePasswordMatched){
                new CustomerGUI();
                break;
            } else
                System.out.println("Incorrect Username or Password.");
        } while (true);
    }

// Check username and password
    private boolean checkUsernameAndPassword(String username, String password) {
        getUserList(); // Get User list from file
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getPassword().equals(password);
            }
        }

        // if inputted username is not exist system create new user automatically and login
        createNewProfile(username, password);
        return true;
    }

// Create new profile
    private void createNewProfile(String username, String password) {
        System.out.println("\nYou are already not a user, we creating new user account for you.");
        User newuser = new User(username, password);
        users.add(newuser);
        saveInFile(newuser);
    }

// Save user Details into a file
    private void saveInFile(User newuser) {
        String username = newuser.getUsername();
        String password = newuser.getPassword();
        try {
            FileWriter file = new FileWriter("Users.txt", true);
            file.write(username + "," + password);
            file.write("\n");
            file.close();
            System.out.print("Account Created Successfully\n");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        checkUsernameAndPassword(username, password);
    }

// Get User details from file
    private void getUserList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Users.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String username = userData[0];
                String password = userData[1];
                User user = new User(username, password);
                users.add(user);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
