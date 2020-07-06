package parser;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.enums.FriendsOrder;
import com.vk.api.sdk.objects.friends.responses.GetResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Parser {
    private final String CONFIG_PATH = "app.config";
    private VkApiClient apiClient;
    private ServiceActor actor;
    private static Parser instance;

    private Parser() {
        TransportClient transportClient = new HttpTransportClient();
        apiClient = new VkApiClient(transportClient);
        String[] authArgsArray = getAuthArgs();
        actor = new ServiceActor(Integer.valueOf(authArgsArray[0]), authArgsArray[1], authArgsArray[2]);
    }

    public static Parser getInstance() {
        if (instance == null)
            return new Parser();
        return instance;
    }

    private String[] getAuthArgs() {
        Path configFilePath = Paths.get(CONFIG_PATH);
        String[] argsArray = new String[3];
        try(BufferedReader reader = Files.newBufferedReader(configFilePath, Charset.forName("UTF-8"))) {
            for (int i = 0; i < 3; i++) {
                argsArray[i] = reader.readLine();
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return argsArray;
    }

    public List<Integer> getUserFriendsIds(Integer userId, Integer count) throws ClientException, ApiException {
        return apiClient.friends().get(actor)
                .lang(Lang.RU)
                .userId(userId)
                .count(count)
                .order(FriendsOrder.NAME)
                .execute()
                .getItems();
    }

}
