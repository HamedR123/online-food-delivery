import java.util.regex.*;

public enum Commands {
    CHANGE_USERNAME(Pattern.compile("change\\s+username\\s+(?<newUsername>.+)")),
    CHANGE_PASSWORD(Pattern.compile("change\\s+password\\s+(?<oldPass>\\S+)\\s+(?<newPass>\\S+)")),
    REMOVE_ACCOUNT(Pattern.compile("remove\\s+account\\s+(?<pass>\\S+)")),
    PROFILE_MENU(Pattern.compile("profile\\s+menu")),
    SHOW_BALANCE(Pattern.compile("show\\s+balance")),
    INCREASE_BALANCE(Pattern.compile("increase\\s+balance\\s+(?<amount>\\d+)")),
    SHOW_RESTAURANTS(Pattern.compile("show\\s+restaurants")),
    SHOW_MENU(Pattern.compile("show\\s+menu\\s+(?<restaurantName>\\S+)")),
    ADD_To_CART(Pattern.compile("add\\s+to\\s+cart\\s+(?<restaurantName>\\S+)\\s+(?<foodName>\\S+)\\s+(?<number>\\d+)")),
    REMOVE_FROM_CART(Pattern.compile("remove\\s+from\\s+cart\\s+(?<restaurantName>\\S+)\\s+(?<foodName>\\S+)\\s+(?<number>\\d+)")),
    SHOW_CART(Pattern.compile("show\\s+cart")),
    PURCHASE(Pattern.compile("purchase")),
    ADD_FOOD(Pattern.compile("add\\s+food\\s+(?<name>\\S+)\\s+(?<type>\\S+)\\s+(?<price>\\d+)\\s+(?<cost>\\d+)")),
    REMOVE_FOOD(Pattern.compile("remove\\s+food\\s+(?<name>\\S+)")),
    RESTAURANT_MENU(Pattern.compile("restaurant menu")),
    OPEN_RESTAURANT(Pattern.compile("open\\s+restaurant\\s+(?<name>\\S+)")),
    CLOSE_RESTAURANT(Pattern.compile("close\\s+restaurant"));

    private final Pattern pattern;

    Commands(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String text) {
        return pattern.matcher(text);
    }
}
