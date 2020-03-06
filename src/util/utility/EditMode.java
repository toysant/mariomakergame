package util.utility;

import util.*;

import java.util.ArrayList;
import java.util.List;

public class EditMode extends Mode {
    private boolean clicked=false;
    private int index=0;
    private int px,py,prx,pry;
    private int clickn=0;
    private Group tg=null;
    private List<Menu> menuList=new ArrayList<Menu>();

    @Override
    public void construct(ConstructPack constructPack) {
        EditPack e=(EditPack)constructPack;
        setSize(e.getSize());
        setFilename(e.getName());
    }

    @Override
    public void opView(View view) {
        view.setMul(getSize());
        view.setFilename(getFilename());
    }

    public EditMode(){
        setCamera(false);
        setAct(false);
        setMethod(false);
        setViewedit(true);
        setControl(false);
        setGamelogical(false);
        setDelaytime(10);
    }
    @Override
    public void clicked(int b, int x, int y) {
        if (b==1&&getFocus()!=null){

        }
    }
    @Override
    public void pressed(int b, int x, int y) {
        prx=x;
        pry=y;
        if (b==1){
            clickn=b;
                for (int i=0;i<menuList.size();i++){
                    if (menuList.get(i).getX()<x&&menuList.get(i).getY()<y&&menuList.get(i).getX()+menuList.get(i).getSize()>x&&menuList.get(i).getY()+menuList.get(i).sizetotall()>y){
                        clicked=true;
                    }
                }
                menuList.forEach(menu -> {
                    menu.getContain().forEach(elements -> {
                        if (elements.getX()+menu.getX()-menu.getXoffset() < x && elements.getY()+menu.getY()-menu.getYoffset() < y && elements.getSize() + elements.getX()+menu.getX()-menu.getXoffset() > x && elements.sizetotall() + elements.getY()-menu.getYoffset() +menu.getY()> y) {
                            try {
                                setFocus((Elements) Class.forName("util.utility." + elements.getClass().getSimpleName()).newInstance());
                                if (elements instanceof Button){
                                    ((Button)elements).modeOP(this);
                                }
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                });
        }
       else if (b==3){
           clickn=b;
           setFocus(null);
       }
        else {
            clickn=123456;
        }

    }

    @Override
    public void released(int b, int x, int y) {
        if (b==1) {
            if (clicked){
                clicked=false;
            }
            else {
                if (getFocus() == null) {

                } else {
                    if (index > 10) {

                    } else {
                        addv(getElements(((x+getXoffset()) / getSize()) * getSize(), ((y+getYoffset()) / getSize()) * getSize(), getSize()));
                    }
                }
            }

        }
        if (b==3){
            rev(getElements(((x+getXoffset()) / getSize()) * getSize(), ((y+getYoffset()) / getSize()) * getSize(), getSize()));
        }
        if (tg!=null){
            tg=null;
        }
        index=0;
    }

    @Override
    public void dragged(int b, int x, int y) {
        if (clicked){

        }
        else {
            if (getFocus() == null&&clickn==1) {
                index++;
                if (index > 10) {
                    if (getXoffset() + px - x >= 0 && getXoffset() + px - x + getVisix() <= getMaxx()) {
                        setXoffset(getXoffset() + px - x);
                    }
                    if (getYoffset() + py - y >= 0 && getYoffset() + py - y + getVisiy() <= getMaxy()) {
                        setYoffset(getYoffset() + py - y);
                    }
                }
            } else if (clickn==1){
                index++;
//                if (index > 10 && tg == null) {
//                    tg = new Group();
//                    tg.setX((prx / getSize()) * getSize());
//                    tg.setY((pry / getSize()) * getSize());
//                    tg.setSize(getSize());
//                    tg.add(getElements((prx / getSize()) * getSize(), (pry / getSize()) * getSize(), getSize()));
//                    addv((Group) tg);
//                }
//                if (tg != null && (px / getSize() != x / getSize() || py / getSize() != y / getSize())) {
//                    synchronized (tg) {
//                        tg.add(getElements((x / getSize()) * getSize(), (y / getSize()) * getSize(), getSize()));
//                    }
//                }
                if (px / getSize() != x / getSize() || py / getSize() != y / getSize()){
                    addv(getElements(((x +getXoffset())/ getSize()) * getSize(), ((y+getYoffset()) / getSize()) * getSize(), getSize()));
                }

            }
            else if (clickn==3){
                if (px / getSize() != x / getSize() || py / getSize() != y / getSize()){
                    rev(getElements(((x+getXoffset() )/ getSize()) * getSize(), ((y+getYoffset()) / getSize()) * getSize(), getSize()));
                }
            }
        }
        px=x;
        py=y;
    }

    @Override
    public void moved(int b, int x, int y) {

    }
    public void ctrl(boolean status){
        if (!status){
            boolean ct=false;
            for (int i=0;i<menuList.size();i++){
                if (menuList.get(i) instanceof ItemMenu){
                    ct=true;
                    getRemovelist().add(menuList.get(i));
                    menuList.remove(menuList.get(i));
                    break;
                }
            }
            if (!ct) {
                Menu menu = new ItemMenu(100, 800);
                getAddlist().add(menu);
                menuList.add(menu);
            }
        }
    }
    private void esc(boolean status){
        if (!status){
            setPause(!isPause());
            boolean ct=false;
            for (int i=0;i<menuList.size();i++){
                if (menuList.get(i) instanceof EditModePauseMenu){
                    ct=true;
                    getRemovelist().add(menuList.get(i));
                    menuList.remove(menuList.get(i));
                    break;
                }
            }
            if (!ct) {
                Menu menu = new EditModePauseMenu(700, 400);
                getAddlist().add(menu);
                menuList.add(menu);
            }
        }
    }
    @Override
    public void switcher(int code, boolean status) {
        switch (code){
            case 17: ctrl(status);break;
            case 27: esc(status);break;
        }
    }

}
