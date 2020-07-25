package util.utility;

import util.Anime;
import util.Derivative;
import util.Elements;

import java.util.Random;

public class BrickDestroyedAnime extends Anime {
    int rangex,rangey,size;
    Random random=new Random();
    public BrickDestroyedAnime(int x,int y,int area){
        rangex=x;
        rangey=y;
        size=area;
        setLifetime(10);
        setResource("***");
        setResourcetype("String");
        setSize(50);
        setX(x);
        setY(y);

    }

    @Override
    public boolean lifetimeover() {
        setLifetime(getLifetime()-1);
        setX(rangex+ random.nextInt(size));
        setY(rangey+ random.nextInt(size));
        if (getLifetime()<=0){
            return true;
        }
        else {
            return false;
        }
    }
}
