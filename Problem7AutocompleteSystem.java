import java.util.*;

public class Problem7AutocompleteSystem {
    private Map<String, Integer> frequencyMap = new HashMap<>();

    public void addQuery(String query) {
        frequencyMap.put(query, frequencyMap.getOrDefault(query, 0) + 1);
    }

    public List<String> getSuggestions(String prefix) {
        List<String> result = new ArrayList<>();

        for (String query : frequencyMap.keySet()) {
            if (query.startsWith(prefix)) {
                result.add(query);
            }
        }

        result.sort((a, b) -> frequencyMap.get(b) - frequencyMap.get(a));

        return result.size() > 5 ? result.subList(0, 5) : result;
    }

    public static void main(String[] args) {
        Problem7AutocompleteSystem app = new Problem7AutocompleteSystem();

        app.addQuery("java tutorial");
        app.addQuery("java tutorial");
        app.addQuery("javascript basics");
        app.addQuery("java download");
        app.addQuery("java interview questions");
        app.addQuery("java tutorial");

        System.out.println(app.getSuggestions("jav"));
    }
}