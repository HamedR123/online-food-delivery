public class Manager extends User {
    Restaurant restaurant;

    public Manager(String username, String password) {
        super(username, password);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
