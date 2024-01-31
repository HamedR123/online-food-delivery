import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    protected Scanner scanner;
    protected User user;

    public ProfileMenu(Scanner scanner, User user) {
        this.scanner = scanner;
        this.user = user;
    }

    public void run() {
        Matcher matcher;
        while (true) {
            System.out.println("You are in Profile Menu");
            String command = scanner.nextLine().trim();
            if (command.equals("whoami")) {
                System.out.println(user.getUsername());
            } else if ((matcher = Commands.CHANGE_USERNAME.getMatcher(command)).matches()) {
                changeUsername(matcher);
            } else if ((matcher = Commands.CHANGE_PASSWORD.getMatcher(command)).matches()) {
                changePassword(matcher);
            } else if (Commands.GET_BALANCE.getMatcher(command).matches()) {
                getBalance();
            } else if ((matcher = Commands.REMOVE_ACCOUNT.getMatcher(command)).matches()) {
                if (removeAccount(matcher)) {
                    break;
                }
            } else if (command.equals("back")) {
                break;
            } else {
                System.out.println("invalid command");
            }
        }
    }

    private void changeUsername(Matcher matcher) {
        String username = matcher.group("newUsername");
        if (User.getUserByUsername(username) == null) {
            user.changeUsername(username);
            System.out.println("username changed successfully");
        } else {
            System.out.println("this username is taken");
        }
    }

    private void changePassword(Matcher matcher) {
        String oldPass = matcher.group("oldPass");
        String newPass = matcher.group("newPass");
        if (Main.isPasswordStrong(newPass)) {
            if (user.changePassword(oldPass, newPass)) {
                System.out.println("password changed successfully");
            } else {
                System.out.println("wrong password");
            }
        } else {
            System.out.println("weak password");
        }
    }

    private void getBalance() {
        int balance = user.getBalance();
        System.out.printf("Your balance is: %d\n", balance);
    }

    private boolean removeAccount(Matcher matcher) {
        String password = matcher.group("pass");
        if (user.removeAccount(password)) {
            System.out.println("Account was removed successfully");
            return true;
        } else {
            System.out.println("wrong password");
            return false;
        }
    }
}
