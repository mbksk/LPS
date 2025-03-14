import java.util.Scanner;

public class exp3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the production (e.g., A->Ad|Ae|aB|ac):");
        String production = sc.nextLine().trim();
        eliminateLeftRecursion(production);
        sc.close();
    }

    public static void eliminateLeftRecursion(String production) {
        String[] parts = production.split("->");
        char nonTerminal = parts[0].charAt(0);
        String[] choices = parts[1].split("\\|");

        StringBuilder alpha = new StringBuilder();
        StringBuilder beta = new StringBuilder();


        for (String choice : choices) {
            choice = choice.trim();
            if (choice.startsWith("" + nonTerminal)) {

                alpha.append(choice.substring(1).trim()).append(" | ");
            } else {

                beta.append(choice.trim()).append(" | ");
            }
        }

        if (alpha.length() > 0) {
            alpha.setLength(alpha.length() - 3);
        }
        if (beta.length() > 0) {
            beta.setLength(beta.length() - 3);
        }


        if (alpha.length() > 0) {
            System.out.println(nonTerminal + " -> " + beta + nonTerminal + "'");
            System.out.println(nonTerminal + "' -> " + alpha + nonTerminal+"'| epsilon");
        } else {
            System.out.println(nonTerminal + " is not left recursive.");
        }
    }
}
