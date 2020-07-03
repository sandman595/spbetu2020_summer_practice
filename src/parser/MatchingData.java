package parser;

import algo.GraphNode;
import algo.SemiEdge;

import java.util.ArrayList;

public class MatchingData {
    private ItemData self;
    private ArrayList<ItemData> dataList;

    public MatchingData(Integer id, String name, Integer rhsId, String rhsName) {
        self = new ItemData(id, name);
        dataList = new ArrayList<>();
        dataList.add(new ItemData(rhsId, rhsName));
    }

    public MatchingData(ItemData self, ItemData[] dataList) {
        this.self = self;
        this.dataList = new ArrayList<>();
        for (int i = 0; i < dataList.length; i++){
            this.dataList.add(dataList[i]);
        }
    }

    public GraphNode getKeyNode() {
        return new GraphNode(self.id, self.name);
    }

    public ArrayList<SemiEdge> getAdjacentList(){
        ArrayList<SemiEdge> adjacentList = new ArrayList<>();
        for (ItemData currentItemData : dataList) {
            adjacentList.add(new SemiEdge(new GraphNode(currentItemData.id, currentItemData.name)));
        }
        return adjacentList;
    }

}

