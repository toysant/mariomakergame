package util.utility;

import util.Block;
import util.ControllableElements;
import util.Elements;

public class Grassblock extends Block {
    private int expand,fl;
    boolean pushed=false;
    public Grassblock(){
        setResource("Abstract1.jpg");
        setResourcetype("Image");
        setEject(true);
    }

    @Override
    public void method() {
        if (pushed){
            fl--;
            if (fl%2==0){
                expand--;
            }
            if (fl==0){
                pushed=false;
            }
        }
    }

    @Override
    public int getPaintsize() {
        return getSize()+expand*2;
    }

    @Override
    public int getPaintx() {
        return getX()-expand;
    }

    @Override
    public int getPainty() {
        return getY()-fl;
    }

    @Override
    public int getPainttall() {
        return (int)(getPaintsize()*getSmul()+getTplus());
    }

    @Override
    public void ispushedup(Elements e) {
        if (e instanceof ControllableElements){
            pushed=true;
            fl=10;
            expand=5;
        }
    }
}
