package tools;

import act.Updater;

import javax.swing.event.MouseInputListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;


public class AllCaseListener implements MouseInputListener, KeyListener {
    private Updater updater=null;
//    private int[] op;
//    private int temp=0;
    public AllCaseListener(Updater updater){
        this.updater=updater;
//        op=new int[10];
//        new Thread(()->{
//            while (true){
//                for (int i=0;i<op.length;i++){
//                    switcher(op[i]);
//                }
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        updater.getMode().clicked(e.getButton(),e.getX(),e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        updater.getMode().pressed(e.getButton(),e.getX(),e.getY());
        updater.press(e.getX(),e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        updater.getMode().released(e.getButton(),e.getX(),e.getY());
        updater.click(e.getX(),e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
//        clear();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updater.drag(e.getX(),e.getY());
        updater.getMode().dragged(e.getButton(),e.getX(),e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updater.getMode().moved(e.getButton(),e.getX(),e.getY());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        updater.getMode().switcher(e.getKeyCode(),true);
//        if (e.getKeyCode()!=temp) {
//            temp=e.getKeyCode();
//            add(temp);
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        updater.getMode().switcher(e.getKeyCode(),false);
//        if (e.getKeyCode()==temp){
//            temp=0;
//        }
//        remove(e.getKeyCode());
    }
//    private void clear(){
//        for (int i=0;i<op.length;i++){
//            op[i]=0;
//        }
//    }
//    private void remove(int code){
//        for (int i=0;i<op.length;i++){
//            if (code==op[i]){
//                op[i]=0;
//            }
//        }
//    }
//    private void add(int code){
//        boolean ntoadd=false;
//        int no=0;
//        for (int i=0;i<op.length;i++){
//            if (code==op[i]){
//                ntoadd=true;
//            }
//            if (op[i]==0){
//                no=i;
//            }
//        }
//        if (ntoadd){
//        }
//        else {
//            op[no]=code;
//        }
//    }
}
