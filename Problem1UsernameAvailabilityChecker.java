import java.util.*;

public class Problem1UsernameAvailabilityChecker {
    private Set<String> usernames = new HashSet<>();
    private Map<String, Integer> attempts = new HashMap<>();

    public boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !usernames.contains(username);
    }

    public void register(String username) {
        usernames.add(username);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            suggestions.add(username + i);
        }
        suggestions.add(username.replace("_", "."));
        return suggestions;
    }

    public String getMostAttempted() {
        String result = "";
        int max = 0;
        for (String key : attempts.keySet()) {
            if (attempts.get(key) > max) {
                max = attempts.get(key);
                result = key;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Problem1UsernameAvailabilityChecker app = new Problem1UsernameAvailabilityChecker();

        app.register("john_doe");

        System.out.println(app.checkAvailability("john_doe"));
        System.out.println(app.checkAvailability("jane_smith"));

        System.out.println(app.suggestAlternatives("john_doe"));

        System.out.println(app.getMostAttempted());
    }
}