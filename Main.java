import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Insert 1 for sign-up, 2 for login and 3 for exit");
            int menu = Integer.parseInt(scanner.nextLine());
            if (menu == 1) {
                System.out.println("sign-up");
                System.out.println("Choose your username");
                String username = scanner.nextLine();
                if (User.getUserByUsername(username) == null) {
                    System.out.println("Choose your password");
                    String password = scanner.nextLine();
                    new User(username, password);
                    System.out.println("Account created");
                } else {
                    System.out.println("This username is already taken");
                }
            } else if (menu == 2) {
                System.out.println("login");
                System.out.println("Enter your username");
                String username = scanner.nextLine();
                User user = User.getUserByUsername(username);
                if (user == null) {
                    System.out.println("No such user");
                } else {
                    System.out.println("Enter your password");
                    String password = scanner.nextLine();
                    if (user.isPasswordCorrect(password)) {
                        System.out.println("Logged in");
                    } else {
                        System.out.println("Wrong password");
                    }
                }
            } else {
                System.out.println("exit");
                break;
            }
        }
        scanner.close();
    }
}