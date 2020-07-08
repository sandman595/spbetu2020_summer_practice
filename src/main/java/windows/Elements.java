package windows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


class GraphNode {
    enum Side {
        LEFT,
        RIGHT
    }

    private ImageIcon img;
    private String name;
    private int id;

    GraphNode(String _url, String _name, int _id, Side _side) throws IOException {
        URL img_url = new URL(_url);
        BufferedImage tmp_img = ImageIO.read(img_url);
        if (_side == Side.LEFT) {
            tmp_img = tmp_img.getSubimage(tmp_img.getWidth() / 2 - 50, tmp_img.getHeight() / 2 - 50, 100, 100);
        } else if (_side == Side.RIGHT) {
            tmp_img = tmp_img.getSubimage(tmp_img.getWidth() / 2 - 25, tmp_img.getHeight() / 2 - 25, 50, 50);
        }
        img.setImage(tmp_img);
        name = _name;
        id = _id;
    }
}

