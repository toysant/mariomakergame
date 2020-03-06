package util.utility;

import util.Enemy;

import java.util.Random;

public class Goomba extends Enemy {
    private int i=0,index=0;
    public Goomba(){
        setResource("goombasprite.png");
        setResourcetype("Sprite");
        setImgx(210);
        setImgy(210);
        setS(new Random().nextInt(6)-3);
    }

    @Override
    public void method(){

    }
    @Override
    public void go(){
            setX(getX() + getSre());
    }





}
