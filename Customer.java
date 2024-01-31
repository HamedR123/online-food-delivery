import java.util.HashMap;

public class Customer extends User {
    private HashMap<Food, Integer> cart;

    public Customer(String username, String password) {
        super(username, password);
        cart = new HashMap<>();
    }

    public void addToCart(Food food, int count) {
        Integer initialCount = cart.get(food);
        if (initialCount == null) {
            cart.put(food, count);
        } else {
            cart.replace(food, initialCount + count);
        }
    }

    public boolean removeFromCart(Food food, int count) {
        Integer initialCount = cart.get(food);
        if (initialCount == null || count > initialCount) {
            return false;
        }
        cart.replace(food, initialCount - count);
        return true;
    }
}
