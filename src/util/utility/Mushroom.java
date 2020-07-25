package util.utility;

import util.Block;
import util.Derivative;
import util.Elements;

import java.util.Random;

public class Mushroom extends Derivative {
    private int index=0,i=0;
    public Mushroom(){
        setResource("mushroom.png");
        setResourcetype("Image");
//        setResourcetype("Sprite");
        //setImgx(210);
        //setImgy(210);
        setS((int)(8*Math.pow(-1,new Random().nextInt(1))));
        //setLogical(false);
        setAnimename("MushroomAnime");
        setHighspeed(true);
    }
    @Override
    public Derivative deal(Elements e) {
        if (e instanceof Mario){
            setDestroy(true);
            return ((Mario)e).toadult();
        }
        return null;
    }

    @Override
    public void method(){

    }
    @Override
    public void go(){

    }



    @Override
    public void cling(int line,Elements e) {
        if (getDropvre()==0&&line==1) {
            if (e instanceof Block) {
                ((Block)e).add(this);
            }
        }
    }

    @Override
    public void istouchedleft(int x,int y){
        setY(y);
        setX(x);
        setS(Math.abs(getS()));
    }
    @Override
    public void istouchedright(int x,int y) {
        setY(y);
        setX(x);
        setS(-Math.abs(getS()));
    }
    @Override
    public void ispushedup(int x,int y){
        if (getDropv()>=0) {
            setDropv(0);
        }
        setY(y);
        setX(x);
    }
    @Override
    public void isstepped(int x,int y) {
        setDropv(0);
        setY(y);
        setX(x);
    }
    @Override
    public void leftre(int sub) {
        setSre(Math.abs(sub));

    }

    @Override
    public void chained() {
        setDropv(-20);
    }

    @Override
    public void rightre(int sub) {
        setSre(-Math.abs(sub));
    }

    @Override
    public void pushedupre(int sub) {
        if (getDropvre()>=0) {
            setDropvre(0);
        }
    }

    @Override
    public void steppedre(int sub) {
        setDropv(0);

    }
}
