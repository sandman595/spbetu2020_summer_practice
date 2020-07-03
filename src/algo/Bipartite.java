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
    private HashMap<GraphNode, GraphNode> firstSideParent;
    private HashMap<GraphNode, GraphNode> secondSideParent;

    public Bipartite(ArrayList<MatchingData> usersData, ArrayList<MatchingData> groupData) {
        firstSide = new HashMap<>();
        secondSide = new HashMap<>();
        nodeMatching = new HashMap<GraphNode, Sides>();
        initializeAll(usersData, groupData);
        sideMatching = new HashMap<>();
        sideMatching.put(Sides.FIRST, firstSide);
        sideMatching.put(Sides.SECOND, secondSide);
        firstSideParent = new HashMap<>();
        secondSideParent = new HashMap<>();
        initializeParents();
    }

    private void initializeAll(ArrayList<MatchingData> usersData, ArrayList<MatchingData> groupData) {
        initializeSide(firstSide, usersData);
        initializeSide(secondSide, groupData);
        initializeMatching();
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

    private void initializeParents() {
        for (GraphNode currentNode : firstSide.keySet()) {
            secondSideParent.put(currentNode, null);
        }
        for (GraphNode currentNode : secondSide.keySet()) {
            firstSideParent.put(currentNode, null);
        }
    }

    public void setFirstSideParent(GraphNode keyNode, GraphNode valueNode) {
        firstSideParent.put(keyNode, valueNode);
    }

    public void setSecondSideParent(GraphNode keyNode, GraphNode valueNode) {
        secondSideParent.put(keyNode, valueNode);
    }

    public GraphNode getParent(GraphNode node) {
        //if (firstSideParent.get(node) == null && secondSideParent.get(node) == null)
        //    return null;
        //if (firstSideParent.get(node) != null)
        //    return firstSideParent.get(node);
        //return secondSideParent.get(node);
        return secondSideParent.get(node);
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
        Boolean canGetAnotherPath = Boolean.TRUE;
        while (canGetAnotherPath) {
            canGetAnotherPath = Boolean.FALSE;
            NodeVisitor nodeVisitor = new NodeVisitor(this);
            for (GraphNode currentNode : firstSide.keySet()) {
                if (getParent(currentNode) == null && currentNode.accept(nodeVisitor))
                    canGetAnotherPath = Boolean.TRUE;
            }
        }

    }

    public ArrayList<Edge> getMaxMatching() {
        printParents();
        prepareParents();
        printParents();
        ArrayList<Edge> result = new ArrayList<>();
        for (Map.Entry<GraphNode, GraphNode> currentEdge : firstSideParent.entrySet()) {
            if (currentEdge.getValue() != null)
                result.add(new Edge(currentEdge.getKey(), currentEdge.getValue()));
        }
        return result;
    }

    public void printParents() {
        System.out.println("Second side parent");
        for (Map.Entry<GraphNode, GraphNode> currentEdge : secondSideParent.entrySet()) {
            System.out.println((currentEdge.getKey() != null ? currentEdge.getKey().getName() : "None")
                    + " : " + (currentEdge.getValue() != null ? currentEdge.getValue().getName() : "None"));
        }
        System.out.println("First side parent");
        for (Map.Entry<GraphNode, GraphNode> currentEdge : firstSideParent.entrySet()) {
            System.out.println((currentEdge.getKey() != null ? currentEdge.getKey().getName() : "None")
                    + " : " + (currentEdge.getValue() != null ? currentEdge.getValue().getName() : "None"));
        }
    }

}
