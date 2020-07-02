public class Node {
    private final String data;
    private Node next;
    private Node prev;

    Node(String d) {
        data = d;
        next = null;
        prev = null;
    }

    public String getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setNext(Node n) {
        next = n;
    }

    public void setPrev(Node n) {
        prev = n;
    }
}
