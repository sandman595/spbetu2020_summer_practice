package windows;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow(){
        super("Vk Bipartite");
        setSize(320, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel vertical_panel = new JPanel();
        BoxLayout layout = new BoxLayout();
        vertical_panel.setLayout(layout);
        for (int i = 0; i < 2; ++i){
            vertical_panel.add(new JButton("Key " + i));
        }
        vertical_panel.add(new Button("Pizdatoe nazvanie"));
        getContentPane().add(vertical_panel);
        setVisible(true);
    }
}

class Button extends JButton {
    public Button(String name){
        super(name);
        setPreferredSize(new Dimension(10, 40));
//        setSize(new Dimension(10, 40));
    }
}