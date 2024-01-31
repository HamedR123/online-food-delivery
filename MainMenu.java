import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    private final Scanner scanner;
    private final User user;

    public MainMenu(Scanner scanner, User user) {
        this.scanner = scanner;
        this.user = user;
    }

    public void run() {
        System.out.println("You are in Main Menu");
        Matcher matcher;
        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equals("whoami")) {
                System.out.println(user.getUsername());
            } else if (command.equals("whereami")) {
                System.out.println("Main Menu");
            } else if (command.equals("back")) {
                break;
            } else if (Commands.PROFILE_MENU.getMatcher(command).matches()) {
                ProfileMenu profileMenu = new ProfileMenu(scanner, user);
                profileMenu.run();
            } else if (Commands.SHOW_BALANCE.getMatcher(command).matches()) {
                showBalance();
            } else if ((matcher = Commands.INCREASE_BALANCE.getMatcher(command)).matches()) {
                increaseBalance(matcher);
            } else if (Commands.SHOW_RESTAURANTS.getMatcher(command).matches()) {
                showRestaurants();
            } else if ((matcher = Commands.SHOW_MENU.getMatcher(command)).matches()) {
                showMenu(matcher);
            } else if ((matcher = Commands.ADD_To_CART.getMatcher(command)).matches()) {
                addToCart(matcher);
            } else if ((matcher = Commands.REMOVE_FROM_CART.getMatcher(command)).matches()) {
                removeFromCart(matcher);
            } else if (Commands.SHOW_CART.getMatcher(command).matches()) {
                showCart();
            } else if (Commands.PURCHASE.getMatcher(command).matches()) {
                purchase();
            } else if (Commands.RESTAURANT_MENU.getMatcher(command).matches()) {
                enterRestaurantMenu();
            } else if ((matcher = Commands.OPEN_RESTAURANT.getMatcher(command)).matches()) {
                openRestaurant(matcher);
            } else if (Commands.CLOSE_RESTAURANT.getMatcher(command).matches()) {
                closeRestaurant();
            }
            else {
                System.out.println("invalid command");
            }
        }
    }

    private void showBalance() {
        int balance = user.getBalance();
        System.out.printf("Your balance is: %d\n", balance);
    }

    private void increaseBalance(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));
        user.increaseBalance(amount);
        System.out.println("Balance Increased successfully");
    }

    private void showRestaurants() {
        for (Restaurant restaurant:
             Restaurant.getRestaurants()) {
            System.out.println(restaurant.getName());
        }
    }

    private void showMenu(Matcher matcher) {
        String name = matcher.group("restaurantName");
        Restaurant restaurant = Restaurant.getRestaurantByName(name);
        if (restaurant == null) {
            System.out.println("restaurant not found");
            return;
        }
        for (Food food: restaurant.getMenu()) {
            System.out.printf("type: %s, name: %s, price: %s\n", food.getType().getString(), food.getName(), food.getPrice());
        }
    }

    private void addToCart(Matcher matcher) {
        String restaurantName = matcher.group("restaurantName");
        String foodName = matcher.group("foodName");
        int number = Integer.parseInt(matcher.group("number"));
        Restaurant restaurant = Restaurant.getRestaurantByName(restaurantName);
        if (restaurant == null) {
            System.out.println("restaurant not found");
            return;
        }
        for (Food food:
             restaurant.getMenu()) {
            if (food.getName().equals(foodName)) {
                user.addToCart(food, number);
                System.out.println("added to cart successfully");
                return;
            }
        }
        System.out.println("food not found");
    }

    private void removeFromCart(Matcher matcher) {
        String restaurantName = matcher.group("restaurantName");
        String foodName = matcher.group("foodName");
        int number = Integer.parseInt(matcher.group("number"));
        int result = user.removeFromCart(foodName, restaurantName, number);
        switch (result) {
            case 1 -> System.out.println("no such food in cart");
            case 2 -> System.out.println("not enough food in cart");
            default -> System.out.println("removed successfully");
        }
    }

    private void showCart() {
        HashMap<Food, Integer> cart = user.getCart();
        int total = 0;
        for (Food food:
             cart.keySet()) {
            int price = cart.get(food) * food.getPrice();
            total += price;
            System.out.printf("restaurant: %s, name: %s, count: %d, price: %d\n",
                    food.getRestaurant().getName(), food.getName(), cart.get(food), price);
        }
        System.out.printf("total: %d\n", total);
    }

    private void purchase() {
        boolean result = user.purchase();
        if (result) {
            System.out.println("purchase successful");
        } else {
            System.out.println("purchase failed: inadequate money");
        }
    }

    private void enterRestaurantMenu() {
        if (user.getRestaurant() == null) {
            System.out.println("You should open a restaurant first");
        } else {
            RestaurantMenu restaurantMenu = new RestaurantMenu(scanner, user);
            restaurantMenu.run();
        }
    }

    private void openRestaurant(Matcher matcher) {
        String restaurantName = matcher.group("name");
        Restaurant restaurant = Restaurant.getRestaurantByName(restaurantName);
        if (restaurant != null) {
            System.out.println("this name is already taken");
            return;
        }
        restaurant = new Restaurant(restaurantName, user);
        if (user.setRestaurant(restaurant)) {
            System.out.println("restaurant open successfully");
        } else {
            System.out.println("you already have a restaurant");
        }
    }

    private void closeRestaurant() {
        if (user.closeRestaurant()) {
            System.out.println("closed successfully");
        }
        else {
            System.out.println("You don't have a restaurant");
        }
    }
}
