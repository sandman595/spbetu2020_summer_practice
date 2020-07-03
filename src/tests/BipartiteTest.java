package tests;

import algo.Bipartite;
import algo.Edge;
import parser.ItemData;
import parser.MatchingData;

import java.util.ArrayList;

public class BipartiteTest {

    public static Bipartite getTrivialBipartite() {
        ArrayList<MatchingData> usr = new ArrayList<>();
        ArrayList<MatchingData> grp = new ArrayList<>();
        usr.add(new MatchingData(1, "asd", 2, "ght"));
        usr.add(new MatchingData(3, "vds", 4, "poi"));
        grp.add(new MatchingData(2, "ght", 1, "asd"));
        grp.add(new MatchingData(4, "poi", 3, "vds"));
        Bipartite bip = new Bipartite(
                usr,
                grp
        );
        return bip;
    }

    public static Bipartite getAnotherTrivialBipartite() {
        ArrayList<MatchingData> usr = new ArrayList<>();
        ArrayList<MatchingData> grp = new ArrayList<>();
        usr.add(new MatchingData(new ItemData(1, "1"), new ItemData[] {new ItemData(2, "2")}));
        usr.add(new MatchingData(new ItemData(3, "3"), new ItemData[] {new ItemData(2, "2")}));
        usr.add(new MatchingData(new ItemData(5, "5"), new ItemData[] {new ItemData(6, "6")}));
        grp.add(new MatchingData(new ItemData(2, "2"), new ItemData[] {new ItemData(1, "1"), new ItemData(3, "3")}));
        grp.add(new MatchingData(new ItemData(6, "6"), new ItemData[] {new ItemData(5, "5")}));
        Bipartite bip = new Bipartite(
                usr,
                grp
        );
        return bip;
    }

    public static void createAndPrintSimpleBipartite() {
        Bipartite bip = getAnotherTrivialBipartite();

        System.out.println(bip.toString());
        System.out.println("Max matching:");
        for (Edge currentEdge : bip.getMaxMatching()) {
            System.out.println(currentEdge.toString());
        }
    }
}
