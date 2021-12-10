package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author novo
 * @since 2021/12/9
 */
public class MainFrame extends JFrame {
    private MenuView menuView;

    public MainFrame() throws HeadlessException {
        // game title
        setTitle("Hunt The Wunpus -- Yuxiao");
        // window size
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // using flow layout
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        // init menu
        menuView = new MenuView();
        this.add(menuView);
        // visible
        setVisible(true);
    }



    public static void main(String[] args) {
        new MainFrame();
    }
}
