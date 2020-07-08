import algo.Bipartite;
import algo.Edge;
import parser.ParserFacade;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import windows.MainWindow;

public class Main {
    public static void main(String[] args){
        ParserFacade parserFacade = new ParserFacade();
        try {
            Bipartite bip = new Bipartite(parserFacade.getMatchingDataList(147946476));
            System.out.println(bip.toString());
            for (Edge currentEdge : bip.getMaxMatching()) {
                System.out.println(currentEdge.toString());
            }
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        MainWindow app = MainWindow.getInstance();
    }
}
