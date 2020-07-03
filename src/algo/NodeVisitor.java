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
            if (graph.getMatching(adjacentNode) == null || adjacentNode.accept(this)) {
                graph.setMatching(adjacentNode, node);
                graph.setMatching(node, null);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
