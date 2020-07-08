package windows;

import algo.Bipartite;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import parser.ParserFacade;

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
    private Bipartite bip;
    private static VisualWindow instance;

    public static VisualWindow getInstance() throws InterruptedException, ClientException, ApiException, IOException {
        if (instance == null)
            instance = new VisualWindow();
        instance.setVisible(true);
        return instance;
    }

    private VisualWindow() throws IOException, InterruptedException, ClientException, ApiException {
        super();
        bip = new Bipartite(new ParserFacade().getMatchingDataList(147946476));
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
    }

    private void setCustomSize() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(tk.getScreenSize().width / 2 - 350, tk.getScreenSize().height / 2 - 100, 700, 900);
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
        buttonPanel.play.addActionListener((ActionEvent e) -> {
            try {
                userBoard.setNodes(bip.getFirstSide());
                groupBoard.setNodes(bip.getSecondSide());
                edgeBoard.setEdges(bip);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        buttonPanel.step.addActionListener((ActionEvent e) -> {
            edgeBoard.showBipartite(bip);
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