package algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parser.MatchingData;

enum Sides {FIRST, SECOND};

public class Bipartite {
    /**
     *
     *
     */
    private HashMap<GraphNode, ArrayList<SemiEdge>> firstSide;
    private HashMap<GraphNode, ArrayList<SemiEdge>> secondSide;
    private HashMap<GraphNode, Sides> nodeMatching;
    private HashMap<Sides, HashMap<GraphNode, ArrayList<SemiEdge>>> sideMatching;
    private HashMap<GraphNode, GraphNode> resultMatching;

    public Bipartite(ArrayList<MatchingData> usersData, ArrayList<MatchingData> groupData) {
        firstSide = new HashMap<>();
        secondSide = new HashMap<>();
        nodeMatching = new HashMap<>();
        resultMatching = new HashMap<>();
        initializeAll(usersData, groupData);
        sideMatching = new HashMap<>();
        sideMatching.put(Sides.FIRST, firstSide);
        sideMatching.put(Sides.SECOND, secondSide);
    }

    private void initializeAll(ArrayList<MatchingData> usersData, ArrayList<MatchingData> groupData) {
        initializeSide(firstSide, usersData);
        //initializeSide(secondSide, groupData);
        initializeMatching();
        initializeResult(usersData, groupData);
    }

    private void initializeResult(ArrayList<MatchingData> usersData, ArrayList<MatchingData> groupData) {}

    public void setMatching(GraphNode lhs, GraphNode rhs) {
        resultMatching.put(lhs, rhs);
    }

    public GraphNode getMatching(GraphNode node) {
        if (resultMatching.containsKey(node))
            return resultMatching.get(node);
        return null;
    }

    private void initializeMatching() {
        for (GraphNode currentNode : firstSide.keySet()) {
            nodeMatching.put(currentNode, Sides.FIRST);
        }

        for (GraphNode currentNode : secondSide.keySet()) {
            nodeMatching.put(currentNode, Sides.SECOND);
        }
    }

    private void initializeSide(HashMap<GraphNode, ArrayList<SemiEdge>> side, ArrayList<MatchingData> dataList) {
        for (MatchingData data : dataList) {
            GraphNode keyNode = data.getKeyNode();
            ArrayList<SemiEdge> adjacentList = data.getAdjacentList();
            side.put(keyNode, adjacentList);
        }
    }

    private void resetSide(HashMap<GraphNode, ArrayList<SemiEdge>> side) {
        side.clear();
    }

    public HashMap<GraphNode, ArrayList<SemiEdge>> getFirstSide() {
        return firstSide;
    }

    public HashMap<GraphNode, ArrayList<SemiEdge>> getSecondSide() {
        return secondSide;
    }

    public HashMap<GraphNode, Sides> getNodeMatching() {
        return nodeMatching;
    }

    public HashMap<Sides, HashMap<GraphNode, ArrayList<SemiEdge>>> getSideMatching() {
        return sideMatching;
    }

    public ArrayList<SemiEdge> getAdjacentList(GraphNode node) {
        if (!nodeMatching.containsKey(node))
            return new ArrayList<>();
        if (!sideMatching.get(nodeMatching.get(node)).containsKey(node))
            return new ArrayList<>();
        return sideMatching.get(nodeMatching.get(node)).get(node);

    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Bipartite:\n");
        builder.append("\tFirst Side:\n");
        builder.append(sideToString(firstSide));
        builder.append("\tSecond Side:\n");
        builder.append(sideToString(secondSide));

        return builder.toString();
    }

    private String sideToString(HashMap<GraphNode, ArrayList<SemiEdge>> side) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<GraphNode, ArrayList<SemiEdge>> currentPair : side.entrySet()) {
            builder.append("\t\t" + currentPair.getKey().getName());
            builder.append(" : ");
            for (SemiEdge currentSemiEdge : currentPair.getValue()) {
                builder.append(currentSemiEdge.getNode().getName() + " ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    private void prepareParents() {
        resultMatching = new HashMap<>();
        for (int i = 0; i < getNodesCount(); i++) {
            NodeVisitor nodeVisitor = new NodeVisitor(this);
            getIthNode(i).accept(nodeVisitor);
        }

    }

    private GraphNode getIthNode(int i) {
        if (i < firstSide.keySet().size())
            return (GraphNode) firstSide.keySet().toArray()[i];
        return (GraphNode) secondSide.keySet().toArray()[i - firstSide.keySet().size()];
    }

    private int getNodesCount() {
        return firstSide.keySet().size() + secondSide.keySet().size();
    }

    public ArrayList<Edge> getMaxMatching() {
        //resetSide(secondSide);
        prepareParents();
        ArrayList<Edge> result = new ArrayList<>();
        for (Map.Entry<GraphNode, GraphNode> currentEdge : resultMatching.entrySet()) {
            if (currentEdge.getValue() != null)
                result.add(new Edge(currentEdge.getValue(), currentEdge.getKey()));
        }
        return result;
    }

}
