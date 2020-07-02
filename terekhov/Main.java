import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("String:");
        String input = in.nextLine();
        StringBuilder output = new StringBuilder();
        Queue q = new Queue();
        Stack s = new Stack();
        char space = 0;
        boolean correct = true;
        boolean lower = Character.isLowerCase(input.charAt(0));
        // push to queue
        for (int i = 0; i < input.length(); ++i) {
            if (!Character.isLetter(input.charAt(i)) || lower != Character.isLowerCase(input.charAt(i))) {
                if (Character.isWhitespace(input.charAt(i))) {
                    lower = !lower;
                } else {
                    System.out.println("Incorrect string!");
                    correct = false;
                    break;
                }
            }
            q.push(new Node(String.valueOf(input.charAt(i))));
        }
        // push words to stack and pop from queue
        while (!q.isEmpty() && correct) {
            while (!q.isEmpty() && !Character.isWhitespace(q.getTop().charAt(0))) {
                s.push(new Node(q.getTop()));
                q.pop();
            }
            space = q.getTop().charAt(0);
            q.pop();
            while (!s.isEmpty()) {
                output.append(s.getTop());
                s.pop();
            }
            output.append(space);
        }
        if (correct) {
            System.out.println("Updated string:");
            System.out.println(output);
        }
    }
}
