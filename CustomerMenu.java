import java.util.Scanner;
import java.util.regex.Matcher;

public class CustomerMenu {
    private final Scanner scanner;
    private final User user;

    public CustomerMenu(Scanner scanner, User user) {
        this.scanner = scanner;
        this.user = user;
    }

    public void run() {
        Matcher matcher;
        while (true) {
            System.out.println("You are in Customer Menu");
            String command = scanner.nextLine().trim();
            if (command.equals("whoami")) {
                System.out.println(user.getUsername());
            } else if (command.equals("back")) {
                break;
            } else if (Commands.PROFILE_MENU.getMatcher(command).matches()) {
                ProfileMenu profileMenu = new ProfileMenu(scanner, user);
                profileMenu.run();
            } else {
                System.out.println("invalid command");
            }
        }
    }
}
