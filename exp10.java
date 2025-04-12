import java.util.*;
class Main
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an assignment expression (e.g: a=b+c*d): ");
        String input = sc.nextLine().replaceAll("\\s+", "");
        String[] parts = input.split("=");
        if (parts.length != 2) {
            System.out.println("Invalid expression. Format must be: a=b+c*d");
            return;
        }
        String lhs = parts[0];
        String rhs = parts[1];
        String postfix = infixToPostfix(rhs);
        System.out.println("Postfix: " + postfix);
        List<String> tac = generateTAC(postfix);    
        if (!tac.isEmpty()) {
            String lastTemp = tac.get(tac.size() - 1).split(" = ")[0];
            tac.add(lhs + " = " + lastTemp);
        } 
        else {
            tac.add(lhs + " = " + rhs);
        }
        System.out.println("\nGenerated Three-Address Code:");
        for (String line : tac) {
            System.out.println(line);
        }
    }
    public static String infixToPostfix(String expr) 
    {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                output.append(c);
            } 
            else if (c == '(') {
                stack.push(c);
            } 
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(" ").append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(')
                    stack.pop();
            } 
            else {
                output.append(" ");
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    output.append(stack.pop()).append(" ");
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            output.append(" ").append(stack.pop());
        }
        return output.toString().trim();
    }
    public static int precedence(char op) 
    {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }   
    public static List<String> generateTAC(String postfix) 
    {
        List<String> code = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");
        int tempCount = 1;
        for (String token : tokens) {
            if (token.matches("[a-zA-Z0-9]+")) {
                stack.push(token);
            } 
            else {
                String op2 = stack.pop();
                String op1 = stack.pop();
                String temp = "t" + tempCount++;
                code.add(temp + " = " + op1 + " " + token + " " + op2);
                stack.push(temp);
            }
        }
        return code;
    }
}