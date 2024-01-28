import java.util.ArrayList;

public class User {
    private static ArrayList<User> usersList = new ArrayList<>();

    private String username;
    private String password;
    private int balance;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        usersList.add(this);
    }

    public static User getUserByUsername(String username) {
        for (User user : usersList) {
            if (username.equals(user.username)) {
                return user;
            }
        }
        return null;
    }

    public void changeUsername(String username) {
        this.username = username;
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    public void increaseBalance(int balance) {
        this.balance += balance;
    }

    public String getUsername() {
        return username;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }

    public int getBalance() {
        return balance;
    }

    public void removeAccount() {
        usersList.remove(this);
    }
}
