public interface Container {
    void push(Node n);

    void pop();

    String getTop();

    boolean isEmpty();

    int len();

    void print();
}
