package parser;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.enums.FriendsOrder;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.groups.responses.GetResponse;
import com.vk.api.sdk.objects.users.User;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Parser {
    private VkApiClient apiClient;
    private ServiceActor serviceActor;
    private UserActor userActor;
    private static Parser instance;

    private Parser() {
        TransportClient transportClient = new HttpTransportClient();
        apiClient = new VkApiClient(transportClient);
        serviceActor = Auth.getServiceActor();
        userActor = Auth.getUserActor();
        try {
            List<Integer> execute = apiClient.groups().get(userActor).count(10).userId(147946476).execute().getItems();
            System.out.println(apiClient.groups().get(userActor).userId(147946476).execute().toPrettyString());
            System.out.println(apiClient.groups().getById(userActor).groupId("179649106").execute());
            for (Integer grpId : execute) {
            }
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }


    public static Parser getInstance() {
        if (instance == null)
            instance = new Parser();
        return instance;
    }


    public List<Integer> getUserFriendsIds(Integer userId, Integer count) throws ClientException, ApiException {
        return apiClient.friends().get(serviceActor)
                .lang(Lang.RU)
                .userId(userId)
                .count(count)
                .order(FriendsOrder.NAME)
                .execute()
                .getItems();
    }

    public List<Integer> getUserCommunities(Integer userId, Integer count) throws ClientException, ApiException {
        return apiClient.groups().get(userActor)
                .lang(Lang.RU)
                .count(count)
                .execute()
                .getItems();
    }

}
