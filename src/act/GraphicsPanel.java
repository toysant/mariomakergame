package act;

import util.*;
import res.ResourceManager;
import util.Button;
import util.Menu;
import util.utility.BackPic;


import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;


public class GraphicsPanel extends JPanel {
     private ResourceManager resourceManager=null;
     private View bufferedview=null;
     private Updater updater=null;
     private long painttime=60;
     private long temp;
     private AtomicInteger threadnum=new AtomicInteger(0);
     private Graphics gh;
     GraphicsPanel(Updater updater){
        this.updater=updater;
        bufferedview=new View("buffer");
        resourceManager=new ResourceManager();
    }
    private void switcher(Elements elements,Graphics g){
        if (elements!=null) {
            if (elements.isVisible()) {
                if (elements instanceof Menu){
                    if (((Menu)elements).getBack()!=null) {
                        routeswitch(((Menu) elements).getBack(), g, elements.getX(), elements.getY(), false, null);
                    }
                    ((Menu)elements).getContain().forEach(elements1 -> {
                        if (elements1 instanceof Button){
                            drawFocusEffect(g,elements1,(Menu) elements);
                        }
                        routeswitch(elements1,g,elements.getX(),elements.getY(),true,(Menu) elements);

                    });
                }
                else if (elements instanceof Button){
                    drawFocusEffect(g,elements,null);
                    if (((Button)elements).getBack()!=null){
                        routeswitch(((Button) elements).getBack(), g, elements.getX(), elements.getY(), false, null);
                        routeswitch(elements,g);
                    }
                    else {
                        routeswitch(elements,g);
                    }

                }
                else {
                    routeswitch(elements,g);
                }

            }
        }
    }
    private  void drawFocusEffect(Graphics g,Elements elements,Menu e){

        if (elements.isFocusing()){
            ((Button) elements).focusing();

            if (((Button) elements).getFocusedEffect()!=null) {
                if (e==null) {
                    routeswitch(((Button) elements).getFocusedEffect(), g, elements.getX(), elements.getY(), false, null);
                }
                else {
                    routeswitch(((Button) elements).getFocusedEffect(), g, e.getX()+elements.getX(), e.getY()+elements.getY(), true, e);
                }

                ;
            }

        }
    }
    private void routeswitch(Elements elements,Graphics g,int x,int y,boolean offset,Menu e){
        int xof,yof;
        if (offset){
            xof=e.getXoffset();
            yof=e.getYoffset();
        }
        else {
            xof=0;
            yof=0;
        }
        switch (elements.getResourcetype()) {
            case "String":
//                g.drawLine(elements.getX(),elements.getY(),elements.getX()+elements.getSize(),elements.getY()+elements.sizetotall());
                if (elements.getColor()!=null) {
                    g.setColor(elements.getColor());
                }
                else {
                    g.setColor(new Color(0x000000));
                }
                g.setFont(new Font("Arial", Font.PLAIN, (elements.getPaintsize()) * 2 / 3));
                g.drawString(elements.getResource(), elements.getPaintx() + x-xof, elements.getPainty() + y -yof+ (elements.getPainttall()) * 3 / 4);
                break;
            case "Image":
                g.drawImage(resourceManager.getImagebyString(elements.getResource()),
                        elements.getPaintx() + x-xof, elements.getPainty() + y-yof, elements.getPaintsize(), elements.getPainttall(), this);
                break;
            case "Sprite":
                g.drawImage(resourceManager.getImagebyString(elements.getResource()),
                        elements.getPaintx() +x-xof, elements.getPainty() +y-yof,
                        elements.getPaintx() +x-xof + elements.getPaintsize(), elements.getPainty() +y + elements.getPainttall()-yof,
                        elements.getImgx(), elements.getImgy(), elements.getImgxto(), elements.getImgyto(), this);

        }
    }
    private void routeswitch(Elements elements,Graphics g){
        switch (elements.getResourcetype()) {
            case "String":
//                g.drawLine(elements.getX(),elements.getY(),elements.getX()+elements.getSize(),elements.getY()+elements.sizetotall());
                if (elements.getColor()!=null) {
                    g.setColor(elements.getColor());
                }
                else {
                    g.setColor(new Color(0x000000));
                }
                g.setFont(new Font("Arial", Font.PLAIN, (elements.getPaintsize()) * 2 / 3));
                g.drawString(elements.getResource(), elements.getPaintx() - updater.getXoffset(), elements.getPainty() - updater.getYoffset() + (elements.getPainttall()) * 3 / 4);
                break;
            case "Image":
                g.drawImage(resourceManager.getImagebyString(elements.getResource()),
                        elements.getPaintx() - updater.getXoffset(), elements.getPainty() - updater.getYoffset(), elements.getPaintsize(), elements.getPainttall(), this);
                break;
            case "Sprite":
                //g.drawLine(elements.getPaintx() - updater.getXoffset(), elements.getPainty() - updater.getYoffset(), elements.getPaintx() - updater.getXoffset() + elements.getS() * 20, elements.getPainty() - updater.getYoffset() + elements.getDropv() * 20);
                g.drawImage(resourceManager.getImagebyString(elements.getResource()),
                        elements.getPaintx() - updater.getXoffset(), elements.getPainty() - updater.getYoffset(),
                        elements.getPaintx() - updater.getXoffset() + elements.getPaintsize(), elements.getPainty() - updater.getYoffset() + elements.getPainttall(),
                        elements.getImgx(), elements.getImgy(), elements.getImgxto(), elements.getImgyto(), this);
        }
    }
    class DThread extends Thread{
        int from=0;
        int to=0;
         DThread(int from,int to){
            this.from=from;
            this.to=to;
        }
        @Override
        public void run() {
            for (int i=from;i<to;i++){
                switcher(bufferedview.getUniversalelements().get(i),gh);
            }
            threadnum.decrementAndGet();
        }
    }
    private void getBuffer(){
        if (updater.you!=null) {
            bufferedview.getUniversalelements().add(updater.you);
        }
        bufferedview.getUniversalelements().addAll(updater.derivativeList);
        bufferedview.getUniversalelements().addAll(updater.animeList);
        bufferedview.getUniversalelements().addAll(updater.enemyList);
        bufferedview.getUniversalelements().addAll(updater.buttonList);
        bufferedview.getUniversalelements().addAll(updater.blockList);
        bufferedview.getUniversalelements().addAll(updater.menuList);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.gh=g;
        temp=System.currentTimeMillis();
        getBuffer();
        this.setBackground(new Color(0xFFFFFF));
        if (updater.backPic!=null) {
            g.drawImage(resourceManager.getImagebyString(updater.backPic.getResource()), 0, 0, updater.getConf().getX(), updater.getConf().getY(), this);
        }
        int size=bufferedview.getUniversalelements().size();
        if (size>1000){
//            Spliterator spliterator=bufferedview.getUniversalelements().spliterator();
//            for (int i=0;i<4;i++){
//                new Thread(()->{
//                    spliterator.trySplit().forEachRemaining(new Consumer() {
//                        @Override
//                        public void accept(Object o) {
//                            switcher((Elements)o,g);
//                        }
//                    });
//                    threadnum=threadnum-1;
//                    System.out.println(threadnum);
//                }).start();
//            }
            int num=size/3;
            for (int i=0;size>0;i++){
                threadnum.incrementAndGet();
                new DThread(i*num,Math.min((i+1)*num,i*num+size)).start();
                size=size-num;
            }
            while(threadnum.get()>0){
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            for (int i=0;i<size;i++){
                switcher(bufferedview.getUniversalelements().get(i),g);
            }
        }

        bufferedview.clear();
        painttime=System.currentTimeMillis()-temp;
    }

    long getPainttime() {
        return painttime;
    }
    void display(){
        repaint();
    }
    void waitthread(){
        if (threadnum.get()>0) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitthread();
        }
    }
}
