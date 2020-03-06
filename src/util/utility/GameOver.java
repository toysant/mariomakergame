package util.utility;

import util.Button;
import util.Derivative;
import util.InfoPack;

public class GameOver extends Button {
    public GameOver(){
        super.setResource("gameover");
        super.setResourcetype("String");
    }

    @Override
    public Derivative click(InfoPack pack) {
        return null;
    }

    @Override
    public void hover() {

    }
}
