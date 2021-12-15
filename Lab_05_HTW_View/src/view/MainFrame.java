package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author novo
 * @since 2021/12/9
 */
public class MainFrame extends JFrame {
    // window parameters
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 300;
    private static final int WINDOW_LOCATION_X = 500;
    private static final int WINDOW_LOCATION_Y = 300;

    private MenuView menuView;

    public MainFrame() throws HeadlessException {
        // game title
        setTitle("Hunt The Wunpus -- Yuxiao");
        // window size
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setLocation(WINDOW_LOCATION_X, WINDOW_LOCATION_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // cannot be resized
        setResizable(false);
        // using flow layout
//        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        // init menu
        menuView = new MenuView();
        this.add(menuView);
        // visible
        setVisible(true);
    }
}
