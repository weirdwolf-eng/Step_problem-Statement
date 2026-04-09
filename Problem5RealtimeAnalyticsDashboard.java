import java.util.*;

class Event {
    String url;
    String userId;
    String source;

    Event(String url, String userId, String source) {
        this.url = url;
        this.userId = userId;
        this.source = source;
    }
}

public class Problem5RealtimeAnalyticsDashboard {
    private Map<String, Integer> pageViews = new HashMap<>();
    private Map<String, Set<String>> uniqueVisitors = new HashMap<>();
    private Map<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(Event e) {
        pageViews.put(e.url, pageViews.getOrDefault(e.url, 0) + 1);

        uniqueVisitors
                .computeIfAbsent(e.url, k -> new HashSet<>())
                .add(e.userId);

        trafficSources.put(e.source, trafficSources.getOrDefault(e.source, 0) + 1);
    }

    public void displayDashboard() {
        System.out.println("Top Pages:");

        List<Map.Entry<String, Integer>> list = new ArrayList<>(pageViews.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        for (int i = 0; i < Math.min(3, list.size()); i++) {
            String url = list.get(i).getKey();
            int views = list.get(i).getValue();
            int unique = uniqueVisitors.get(url).size();
            System.out.println((i + 1) + ". " + url + " - " + views + " views (" + unique + " unique)");
        }

        System.out.println("Traffic Sources:");
        for (String source : trafficSources.keySet()) {
            System.out.println(source + ": " + trafficSources.get(source));
        }
    }

    public static void main(String[] args) {
        Problem5RealtimeAnalyticsDashboard app = new Problem5RealtimeAnalyticsDashboard();

        app.processEvent(new Event("/home", "user1", "Google"));
        app.processEvent(new Event("/home", "user2", "Facebook"));
        app.processEvent(new Event("/sports", "user1", "Google"));
        app.processEvent(new Event("/home", "user1", "Direct"));
        app.processEvent(new Event("/news", "user3", "Google"));
        app.processEvent(new Event("/sports", "user4", "Direct"));

        app.displayDashboard();
    }
}