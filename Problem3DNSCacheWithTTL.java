import java.util.*;

class DNSEntry {
    String ip;
    long expiryTime;

    DNSEntry(String ip, long ttl) {
        this.ip = ip;
        this.expiryTime = System.currentTimeMillis() + ttl;
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

public class Problem3DNSCacheWithTTL {
    private Map<String, DNSEntry> cache = new HashMap<>();

    public String resolve(String domain) {
        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);
            if (!entry.isExpired()) {
                return "Cache HIT: " + entry.ip;
            } else {
                cache.remove(domain);
            }
        }

        String ip = queryDNS(domain);
        cache.put(domain, new DNSEntry(ip, 5000));
        return "Cache MISS: " + ip;
    }

    private String queryDNS(String domain) {
        return "192.168." + (int)(Math.random() * 255) + "." + (int)(Math.random() * 255);
    }

    public static void main(String[] args) throws InterruptedException {
        Problem3DNSCacheWithTTL app = new Problem3DNSCacheWithTTL();

        System.out.println(app.resolve("google.com"));
        System.out.println(app.resolve("google.com"));

        Thread.sleep(6000);

        System.out.println(app.resolve("google.com"));
    }
}