package util.utility;

import util.Block;
import util.Elements;

public class SlimPanel extends Block {
    public SlimPanel(){
        setResource("white.png");
        setResourcetype("Image");
    }



    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public int sizetotall() {
        return 200;
    }

//    @Override
//    public int getPaintsize() {
//        return getSize();
//    }
//
//    @Override
//    public int getPainttall() {
//        return sizetotall();
//    }
}
