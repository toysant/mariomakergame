package util.utility;

import util.Derivative;
import util.Elements;
import util.Enemy;

import java.awt.*;

public class Tamaco extends Derivative {
    static int speed=20;
    public Tamaco(int x,int y,boolean s){
        setX(x);
        setY(y);
        setResource("*");
        setGram(false);
        setHighspeed(true);
        setSize(2);
        if (s){
            setS(speed);
        }
        else {
            setS(speed*-1);
        }
        setResourcetype("String");
        setColor(new Color(0xF50000));
    }

    @Override
    public int getPaintsize() {
        return getSize()*30;
    }

    @Override
    public Elements deal(Elements e) {
        //System.out.println("hit"+e);

        if (e instanceof Enemy)
            e.destroy();
        return null;
    }

    @Override
    public void go() {

    }

    @Override
    public void istouchedleft(int x, int y) {
       destroy();
    }

    @Override
    public void istouchedright(int x, int y) {
        destroy();
    }

    @Override
    public void ispushedup(int x, int y) {
        destroy();
    }

    @Override
    public void isstepped(int x, int y) {
        destroy();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
