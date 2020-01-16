package tools;

import act.Updater;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class AllCaseListener implements MouseInputListener{
    private Updater updater=null;
    public AllCaseListener(Updater updater){
        this.updater=updater;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        updater.click(e.getX(),e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
