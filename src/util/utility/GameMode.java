package util.utility;

import util.ControllableElements;
import util.Menu;
import util.Mode;

import java.util.ArrayList;
import java.util.List;

public class GameMode extends Mode {
    private List<Menu> menuList=new ArrayList<Menu>();
    public GameMode(){
        setAct(true);
        setCamera(true);
        setMethod(true);
        setViewedit(false);
        setControl(true);
        setGamelogical(true);
        setDelaytime(25);

    }
    public void right(boolean status){
        if (isControl()){
            if (getFocus()!=null){
                getAddlist().add(((ControllableElements)getFocus()).right(status));
            }
        }
    }
    public void left(boolean status){
        if (isControl()){
            if (getFocus()!=null){
                getAddlist().add(((ControllableElements)getFocus()).left(status));
            }
        }
    }
    public void up(boolean status){
        if (isControl()){
            if (getFocus()!=null){
                getAddlist().add(((ControllableElements)getFocus()).up(status));
            }
        }
    }
    public void down(boolean status){
        if (isControl()){
            if (getFocus()!=null){
                getAddlist().add(((ControllableElements)getFocus()).down(status));
            }
        }
    }
    public void ctrl(boolean status){
        if (isControl()){
            if (getFocus()!=null){
                getAddlist().add(((ControllableElements)getFocus()).ctrl(status));
            }
        }
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

    private void esc(boolean status){
        if (!status){
            setPause(!isPause());
            boolean ct=false;
            for (int i=0;i<menuList.size();i++){
                if (menuList.get(i) instanceof GameModePauseMenu){
                    ct=true;
                    getRemovelist().add(menuList.get(i));
                    menuList.remove(menuList.get(i));
                    break;
                }
            }
            if (!ct) {
                Menu menu = new GameModePauseMenu(600, 300);
                getAddlist().add(menu);
                menuList.add(menu);
            }
        }
    }
    @Override
    public void switcher(int code,boolean status){
        switch (code) {
            case 17: ctrl(status);break;
            case 37:
            case 'A':
            case 'a': left(status);break;
            case 39:
            case 'D':
            case 'd':right(status);;break;
            case 38:
            case 'W':
            case 'w':up(status);break;
            case 40:
            case 'S':
            case 's':down(status);break;
            case 27: esc(status);break;
            default:other(code,status);
        }
    }
    public void other(int code,boolean status){
        if (isControl()){
            if (getFocus()!=null){
                getAddlist().add(((ControllableElements)getFocus()).definedKey(code,status));
            }
        }
    }

    @Override
    public int scenelogic() {
        if (getFocus()!=null) {
            return ((ControllableElements) getFocus()).gameover();
        }
        return super.scenelogic();
    }
}
