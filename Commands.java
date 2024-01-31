import java.util.regex.*;

public enum Commands {
    CHANGE_USERNAME(Pattern.compile("change\\s+username\\s+(?<newUsername>.+)")),
    CHANGE_PASSWORD(Pattern.compile("change\\s+password\\s+(?<oldPass>\\S+)\\s+(?<newPass>\\S+)")),
    GET_BALANCE(Pattern.compile("get\\s+balance")),
    REMOVE_ACCOUNT(Pattern.compile("remove\\s+account\\s+(?<pass>\\S+)")),
    PROFILE_MENU(Pattern.compile("profile\\s+menu"));

    private final Pattern pattern;

    Commands(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String text) {
        return pattern.matcher(text);
    }
}
