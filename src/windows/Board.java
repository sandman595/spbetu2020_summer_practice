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
    protected int count = 0;

    BoardNode() {
        super(100, 600);
    }
    public void add(Color clr) {
    }
    public void erase() {
        super.erase();
        count=0;
    }

}

class BoardUser extends BoardNode {
    private final int dy = 110;
    private final int r = 100;

    BoardUser() {
        super();
    }

    @Override
    public void add(Color clr) {
        if (count > 2){
            return;
        }
        Graphics g = getGraphics();
        g.setColor(clr);
        g.fillOval(0, count * dy, r, r);
        ++count;
    }

}


class BoardGroup extends BoardNode {
    private final int dy = 60;
    private final int r = 50;

    BoardGroup() {
        super();
    }

    @Override
    public void add(Color clr) {
        if (count > 2){
            return;
        }
        Graphics g = getGraphics();
        g.setColor(clr);
        g.fillOval(0, count * dy, r, r);
        ++count;
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
        g.drawLine(0, 50+0*110, width, 25+0*60);
        g.drawLine(0, 50+0*110, width, 25+1*60);
        g.drawLine(0, 50+1*110, width, 25+2*60);
        g.drawLine(0, 50+2*110, width, 25+1*60);
    }
}
