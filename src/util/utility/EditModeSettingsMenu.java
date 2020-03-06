package util.utility;

import util.*;

public class EditModeSettingsMenu extends Menu {
    public EditModeSettingsMenu(int x,int y){
        setX(x);
        setY(y);
        setSize(1000);
        setSmul(0.8f);
        Elements e;
        e=new Elements();
        e.setResource("Abstract1.jpg");
        e.setResourcetype("Image");
        e.setSize(1000);
        e.setSmul(0.8f);
        setBack(e);
        e=new Button() ;
        e.setX(50);
        e.setY(500);
        e.setSize(100);
        e.setResource("file:");
        e.setResourcetype("String");
        getContain().add(e);
        e=new StringButton();
        e.setX(200);
        e.setY(500);
        e.setSize(100);
        ((StringButton) e).setResourcename("filename");
        getContain().add(e);
        e=new Button() ;
        e.setX(50);
        e.setY(100);
        e.setSize(100);
        e.setResource("x:");
        e.setResourcetype("String");
        getContain().add(e);
        e=new StringButton();
        e.setX(200);
        e.setY(100);
        e.setSize(100);
        ((StringButton) e).setResourcename("maxx");
        getContain().add(e);
        e=new Button() ;
        e.setX(50);
        e.setY(300);
        e.setSize(100);
        e.setResource("y:");
        e.setResourcetype("String");
        getContain().add(e);
        e=new StringButton();
        e.setX(200);
        e.setY(300);
        e.setSize(100);
        ((StringButton) e).setResourcename("maxy");
        getContain().add(e);
        e=new EditModeSettingsMenuConfirmButton();
        e.setX(200);
        e.setY(600);
        e.setSize(100);
        for (int i=0;i<getContain().size();i++){
            ((EditModeSettingsMenuConfirmButton) e).addLinked(getContain().get(i));
        }
        getContain().add(e);
        e=new CancelButton(this);
        e.setX(500);
        e.setY(600);
        e.setSize(100);
        getContain().add(e);
    }

    @Override
    public void dragged(int x, int y) {
        setX(getX()-x);
        setY(getY()-y);
    }
}
