import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loop:
        while (true) {
            System.out.println("Insert 1 for sign-up, 2 for login and 3 for exit");
            int menu = tryParse(scanner.nextLine());
            String username;
            switch (menu) {
                case 1:
                    System.out.println("sign-up");
                    System.out.println("Choose your username");
                    username = scanner.nextLine();
                    if (User.getUserByUsername(username) == null) {
                        System.out.println("Choose your password");
                        String password = scanner.nextLine();
                        if (isPasswordInvalid(password)) {
                            System.out.println("Password should not contain white space");
                            continue;
                        }
                        if (!isPasswordStrong(password)) {
                            System.out.println("weak password");
                            continue;
                        }
                        new User(username, password);
                        System.out.println("Account created");
                    } else {
                        System.out.println("This username is already taken");
                    }
                    break;
                case 2:
                    System.out.println("login");
                    System.out.println("Enter your username");
                    username = scanner.nextLine();
                    User user = User.getUserByUsername(username);
                    if (user == null) {
                        System.out.println("No such user");
                    } else {
                        System.out.println("Enter your password");
                        String password = scanner.nextLine();
                        if (user.isPasswordCorrect(password)) {
                            MainMenu mainMenu = new MainMenu(scanner, user);
                            mainMenu.run();
                        } else {
                            System.out.println("Wrong password");
                        }
                    }
                    break;
                case 3:
                    System.out.println("exit");
                    break loop;
                default:
                    System.out.println("Invalid input");
            }
        }
        scanner.close();
    }

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static boolean isPasswordInvalid(String password) {
        return Pattern.compile("\\s").matcher(password).find();
    }

    public static boolean isPasswordStrong(String password) {
        return password.length() > 5 &&
                Pattern.compile("[a-z]").matcher(password).find() &&
                Pattern.compile("[A-Z]").matcher(password).find() &&
                Pattern.compile("[0-9]").matcher(password).find();
    }
}