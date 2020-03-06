package util;

import java.util.ArrayList;
import java.util.List;

public abstract class Mode {
    private boolean method,act,camera,viewedit,control,gamelogical,pause,save=false;
    private String filename;
    private int delaytime;
    private Elements focus=null;
    private List<Elements> addlist=new ArrayList<Elements>();
    private List<Elements> removelist=new ArrayList<Elements>();
    private List<Elements> addview=new ArrayList<Elements>();
    private List<Elements> removeview=new ArrayList<Elements>();
    private int xoffset,yoffset;
    private int maxx,maxy,visix,visiy;
    private int size;
    public void addv(Elements e){
        synchronized (addview) {
            addview.add(e);
        }
    }
    public void rev(Elements e){
        synchronized (removeview) {
            removeview.add(e);
        }
    }
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void construct(ConstructPack constructPack){
    }
    public Elements getElements(int x,int y,int size){
        Elements e=null;
        try {
            if (getFocus()!=null) {
                e = (Elements) Class.forName(getFocus().getClass().getName()).newInstance();
            }
            else {
                e=new Elements();
            }
            e.setX(x);
            e.setY(y);
            e.setSize(size);
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return e;
    }
    public void opView(View view){
        view.setMul(getSize());
        view.setFilename(getFilename());
    }
    public boolean save(){
        return save;
    }
    public void setSave(boolean save){
        this.save=save;
    }
    public List<Elements> getAddview() {
        return addview;
    }

    public void setAddview(List<Elements> addview) {
        this.addview = addview;
    }

    public List<Elements> getRemoveview() {
        return removeview;
    }

    public void setRemoveview(List<Elements> removeview) {
        this.removeview = removeview;
    }

    public int getVisix() {
        return visix;
    }

    public void setVisix(int visix) {
        this.visix = visix;
    }

    public int getVisiy() {
        return visiy;
    }

    public void setVisiy(int visiy) {
        this.visiy = visiy;
    }

    public int getMaxx() {
        return maxx;
    }

    public void setMaxx(int maxx) {
        this.maxx = maxx;
    }

    public int getMaxy() {
        return maxy;
    }

    public void setMaxy(int maxy) {
        this.maxy = maxy;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public int getDelaytime() {
        return delaytime;
    }

    public void setDelaytime(int delaytime) {
        this.delaytime = delaytime;
    }

    public boolean isControl() {
        return control;
    }

    public boolean isGamelogical() {
        return gamelogical;
    }

    public void setGamelogical(boolean gamelogical) {
        this.gamelogical = gamelogical;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

    public List<Elements> getAddlist() {
        return addlist;
    }

    public void setAddlist(List<Elements> addlist) {
        this.addlist = addlist;
    }

    public List<Elements> getRemovelist() {
        return removelist;
    }

    public void setRemovelist(List<Elements> removelist) {
        this.removelist = removelist;
    }

    public int getXoffset() {
        return xoffset;
    }

    public void setXoffset(int xoffset) {
        this.xoffset = xoffset;
    }

    public int getYoffset() {
        return yoffset;
    }

    public void setYoffset(int yoffset) {
        this.yoffset = yoffset;
    }

    public Elements getFocus() {
        return focus;
    }

    public void setFocus(Elements focus) {
        this.focus = focus;
    }

    public boolean isViewedit() {
        return viewedit;
    }

    public void setViewedit(boolean viewedit) {
        this.viewedit = viewedit;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public boolean isAct() {
        return act;
    }

    public void setAct(boolean act) {
        this.act = act;
    }

    public boolean isCamera() {
        return camera;
    }

    public void setCamera(boolean camera) {
        this.camera = camera;
    }

    public boolean isMethod() {
        return method;
    }

    public void setMethod(boolean method) {
        this.method = method;
    }
    public abstract void clicked(int b,int x,int y);
    public abstract void pressed(int b,int x,int y);
    public abstract void released(int b,int x,int y);
    public abstract void dragged(int b,int x,int y);
    public abstract void moved(int b,int x,int y);
    public abstract void switcher(int code,boolean status);

}
