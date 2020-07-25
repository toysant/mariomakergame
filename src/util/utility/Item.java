package util.utility;

import util.Button;
import util.Elements;

public class Item extends Button {
    Item(String s,int x,int y,int size){
        setResource(s);
        setResourcetype("String");
        setX(x);
        setY(y);
        setSize(size);
        Elements e=new Elements();
        e.setResourcetype("Image");
        e.setResource("white.png");
        e.setSize(650);
        e.setSmul(0);
        e.setTplus(100);
        e.setX(-25);
        e.setY(0);
        setFocusedEffect(e);
    }
}
