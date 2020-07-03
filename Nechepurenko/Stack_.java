import java.util.EmptyStackException;

/**
 * Simple single-linked list stack implementation
 * @param <T> - what kind of data will be placed in stack;
 * @author nechepurenkon
 */
public class Stack_<T> {

    protected class StackNode {
        public T value;
        public StackNode next;

        public StackNode(T value, StackNode next) {
            this.value = value;
            this.next = next;
        }

        public StackNode(T value) {
            this(value, null);
        }
    }

    private StackNode head;
    private StackNode tail;

    public Stack_() {
        head = null;
        tail = null;
    }

    public Boolean isEmpty() {
        return head == null && tail == null;
    }

    public void push(T value) {
        if (isEmpty()) {
            head = tail = new StackNode(value);
            return;
        }
        tail.next = new StackNode(value);
        tail = tail.next;
    }

    public T top() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        return tail.value;
    }

    public void pop() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        if (head == tail) {
            head = tail = null;
            return;
        }
        StackNode iteratorNode = head;
        while (iteratorNode.next != tail) {
            iteratorNode = iteratorNode.next;
        }
        iteratorNode.next = null;
        tail = iteratorNode;
    }

    public void clear() {
        while (!isEmpty())
            pop();
    }

}
