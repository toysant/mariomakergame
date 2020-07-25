package util.utility;

import util.Anime;

public class MushroomAnime extends Anime {
    private  int index=0,i=0;
    public MushroomAnime(){
        setResource("mushroom.png");
        setResourcetype("Image");
        setLifetime(10);

    }
    @Override
    public boolean lifetimeover() {
        if (getLifetime()>0){

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
