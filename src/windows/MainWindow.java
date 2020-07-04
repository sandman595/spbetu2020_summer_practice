package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints gblc = new GridBagConstraints();
    private final JLabel greeting = new JLabel("<html><p align=center>VK Bipartite<br>v.0.1</p></html>");
    private final JLabel inputLabel = new JLabel("VK ID:");
    private final JTextField inputLine = new JTextField();
    private final JPanel inputPanel = createInputPanel();
    private final JButton startButton = new JButton("Start!");

    public MainWindow() {
        super("Vk Bipartite");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setCustomSize();
        setLayout(gbl);
        setGreeting();
        setInputLine();
        setButtonStart();
        startButton.addActionListener(new StartActionListener());
        setVisible(true);
        setBackground(Color.white);
    }

    class StartActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new VisualWindow();
        }
    }

    private void setCustomSize() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(tk.getScreenSize().width / 2 - 250, tk.getScreenSize().height / 2 - 200, 500, 400);
        setResizable(false);
    }

    private void setGreeting() {
        greeting.setHorizontalAlignment(JLabel.CENTER);
        gblc.anchor = GridBagConstraints.NORTH;
        gblc.fill = GridBagConstraints.HORIZONTAL;
        gblc.gridheight = 1;
        gblc.gridwidth = GridBagConstraints.REMAINDER;
        gblc.gridx = 1;
        gblc.gridy = 1;
        gblc.ipadx = 490;
        gblc.ipady = 190;
        gbl.setConstraints(greeting, gblc);
        add(greeting);
    }

    private void setInputLine() {
        gblc.gridy = GridBagConstraints.RELATIVE;
        gblc.gridx = 1;
        gblc.ipadx = 300;
        gblc.fill = GridBagConstraints.NONE;
        gblc.ipady = 10;
        gbl.setConstraints(inputPanel, gblc);
        add(inputPanel);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setSize(390, 10);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputLabel.setPreferredSize(new Dimension(50, 10));
        inputLabel.setHorizontalTextPosition(JLabel.RIGHT);
        inputPanel.add(inputLabel);
        inputLine.setPreferredSize(new Dimension(300, 10));
        inputPanel.add(inputLine);
        return inputPanel;
    }

    private void setButtonStart() {
        startButton.setBackground(Color.white);
        startButton.setBorder(new RoundedBorder(10));
        startButton.setPreferredSize(new Dimension(30, 7));
        gblc.gridwidth = GridBagConstraints.NONE;
        gblc.gridx = 1;
        gblc.gridy = GridBagConstraints.RELATIVE;
        gblc.ipadx = 30;
        gblc.ipady = 7;
        gblc.fill = GridBagConstraints.NONE;
        gblc.insets = new Insets(20, 0, 0, 0);
        gbl.setConstraints(startButton, gblc);
        add(startButton);
    }
}
