package util.utility;

import util.Anime;

public class MushroomAnime extends Anime {
    private  int index=0,i=0;
    public MushroomAnime(){
        setResource("goombasprite.png");
        setResourcetype("Sprite");
        setImgx(210);
        setLifetime(10);
    }
    @Override
    public boolean lifetimeover() {
        if (getLifetime()>0){
            setImgy(210-21*getLifetime());
            setLifetime(getLifetime()-1);
            setY(getY()-getSize()/10);
            return false;
        }
        else {
            setDestroy(true);
            getInsideob().setVisible(true);
            getInsideob().setLogical(true);
            return true;
        }
    }

    @Override
    public void method() {

    }


}
