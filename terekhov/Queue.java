public class Queue implements Container {
    private Node begin;
    private Node end;
    private int length;

    Queue() {
        begin = null;
        end = null;
        length = 0;
    }

    @Override
    public void push(Node n) {
        if (length == 0) {
            begin = n;
        } else {
            n.setPrev(end);
            end.setNext(n);
        }
        end = n;
        ++length;
    }

    @Override
    public void pop() {
        if (length == 1) {
            begin = null;
            end = null;
            length = 0;
        } else if (length != 0) {
            begin.getNext().setPrev(null);
            begin = begin.getNext();
            --length;
        }
    }

    @Override
    public String getTop() {
        if (isEmpty())
            return "\0";
        return begin.getData();
    }

    @Override
    public void print() {
        Node cur = begin;
        System.out.printf("Length = %d\n", length);
        System.out.println("Queue:");
        while (cur != null) {
            System.out.println(cur.getData());
            cur = cur.getNext();
        }
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int len() {
        return length;
    }
}
