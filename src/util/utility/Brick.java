package util.utility;

import util.Block;
import util.ControllableElements;
import util.Elements;

public class Brick extends Block {
    private int expand,fl;
    boolean pushed=false;
    public Brick(){
        setResource("brick.png");
        setResourcetype("Sprite");
        setImgx(160);
        setImgxto(320);
        setImgy(0);
        setImgyto(160);
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
        super.ispushedup(e);
        if (e instanceof ControllableElements){
            pushed=true;
            fl=10;
            expand=5;
            if (e instanceof Mario){
                if (((Mario)e).isAdult()&&getInside()==null){
                    destroy();
                }

            }

        }
    }

    @Override
    public Elements destroyed() {
        return new BrickDestroyedAnime(getX(),getY(),getSize());
    }

    @Override
    public void destroy() {
        super.destroy();
        expand=0;
        pushed=false;
        fl=0;
    }
}
