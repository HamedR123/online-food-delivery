import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private static final ArrayList<User> usersList = new ArrayList<>();

    private String username;
    private String password;
    private int balance;
    private HashMap<Food, Integer> cart;
    private Restaurant restaurant;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        usersList.add(this);
        cart = new HashMap<>();
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

    public boolean removeAccount(String password) {
        if (password.equals(this.password)) {
            usersList.remove(this);
            return true;
        }
        return false;
    }

    public void addToCart(Food food, int count) {
        Integer initialCount = cart.get(food);
        if (initialCount == null) {
            cart.put(food, count);
        } else {
            cart.replace(food, initialCount + count);
        }
    }

    public int removeFromCart(String foodName, String restaurantName, int count) {
        Food food = null;
        for (Food temp:
                cart.keySet()) {
            if (temp.getName().equals(foodName) && temp.getRestaurant().getName().equals(restaurantName)) {
                food = temp;
            }
        }
        if (food == null) {
            return 1;
        }
        int initialCount = cart.get(food);
        if (count > initialCount) {
            return 2;
        }
        int finalCount = initialCount - count;
        if (finalCount == 0) {
            cart.remove(food);
        } else {
            cart.replace(food, finalCount);
        }
        return 0;
    }

    public HashMap<Food, Integer> getCart() {
        return cart;
    }

    public boolean purchase() {
        int total = 0;
        for (Food food:
                cart.keySet()) {
            total += food.getPrice() * cart.get(food);
        }
        if (total > balance) {
            return false;
        }
        for (Food food:
                cart.keySet()) {
            food.purchase(cart.get(food));
        }
        balance -= total;
        cart = new HashMap<>();
        return true;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public boolean setRestaurant(Restaurant restaurant) {
        if (this.restaurant == null) {
            this.restaurant = restaurant;
            return true;
        }
        return false;
    }

    public boolean closeRestaurant() {
        if (restaurant == null) {
            return false;
        }
        restaurant.removeRestaurant();
        restaurant = null;
        return true;
    }
}
