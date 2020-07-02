public class Stack implements Container {
    private Node top;
    private int height;

    Stack() {
        top = null;
        height = 0;
    }

    @Override
    public void push(Node n) {
        if (height != 0) {
            n.setPrev(top);
            top.setNext(n);
        }
        top = n;
        ++height;
    }

    @Override
    public String getTop() {
        if (isEmpty())
            return "\0";
        return top.getData();
    }

    @Override
    public void pop() {
        if (height > 1) {
            top.getPrev().setNext(null);
            top = top.getPrev();
            --height;
        } else if (height == 1) {
            top = null;
            --height;
        }
    }

    @Override
    public void print() {
        Node cur = top;
        System.out.printf("Height = %d\n", height);
        System.out.println("Stack:");
        while (cur != null) {
            System.out.println(cur.getData());
            cur = cur.getPrev();
        }
    }

    @Override
    public boolean isEmpty() {
        return height == 0;
    }

    @Override
    public int len() {
        return height;
    }
}
