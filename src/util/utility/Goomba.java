package util.utility;

import util.Enemy;

import java.util.Random;

public class Goomba extends Enemy {
    private int i=0,index=0;
    private static int[] mark={30,240};
    public Goomba(){
        setResource("goombasprite.png");
        setResourcetype("Sprite");
        setImgx(mark[0]);
        setImgy(30);
        setImgxto(mark[0]+160);
        setImgyto(190);
        setS(new Random().nextInt(6)-3);
    }

    @Override
    public void method(){
        i++;
        if (i%15==0) {
            index++;
            setImgx(mark[index % 2]);
            setImgxto(mark[index % 2] + 160);
        }
    }
    @Override
    public void go(){

    }





}
