package act;
import util.*;
import util.utility.*;
import res.XMLManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Updater extends Thread {
    private JFrame frame=null;
    public View view=null;
    private XMLManager xmlManager=null;
    private View loadedview=new View("loaded");
    private Filter filter=null;
    List<Button> buttonList=null;
    List<Enemy> enemyList=null;
    List<Block> blockList=null;
    private int xnow=0;
    private int ynow=0;
//    List<Gravity> gravityList=null;
//    List<Elements> removeList=null;
    private Configs conf=new Configs();
    Updater(View view){
        this.view=view;
//        removeList=new ArrayList<Elements>();
        xmlManager=new XMLManager();
        filter=new Filter();
        getViewContent(view);
        initlists();
       // logicalview=view;//testnow
        xmlManager.readconf(conf);
        loader();
    }

    @Override
    public void run() {
//        new Thread(()->{
//            getLists();
//        }).start();
        adjustframe();
        while(true){
            //dogravity();
//            dynamicloader();
            istouched();
//            doremove();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void loader(){
        for (int i=0;i<loadedview.getUniversalelements().size();i++){
            if (loadedview.getUniversalelements().get(i).getX()>xnow-100&&loadedview.getUniversalelements().get(i).getX()<xnow+conf.getX()+100&&loadedview.getUniversalelements().get(i).getY()>ynow-100&&loadedview.getUniversalelements().get(i).getY()<ynow+conf.getY()+100){
                filtelements(loadedview.getUniversalelements().get(i));
            }
            else{
                remove(loadedview.getUniversalelements().get(i));
            }
        }
    }
    private void remove(Elements elements){
        if(elements instanceof Block){
            blockList.remove((Block)elements);
        }
        else if(elements instanceof Button) {
            buttonList.remove((Button)elements);
        }
        else if(elements instanceof Enemy){
            enemyList.remove((Enemy)elements);
            ((Enemy)elements).destroy();
        }
    }
    private void istouched(){
        for (int i=0;i<enemyList.size();i++){
            for (int a=0;a<blockList.size();a++){
                    if (enemyList.get(i).getX()+enemyList.get(i).getSize()>blockList.get(a).getX()&&enemyList.get(i).getX()<blockList.get(a).getX()+blockList.get(a).getSize()){
                        if (enemyList.get(i).getY()+enemyList.get(i).getSize()>blockList.get(a).getY()&&enemyList.get(i).getY()<blockList.get(a).getY()+blockList.get(a).getSize()){
                            if (enemyList.get(i).getA()>0) {
                                blockList.get(a).isstepped();
                                enemyList.get(i).setIson(true);
                                enemyList.get(i).setA(0);
                                enemyList.get(i).setY(blockList.get(a).getY() - enemyList.get(i).getSize());
                            }
                            if (enemyList.get(i).getS()>0){
                                enemyList.get(i).setX(blockList.get(a).getX()-enemyList.get(i).getSize());
                                enemyList.get(i).setS(0-enemyList.get(i).getS());
                            }
                            else if (enemyList.get(i).getS()<0){
                                enemyList.get(i).setX(blockList.get(a).getX()+blockList.get(a).getSize());
                                enemyList.get(i).setS(0-enemyList.get(i).getS());
                            }
                        }
                    }
                    else {
                        enemyList.get(i).setIson(false);
                    }


            }
        }
    }

//    public void dogravity(){
//        for(int i=0;i<gravityList.size();i++){
//            gravityList.get(i).gravity();
//        }
//    }
    public void initlists(){
//        gravityList=new ArrayList<Gravity>();
        enemyList=new ArrayList<Enemy>();
        buttonList=new ArrayList<Button>();
        blockList=new ArrayList<Block>();
    }
    public void filtelements(Elements elements){
        if(elements instanceof Block){
            blockList.add((Block)elements);
        }
        else if(elements instanceof Button) {
            buttonList.add((Button)elements);
        }
        else if(elements instanceof Enemy){
            enemyList.add((Enemy)elements);
            if (((Enemy)elements).isTogo()){

            }
            else {
                ((Enemy)elements).start();
            }
        }
//        if(elements instanceof Gravity){
//            gravityList.add((Gravity)elements);
//        }
    }
    private void viewload(View v){
        if(v.getLayout().equals("GridLayout")){
            filter.gridlayoutinit(v);
        }
        for (int i=0;i<v.getUniversalelements().size();i++){
            if(v.getUniversalelements().get(i) instanceof Group){
                 ((Group)v.getUniversalelements().get(i)).getElementsList().forEach(elements -> loadedview.add(elements));
            }
           else if(v.getUniversalelements().get(i) instanceof Background){
                View tv=new View(v.getUniversalelements().get(i).getResource());
                getViewContent(tv);
            }
            else{
                loadedview.add(v.getUniversalelements().get(i));
            }
        }
    }
    public void clear(){
        enemyList.clear();
        blockList.clear();
        buttonList.clear();
        loadedview.clear();
    }
//    public void doremove(){
//       for (int i=0;i<removeList.size();i++) {
//           if (removeList.get(i) instanceof Button) {
//               buttonList.remove(removeList.get(i));
//           } else if (removeList.get(i) instanceof Enemy) {
//               ((Enemy)removeList.get(i)).destroy();
//               enemyList.remove(removeList.get(i));
//           }
//           else if (removeList.get(i) instanceof Block){
//               blockList.remove(removeList.get(i));
//           }
////           if (removeList.get(i) instanceof Gravity) {
////               gravityList.remove(removeList.get(i));
////           }
//           logicalview.remove(removeList.get(i));
//       }
//        removeList.clear();
//    }

    public void getViewContent(View v) { ;
        xmlManager.read(v);
        viewload(v);
    }

    public void click(int x,int y){
        for (int i=0;i<buttonList.size();i++){
            if(x>buttonList.get(i).getX()&&y>buttonList.get(i).getY()&&x<buttonList.get(i).getX()+100&&y<buttonList.get(i).getY()+100){
                buttonList.get(i).click(this);
            }
        }
    }



    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    public void adjustframe(){
        frame.setTitle(conf.getTitle());
        frame.setSize(conf.getX(),conf.getY());
        Refresher.setFps(conf.getFps());
        //frame.validate();
    }
//    public void remove(Elements elements){
//        removeList.add(elements);
//    }
    public void formatchanger(){
        System.out.println("formatchanger");

    }
}
