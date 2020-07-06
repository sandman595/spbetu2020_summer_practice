import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.friends.responses.GetResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;

import parser.Parser;
import tests.BipartiteTest;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        BipartiteTest.createAndPrintSimpleBipartite();
        Parser parser = Parser.getInstance();
        List<Integer> resp = null;
        try {
            resp = parser.getUserFriendsIds(147946476, 5);
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        for (Integer usrId : resp) {
            System.out.println(usrId);
        }
    }
}