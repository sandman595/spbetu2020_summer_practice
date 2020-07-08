package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VisualWindow extends JDialog {
    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints consLayout = new GridBagConstraints();
    private final BoardUser userBoard = new BoardUser();
    private final BoardEdge edgeBoard = new BoardEdge();
    private final BoardGroup groupBoard = new BoardGroup();
    private final ButtonPanel buttonPanel = new ButtonPanel();

    VisualWindow() throws IOException {
        super();
        ImageIcon icon = new ImageIcon("res/icon.png");
        setModal(true);
        setTitle("Visualization");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBackground(Color.lightGray);
        setCustomSize();
        setUserBoard();
        setEdgeBoard();
        setGroupBoard();
        setButtonPanel();
        setLayout(gbl);
        setVisible(true);
    }

    private void setCustomSize() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(tk.getScreenSize().width / 2 - 350, tk.getScreenSize().height / 2 - 300, 700, 600);
        setResizable(false);
    }

    private void setUserBoard() {
        consLayout.anchor = GridBagConstraints.WEST;
        consLayout.fill = GridBagConstraints.VERTICAL;
        consLayout.gridheight = GridBagConstraints.REMAINDER;
        consLayout.gridwidth = 1;
        consLayout.gridx = 1;
        consLayout.gridy = 1;
        consLayout.insets = new Insets(0, 0, 0, 0);
        consLayout.ipadx = userBoard.getWidth();
        consLayout.ipady = userBoard.getHeight();
        gbl.setConstraints(userBoard, consLayout);
        add(userBoard);
//        userBoard.setBackground(Color.BLUE);
    }

    private void setEdgeBoard() {
        consLayout.gridx = 2;
        consLayout.gridy = 1;
        consLayout.ipadx = edgeBoard.getWidth();
        consLayout.ipady = edgeBoard.getHeight();
        gbl.setConstraints(edgeBoard, consLayout);
        add(edgeBoard);
//        edgeBoard.setBackground(Color.RED);
    }

    private void setGroupBoard() {
        consLayout.gridx = 3;
        consLayout.gridy = 1;
        consLayout.ipadx = groupBoard.getWidth();
        consLayout.ipady = groupBoard.getHeight();
        gbl.setConstraints(groupBoard, consLayout);
        add(groupBoard);
//        groupBoard.setBackground(Color.GREEN);
    }

    private void setButtonPanel() throws IOException {
        consLayout.gridx = 4;
        consLayout.gridy = 1;
        consLayout.insets = new Insets(0, 10, 0, 0);
        consLayout.ipadx = buttonPanel.getWidth();
        consLayout.ipady = buttonPanel.getHeight();
        gbl.setConstraints(buttonPanel, consLayout);
        add(buttonPanel);
        GraphNode n = new GraphNode("https://sun9-8.userapi.com/impf/c851036/v851036709/bda4/RB2denXpiSk.jpg?size=200x0&quality=90&sign=2f103127343674f44c2548671cd49ad0&ava=1", "Санех", 0, GraphNode.Side.LEFT);
        GraphNode n2 = new GraphNode("https://sun9-61.userapi.com/c858420/v858420103/a71a3/npqvpQOn8ys.jpg", "Илюх", 1, GraphNode.Side.LEFT);

        buttonPanel.play.addActionListener((ActionEvent e) -> {
            userBoard.add(n);
            userBoard.add(n2);
        });
        buttonPanel.step.addActionListener((ActionEvent e) -> {
        });
    }
}

class ButtonPanel extends JPanel {
    JButton step = new JButton("Step");
    JButton play = new JButton("Play");

    ButtonPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(50, 10));
        step.setBorder(new RoundedBorder(10));
        play.setBorder(new RoundedBorder(10));
        add(step);
        add(Box.createHorizontalStrut(10));
        add(play);
    }

}