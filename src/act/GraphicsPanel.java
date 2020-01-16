package act;

import util.*;
import res.ResourceManager;


import javax.swing.*;
import java.awt.*;
import java.util.List;


public class GraphicsPanel extends JPanel {
     private ResourceManager resourceManager=null;
     private View bufferedview=null;
     private Updater updater=null;
     private long painttime=60;
     private long temp;
     int i=0;
    GraphicsPanel(Updater updater){
        this.updater=updater;
        bufferedview=new View("buffer");
        resourceManager=new ResourceManager();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        temp=System.currentTimeMillis();
        bufferedview.getUniversalelements().addAll(updater.enemyList);
        bufferedview.getUniversalelements().addAll(updater.buttonList);
        bufferedview.getUniversalelements().addAll(updater.blockList);
        this.setBackground(new Color(0xFFFFFF));
        bufferedview.getUniversalelements().forEach(elements -> {
            switch (elements.getResourcetype()){
                case "String":
                g.setColor(new Color((0x000000)));
                g.setFont(new Font("Arial",Font.PLAIN,20));
                g.drawString(elements.getResource(),elements.getX(),elements.getY());
                break;
                case "Image":
                g.drawImage(resourceManager.getImagebyString(elements.getResource()),
                        elements.getX(),elements.getY(),elements.getSize(),elements.getSize(),this);
                break;
                case "Sprite":
                //((Sprite)elements).nextStep();
                g.drawImage(resourceManager.getImagebyString(elements.getResource()),
                        elements.getX(), elements.getY(),
                        elements.getX() + elements.getSize(), elements.getY() + elements.getSize(),
                        elements.getImgx()*((Sprite)elements).getStep(), 0,
                        elements.getImgx()*((Sprite)elements).getStep()+elements.getImgx(), elements.getImgy(), this);
            }
        });
        bufferedview.clear();
        painttime=System.currentTimeMillis()-temp;
    }

    long getPainttime() {
        return painttime;
    }

    void display(){
        repaint();
    }

}
