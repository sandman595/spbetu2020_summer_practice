import algo.Bipartite;
import parser.MatchingData;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
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
        System.out.println(bip.toString());
    }
}