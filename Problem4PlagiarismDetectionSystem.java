import java.util.*;

public class Problem4PlagiarismDetectionSystem {
    private Map<String, Set<String>> index = new HashMap<>();

    private List<String> generateNGrams(String text, int n) {
        String[] words = text.split("\\s+");
        List<String> ngrams = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(words[i + j]).append(" ");
            }
            ngrams.add(sb.toString().trim());
        }
        return ngrams;
    }

    public void addDocument(String docId, String text) {
        List<String> ngrams = generateNGrams(text, 3);

        for (String gram : ngrams) {
            index.computeIfAbsent(gram, k -> new HashSet<>()).add(docId);
        }
    }

    public double checkSimilarity(String docId, String text) {
        List<String> ngrams = generateNGrams(text, 3);
        int matchCount = 0;

        for (String gram : ngrams) {
            if (index.containsKey(gram) && !index.get(gram).isEmpty()) {
                matchCount++;
            }
        }

        return (double) matchCount / ngrams.size() * 100;
    }

    public static void main(String[] args) {
        Problem4PlagiarismDetectionSystem app = new Problem4PlagiarismDetectionSystem();

        String doc1 = "this is a simple plagiarism detection system example";
        String doc2 = "this is a plagiarism detection example for testing";

        app.addDocument("doc1", doc1);

        double similarity = app.checkSimilarity("doc2", doc2);

        System.out.println("Similarity: " + similarity + "%");
    }
}
