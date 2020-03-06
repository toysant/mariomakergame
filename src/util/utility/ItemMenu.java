package util.utility;

import util.Menu;

public class ItemMenu extends Menu {
    public ItemMenu(int x,int y){
        setSize(1000);
        setSmul(0.1f);
        setX(x);
        setY(y);
        setXmlcontain(true);
        setXmlpath("./res/itemmenu.xml");
    }

    @Override
    public void dragged(int x, int y) {
        setXoffset(getXoffset()+x);
    }
}
