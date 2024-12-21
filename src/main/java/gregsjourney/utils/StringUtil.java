package gregsjourney.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean checkOredictString(String input, String[] expressions) {
        for (String expression : expressions) {
            if (evaluateExpression(expression, input)) {
                return true;
            }
        }
        return false;
    }

    private static boolean evaluateExpression(String expression, String input) {
        // Convert the expression into tokens for evaluation.
        List<String> tokens = tokenize(expression);
        // Evaluate the tokens with the input string.
        return evaluateTokens(tokens, input);
    }

    private static List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if (c == '&' || c == '|' || c == '!' || c == '(' || c == ')') {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else {
                currentToken.append(c);
            }
        }

        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }

        return tokens;
    }

    private static boolean evaluateTokens(List<String> tokens, String input) {
        Stack<Boolean> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "&", "|" -> {
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                        applyOperator(values, operators.pop());
                    }
                    operators.push(token);
                }
                case "!", "(" -> operators.push(token);
                case ")" -> {
                    while (!operators.isEmpty() && !operators.peek().equals("(")) {
                        applyOperator(values, operators.pop());
                    }
                    operators.pop(); // Remove the '('
                }
                default -> // Operand
                        values.push(evaluateOperand(token, input));
            }
        }

        while (!operators.isEmpty()) {
            applyOperator(values, operators.pop());
        }

        return values.pop();
    }

    private static int precedence(String operator) {
        return switch (operator) {
            case "!" -> 3;
            case "&" -> 2;
            case "|" -> 1;
            default -> 0;
        };
    }

    private static boolean evaluateOperand(String operand, String input) {
        if (operand.contains("*")) {
            String regex = operand.replace("*", ".*");
            return input.matches(regex);
        } else {
            return Objects.equals(input, operand);
        }
    }

    private static void applyOperator(Stack<Boolean> values, String operator) {
        switch (operator) {
            case "!" -> {
                boolean value = values.pop();
                values.push(!value);
            }
            case "&" -> {
                boolean b = values.pop();
                boolean a = values.pop();
                values.push(a && b);
            }
            case "|" -> {
                boolean b = values.pop();
                boolean a = values.pop();
                values.push(a || b);
            }
        }
    }

    public static final Pattern allowedSymbols = Pattern.compile("[a-zA-Z,&|!()* ]+");
}
