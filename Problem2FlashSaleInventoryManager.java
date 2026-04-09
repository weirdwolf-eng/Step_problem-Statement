import java.util.*;

public class Problem2FlashSaleInventoryManager {
    private Map<String, Integer> inventory = new HashMap<>();
    private Queue<Integer> waitingList = new LinkedList<>();

    public Problem2FlashSaleInventoryManager() {
        inventory.put("IPHONE15_256GB", 5);
    }

    public synchronized String purchaseItem(String productId, int userId) {
        int stock = inventory.getOrDefault(productId, 0);

        if (stock > 0) {
            inventory.put(productId, stock - 1);
            return "Success, remaining: " + (stock - 1);
        } else {
            waitingList.offer(userId);
            return "Out of stock, added to waiting list at position " + waitingList.size();
        }
    }

    public int checkStock(String productId) {
        return inventory.getOrDefault(productId, 0);
    }

    public void displayWaitingList() {
        System.out.println("Waiting List: " + waitingList);
    }

    public static void main(String[] args) {
        Problem2FlashSaleInventoryManager app = new Problem2FlashSaleInventoryManager();

        Runnable task = () -> {
            int userId = (int) (Math.random() * 1000);
            System.out.println(Thread.currentThread().getName() + " -> " +
                    app.purchaseItem("IPHONE15_256GB", userId));
        };

        Thread t1 = new Thread(task, "User-1");
        Thread t2 = new Thread(task, "User-2");
        Thread t3 = new Thread(task, "User-3");
        Thread t4 = new Thread(task, "User-4");
        Thread t5 = new Thread(task, "User-5");
        Thread t6 = new Thread(task, "User-6");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Stock: " + app.checkStock("IPHONE15_256GB"));
        app.displayWaitingList();
    }
}