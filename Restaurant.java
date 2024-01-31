import java.util.ArrayList;

public class Restaurant {
    private static final ArrayList<Restaurant> restaurants = new ArrayList<>();
    private String name;
    private final User manager;
    private final ArrayList<Food> menu;

    public Restaurant(String name, User manager) {
        this.name = name;
        this.manager = manager;
        menu = new ArrayList<>();
        restaurants.add(this);
    }

    public static ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public static Restaurant getRestaurantByName(String restaurantName) {
        for (Restaurant restaurant:
             restaurants) {
            if (restaurantName.equals(restaurant.name)) {
                return restaurant;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getManager() {
        return manager;
    }

    public ArrayList<Food> getMenu() {
        return menu;
    }

    public boolean addFood(Food newFood) {
        for (Food food:
             menu) {
            if (food.getName().equals(newFood.getName())) {
                return false;
            }
        }
        menu.add(newFood);
        return  true;
    }

    public boolean removeFood(String foodName) {
        for (Food food:
             menu) {
            if (food.getName().equals(foodName)) {
                menu.remove(food);
                return true;
            }
        }
        return false;
    }

    public void removeRestaurant() {
        restaurants.remove(this);
    }
}
