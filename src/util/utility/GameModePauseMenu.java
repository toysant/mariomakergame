package util.utility;

import util.Elements;
import util.Menu;

public class GameModePauseMenu extends Menu {
    public GameModePauseMenu(int x,int y){

        setX(x);
        setY(y);
        setSize(300);
        Elements e;
        e=new Elements();
        e.setResource("windows.png");
        e.setResourcetype("Image");
        e.setSize(385);
        e.setSmul(0.85f);
        setBack(e);
        e=new ToEditModeButton();
        e.setX(25);
        e.setY(0);
        e.setSize(100);
        getContain().add(e);
        e=new TitleButton();
        e.setX(25);
        e.setY(100);
        e.setSize(100);
        getContain().add(e);
        e=new ExitButton();
        e.setX(25);
        e.setY(200);
        e.setSize(100);
        getContain().add(e);
    }
}
