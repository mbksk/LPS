import java.util.*;

public class exp4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the production (e.g., A->aB|aC|bD):");
        String production = sc.nextLine().trim();
        leftFactor(production);
        sc.close();
    }

    public static void leftFactor(String production) {
        String[] parts = production.split("->");
        char nonTerminal = parts[0].charAt(0);
        String[] choices = parts[1].split("\\|");

        // Map to store common prefixes and their corresponding productions
        Map<String, List<String>> prefixMap = new HashMap<>();

        // Group productions by their common prefixes
        for (String choice : choices) {
            choice = choice.trim();
            String prefix = getCommonPrefix(choice, choices);
            if (!prefix.isEmpty()) {
                prefixMap.putIfAbsent(prefix, new ArrayList<>());
                prefixMap.get(prefix).add(choice.substring(prefix.length()).trim());
            }
        }

        // Output the left-factored grammar
        if (!prefixMap.isEmpty()) {
            System.out.println(nonTerminal + " -> " + String.join(" | ", prefixMap.keySet()) + nonTerminal + "'");
            for (Map.Entry<String, List<String>> entry : prefixMap.entrySet()) {
                String prefix = entry.getKey();
                List<String> suffixes = entry.getValue();
                StringBuilder suffixBuilder = new StringBuilder();
                for (String suffix : suffixes) {
                    suffixBuilder.append(suffix).append(" | ");
                }
                // Remove the last " | " if present
                if (suffixBuilder.length() > 0) {
                    suffixBuilder.setLength(suffixBuilder.length() - 3);
                }
                System.out.println(nonTerminal + "' -> " + suffixBuilder.toString());
            }
        } else {
            System.out.println(nonTerminal + " has no common prefixes to factor.");
        }
    }

    // Function to find the common prefix of a production with others
    private static String getCommonPrefix(String choice, String[] choices) {
        String prefix = "";
        for (String otherChoice : choices) {
            otherChoice = otherChoice.trim();
            if (otherChoice.equals(choice)) continue; // Skip itself
            int minLength = Math.min(choice.length(), otherChoice.length());
            StringBuilder common = new StringBuilder();
            for (int i = 0; i < minLength; i++) {
                if (choice.charAt(i) == otherChoice.charAt(i)) {
                    common.append(choice.charAt(i));
                } else {
                    break;
                }
            }
            if (common.length() > prefix.length()) {
                prefix = common.toString();
            }
        }
        return prefix;
    }
}
