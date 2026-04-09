import java.util.*;

class Transaction {
    int id;
    int amount;
    String merchant;

    Transaction(int id, int amount, String merchant) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
    }
}

public class Problem9TwoSumVariants {

    public List<int[]> twoSum(List<Transaction> transactions, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (Transaction t : transactions) {
            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                result.add(new int[]{map.get(complement), t.id});
            }

            map.put(t.amount, t.id);
        }

        return result;
    }

    public List<String> detectDuplicates(List<Transaction> transactions) {
        Map<String, List<Integer>> map = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (Transaction t : transactions) {
            String key = t.amount + "-" + t.merchant;
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(t.id);
        }

        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                result.add(key + " -> " + map.get(key));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Problem9TwoSumVariants app = new Problem9TwoSumVariants();

        List<Transaction> transactions = Arrays.asList(
                new Transaction(1, 500, "StoreA"),
                new Transaction(2, 300, "StoreB"),
                new Transaction(3, 200, "StoreC"),
                new Transaction(4, 500, "StoreA")
        );

        List<int[]> pairs = app.twoSum(transactions, 500);

        System.out.println("Two Sum Results:");
        for (int[] pair : pairs) {
            System.out.println(pair[0] + " + " + pair[1]);
        }

        System.out.println("Duplicates:");
        System.out.println(app.detectDuplicates(transactions));
    }
}