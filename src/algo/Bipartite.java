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

    public Bipartite(ArrayList<MatchingData> usersData, ArrayList<MatchingData> groupData) {
        firstSide = new HashMap<>();
        secondSide = new HashMap<>();
        nodeMatching = new HashMap<GraphNode, Sides>();
        initializeAll(usersData, groupData);
        sideMatching = new HashMap<>();
        sideMatching.put(Sides.FIRST, firstSide);
        sideMatching.put(Sides.SECOND, secondSide);
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

}
