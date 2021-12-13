package image;

import javax.swing.*;
import java.net.URL;

/**
 * @author novo
 * @since 2021/12/10
 */
public class ImageUtil {
    public static ImageIcon player() {
        URL filePath = ImageUtil.class.getResource("/image/player.png");
        return new ImageIcon(filePath);
    }

    public static ImageIcon wumpus() {
        URL filePath = ImageUtil.class.getResource("/image/wumpus.png");
        return new ImageIcon(filePath);
    }

    public static ImageIcon target() {
        URL filePath = ImageUtil.class.getResource("/image/target.png");
        return new ImageIcon(filePath);
    }

    public static ImageIcon hallway(String direction) {
        String path = "/image/hallway-" + direction + ".png";
        URL filePath = ImageUtil.class.getResource(path);
        return new ImageIcon(filePath);
    }

    public static ImageIcon room(String direction) {
        String path = "/image/roombase-" + direction.length() + "-" + direction + ".png";
        URL filePath = ImageUtil.class.getResource(path);
        return new ImageIcon(filePath);
    }

    public static ImageIcon pitNearby() {
        URL filePath = ImageUtil.class.getResource("/image/slime-pit-nearby.png");
        return new ImageIcon(filePath);
    }

    public static ImageIcon pit() {
        URL filePath = ImageUtil.class.getResource("/image/slime-pit.png");
        return new ImageIcon(filePath);
    }

    public static ImageIcon bat() {
        URL filePath = ImageUtil.class.getResource("/image/superbat.png");
        return new ImageIcon(filePath);
    }

    public static ImageIcon wumpusNearby() {
        URL filePath = ImageUtil.class.getResource("/image/wumpus-nearby.png");
        return new ImageIcon(filePath);
    }

    public static ImageIcon empty() {
        URL filePath = ImageUtil.class.getResource("/image/empty.png");
        return new ImageIcon(filePath);
    }

    public static ImageIcon arrow() {
        URL filePath = ImageUtil.class.getResource("/image/arrow.png");
        return new ImageIcon(filePath);
    }



}
