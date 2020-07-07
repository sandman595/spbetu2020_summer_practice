package algo;

import parser.ItemData;

import java.util.Objects;

public class GraphNode {
    private ItemData data;

    public GraphNode(ItemData data) {
        this.data = data;
    }

    public Boolean accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public String toString() {
        return data.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode graphNode = (GraphNode) o;
        return data.id.equals(graphNode.data.id) &&
                data.name.equals(graphNode.data.name) &&
                data.photo.equals(graphNode.data.photo);
    }

    @Override
    public int hashCode() {
        return data.photo.hashCode();
    }
}

