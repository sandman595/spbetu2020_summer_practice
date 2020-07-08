import windows.MainWindow;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.friends.responses.GetResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;

import parser.Parser;
import tests.BipartiteTest;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        MainWindow app = new MainWindow();
        app.setVisible(true);
    }
}
