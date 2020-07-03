package algo;

import java.util.HashSet;

interface Visitor {
    public abstract Boolean visit(GraphNode node);
}
public class NodeVisitor implements Visitor {
    private HashSet<GraphNode> visited;
    private Bipartite graph;

    public NodeVisitor(Bipartite graph) {
        visited = new HashSet<>();
        this.graph = graph;
    }

    public Boolean visit(GraphNode node) {
        if (visited.contains(node))
            return Boolean.FALSE;
        visited.add(node);
        for (SemiEdge currentSemiEdge : graph.getAdjacentList(node)) {
            GraphNode adjacentNode = currentSemiEdge.getNode();
            if (graph.getParent(adjacentNode) == null || graph.getParent(adjacentNode).accept(this)) {
                System.out.println("Been " + node.toString() + " to " + adjacentNode.toString());
                System.out.println("Become inverted!");
                graph.setSecondSideParent(adjacentNode, node);
                graph.setFirstSideParent(node, adjacentNode);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
