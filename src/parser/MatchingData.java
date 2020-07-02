package parser;

import algo.GraphNode;
import algo.SemiEdge;

import java.util.ArrayList;

public class MatchingData {
    private ItemData self;
    private ArrayList<ItemData> pairList;

    public MatchingData(Integer id, String name, Integer rhsId, String rhsName) {
        self = new ItemData(id, name);
        pairList = new ArrayList<>();
        pairList.add(new ItemData(rhsId, rhsName));
    }

    public GraphNode getKeyNode() {
        return new GraphNode(self.id, self.name);
    }

    public ArrayList<SemiEdge> getAdjacentList(){
        ArrayList<SemiEdge> adjacentList = new ArrayList<>();
        for (ItemData currentItemData : pairList) {
            adjacentList.add(new SemiEdge(new GraphNode(currentItemData.id, currentItemData.name)));
        }
        return adjacentList;
    }

}

class ItemData {
    public Integer id;
    public String name;
    public ItemData(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}