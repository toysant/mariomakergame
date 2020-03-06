package util.utility;

import util.Button;

public class Item extends Button {
    Item(String s,int x,int y,int size){
        setResource(s);
        setResourcetype("String");
        setX(x);
        setY(y);
        setSize(size);
    }
}
