package util.utility;


import util.Menu;
import util.Mode;

import java.util.ArrayList;
import java.util.List;


public class StartMode extends Mode {
    private List<Menu> menuList=new ArrayList<Menu>();
    public StartMode(){
        setAct(true);
        setCamera(false);
        setMethod(true);
        setViewedit(false);
        setControl(false);
        setGamelogical(false);
        setDelaytime(25);
    }

    @Override
    public void clicked(int b, int x, int y) {

    }

    @Override
    public void pressed(int b, int x, int y) {
    }

    @Override
    public void released(int b, int x, int y) {

    }

    @Override
    public void dragged(int b, int x, int y) {

    }

    @Override
    public void moved(int b, int x, int y) {

    }

    @Override
     public void switcher(int code,boolean status){
        if (getFocus() instanceof StringButton&&status){
            getFocus().focusmethod(code);
        }
    }
}
