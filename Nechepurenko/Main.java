import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        String resultString = getPairIndexes(inputString);
        System.out.println(resultString);
    }

    /**
     *
     * @param inputString - () balanced string;
     * @return answer in format "1 9; ..."
     *               for string "(abacaba)..."
     */
    private static String getPairIndexes(String inputString) {
        Stack_<Integer> stack = new Stack_<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == '(')
                stack.push(i+1);                          // use i + 1 here
            if (!stack.isEmpty() && inputString.charAt(i) == ')'){
                int openIndex = stack.top(); stack.pop();
                builder.append(openIndex + " " + (i+1) + "; "); // and there to make 1-based indexing;
            }
        }

        return builder.toString();
    }
}