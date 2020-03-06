package util.utility;

import util.Elements;
import util.Menu;

public class EditModePauseMenu extends Menu {
    EditModePauseMenu(int x,int y){
        setX(x);
        setY(y);
        setSize(400);
        Elements e;
        e=new SaveButton();
        e.setX(0);
        e.setY(0);
        e.setSize(100);
        getContain().add(e);
        e=new ToGameModeButton();
        e.setX(0);
        e.setY(100);
        e.setSize(100);
        getContain().add(e);
        e=new TitleButton();
        e.setX(0);
        e.setY(200);
        e.setSize(100);
        getContain().add(e);
        e=new ExitButton();
        e.setX(0);
        e.setY(300);
        e.setSize(100);
        getContain().add(e);
    }
}
