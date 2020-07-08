package windows;

import javax.swing.*;
import java.awt.*;

abstract public class Board extends JPanel {
    Board(int w, int h) {
        super();
        setSize(w, h);
        setBackground(Color.white);
    }

    public void erase() {
        paintComponent(getGraphics());
    }
}

abstract class BoardNode extends Board {
    BoardNode() {
        super(100, 600);
    }
    protected int dy;
    public void add(Node n) {
        Graphics g = getGraphics();
        g.drawImage(n.img.getImage(), 0, dy*n.index, null);
        g.setFont(new Font("Serif", Font.ITALIC, 16));
        g.drawString(n.text, 0, dy*n.index + 110);
    }

    public void erase() {
        super.erase();
    }

}

class BoardUser extends BoardNode {

    BoardUser() {
        super();
        super.dy = 120;
    }
}


class BoardGroup extends BoardNode {
    private final int r = 50;

    BoardGroup() {
        super();
        super.dy = 60;
    }


}

class BoardEdge extends Board {
    private final int width = 300;

    BoardEdge() {
        super(300, 600);
    }

    public void setEdges(Color clr) {
        Graphics g = getGraphics();
        g.setColor(clr);
        g.drawLine(0, 50 + 0 * 110, width, 25 + 0 * 60);
        g.drawLine(0, 50 + 0 * 110, width, 25 + 1 * 60);
        g.drawLine(0, 50 + 1 * 110, width, 25 + 2 * 60);
        g.drawLine(0, 50 + 2 * 110, width, 25 + 1 * 60);
    }
}
