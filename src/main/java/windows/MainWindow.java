package windows;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import parser.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainWindow extends JFrame {
    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints consLayout = new GridBagConstraints();
    private final JLabel greeting = new JLabel("<html><p align=center>VK Bipartite<br>v.0.1</p></html>");
    private final InputPanel inputPanel = new InputPanel();
    private final JButton startButton = new JButton("Start!");
    private static MainWindow instance;

    public static MainWindow getInstance(){
        if (instance == null)
            instance = new MainWindow();
        return instance;
    }

    private MainWindow() {
        super("Vk Bipartite");
        ImageIcon icon = new ImageIcon("res/icon.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setCustomSize();
        setLayout(gbl);
        setGreeting();
        setInputPanel();
        setButtonStart();
        setBackground(Color.white);
        setVisible(true);
    }

    static class StartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                VisualWindow.getInstance();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ClientException clientException) {
                clientException.printStackTrace();
            } catch (ApiException apiException) {
                apiException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void setCustomSize() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(tk.getScreenSize().width / 2 - 300, tk.getScreenSize().height / 2 - 200, 600, 400);
        setMinimumSize(new Dimension(600, 400));
    }

    private void setGreeting() {
        greeting.setHorizontalAlignment(JLabel.CENTER);
        consLayout.anchor = GridBagConstraints.NORTH;
        consLayout.fill = GridBagConstraints.HORIZONTAL;
        consLayout.gridheight = 1;
        consLayout.gridwidth = GridBagConstraints.REMAINDER;
        consLayout.gridx = 1;
        consLayout.gridy = 1;
        consLayout.ipadx = 490;
        consLayout.ipady = 190;
        gbl.setConstraints(greeting, consLayout);
        add(greeting);
    }

    private void setInputPanel() {
        consLayout.gridy = GridBagConstraints.RELATIVE;
        consLayout.gridx = 1;
        consLayout.ipadx = 300;
        consLayout.fill = GridBagConstraints.NONE;
        consLayout.ipady = 10;
        gbl.setConstraints(inputPanel, consLayout);
        add(inputPanel);
    }

    private void setButtonStart() {
        startButton.setBackground(Color.white);
        startButton.setBorder(new RoundedBorder(10));
        startButton.addActionListener(new StartActionListener());
        consLayout.gridwidth = GridBagConstraints.NONE;
        consLayout.gridx = 1;
        consLayout.gridy = GridBagConstraints.RELATIVE;
        consLayout.ipadx = 30;
        consLayout.ipady = 10;
        consLayout.fill = GridBagConstraints.NONE;
        consLayout.insets = new Insets(20, 0, 0, 0);
        gbl.setConstraints(startButton, consLayout);

        add(startButton);
    }
    public String getVkId(){
        return inputPanel.getText();
    }
}

class InputPanel extends JPanel {
    private final JTextField inputLine = new JTextField();

    InputPanel() {
        super();
        setPreferredSize(new Dimension(390, 10));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(50, 10));
        JLabel inputLabel = new JLabel("VK ID:");
        inputLabel.setHorizontalTextPosition(JLabel.RIGHT);
        add(inputLabel);
        inputLine.setPreferredSize(new Dimension(300, 10));
        add(Box.createHorizontalStrut(10));
        add(inputLine);
    }

    public String getText(){
        return inputLine.getText();
    }
}

