package algo;

public class GraphNode {
    private Integer id;
    private String name;

    public GraphNode(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Boolean accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public String toString() {
        return name;
    }
}

class UserNode extends GraphNode {

    public UserNode(Integer id, String name) {
        super(id, name);
    }
}

class GroupNode extends GraphNode {

    public GroupNode(Integer id, String name) {
        super(id, name);
    }
}

