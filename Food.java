public class Food {
    private final String name;
    private final FoodType type;
    private int price;
    private int cost;
    private final Restaurant restaurant;

    public Food(String name, FoodType type, int price, int cost, Restaurant restaurant) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.cost = cost;
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public FoodType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void purchase(int count) {
        int profit = count * (price - cost);
        this.restaurant.getManager().increaseBalance(profit);
    }
}
