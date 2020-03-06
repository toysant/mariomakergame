package act;

import util.*;
import tools.AllCaseListener;

import javax.swing.*;

public class MainActivity {
    public static void main(String[] args) {
        View view=new View("./res/start.xml");
        Updater updater=new Updater(view);
        JFrame frame=new JFrame();
        AllCaseListener listener=new AllCaseListener(updater);
        GraphicsPanel panel=new GraphicsPanel(updater);
        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);
        frame.addKeyListener(listener);
        Refresher refresher=new Refresher(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        updater.setFrame(frame);
        frame.setVisible(true);
        refresher.start();
        updater.start();
    }

}
