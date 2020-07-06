package algo;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode graphNode = (GraphNode) o;
        return id.equals(graphNode.id) &&
                name.equals(graphNode.name);
    }

    @Override
    public int hashCode() {
        return id;
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

