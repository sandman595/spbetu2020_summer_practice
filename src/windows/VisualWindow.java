package windows;

import javax.swing.*;
import java.awt.*;

public class VisualWindow extends JFrame {
    VisualWindow(){
        super("Visualization");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setCustomSize();
        setVisible(true);
        setBackground(Color.white);
    }

    private void setCustomSize(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(tk.getScreenSize().width / 2 - 350, tk.getScreenSize().height / 2 - 300, 700, 600);
        setResizable(false);
    }

}
