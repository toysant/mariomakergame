package util.utility;

import util.Enemy;
import util.Sprite;

import java.util.Random;

public class Goomba extends Enemy implements Sprite {
    private int i=0,index=0;
    private final int gravity=1;
    public Goomba(){
        setResource("goombasprite.png");
        setResourcetype("Sprite");
        setImgx(210);
        setImgy(210);
        setS(new Random().nextInt(6)-3);
    }

    @Override
    public void method(){
                setX(super.getX()+getS());
                gravity();
                nextStep();
    }
    @Override
    public void istouched() {
    }

    @Override
    public void nextStep() {
        index++;
        if(index%8==0) {
            if(i<1){
            i++;
            }
            else {
                i=0;
            }
        }
    }

    @Override
    public int getStep() {
        return i;
    }
    public void haha(){
        System.out.println("haha");
    }
}
