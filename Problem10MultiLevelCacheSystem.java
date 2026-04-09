import java.util.*;

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

public class Problem10MultiLevelCacheSystem {
    private LRUCache<String, String> L1 = new LRUCache<>(3);
    private Map<String, String> L2 = new HashMap<>();
    private Map<String, String> L3 = new HashMap<>();

    public Problem10MultiLevelCacheSystem() {
        L3.put("video1", "Data1");
        L3.put("video2", "Data2");
        L3.put("video3", "Data3");
    }

    public String getVideo(String id) {
        if (L1.containsKey(id)) {
            return "L1 HIT: " + L1.get(id);
        }

        if (L2.containsKey(id)) {
            String data = L2.get(id);
            L1.put(id, data);
            return "L2 HIT → Promoted to L1: " + data;
        }

        if (L3.containsKey(id)) {
            String data = L3.get(id);
            L2.put(id, data);
            return "L3 HIT → Added to L2: " + data;
        }

        return "MISS";
    }

    public void displayCaches() {
        System.out.println("L1: " + L1);
        System.out.println("L2: " + L2);
        System.out.println("L3: " + L3);
    }

    public static void main(String[] args) {
        Problem10MultiLevelCacheSystem app = new Problem10MultiLevelCacheSystem();

        System.out.println(app.getVideo("video1"));
        System.out.println(app.getVideo("video1"));
        System.out.println(app.getVideo("video2"));
        System.out.println(app.getVideo("video2"));
        System.out.println(app.getVideo("video3"));

        app.displayCaches();
    }
}