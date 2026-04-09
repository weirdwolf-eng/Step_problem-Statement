import java.util.*;

class TokenBucket {
    int tokens;
    long lastRefillTime;

    TokenBucket(int capacity) {
        this.tokens = capacity;
        this.lastRefillTime = System.currentTimeMillis();
    }
}

public class Problem6RateLimiter {
    private Map<String, TokenBucket> clients = new HashMap<>();
    private final int MAX_TOKENS = 5;
    private final long REFILL_INTERVAL = 5000;

    public synchronized boolean allowRequest(String clientId) {
        TokenBucket bucket = clients.computeIfAbsent(clientId, k -> new TokenBucket(MAX_TOKENS));

        long now = System.currentTimeMillis();
        long elapsed = now - bucket.lastRefillTime;

        if (elapsed > REFILL_INTERVAL) {
            bucket.tokens = MAX_TOKENS;
            bucket.lastRefillTime = now;
        }

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        Problem6RateLimiter app = new Problem6RateLimiter();

        String client = "client1";

        for (int i = 1; i <= 7; i++) {
            System.out.println("Request " + i + ": " + app.allowRequest(client));
        }

        Thread.sleep(6000);

        System.out.println("After refill:");

        for (int i = 1; i <= 3; i++) {
            System.out.println("Request " + i + ": " + app.allowRequest(client));
        }
    }
}