package util.utility;

import util.Button;
import util.Derivative;
import util.InfoPack;
import util.Mode;

public class ToGameModeButton extends Button {
    public ToGameModeButton(){
        setResource("toGameMode");
        setResourcetype("String");
        setLength(350);
    }

    @Override
    public Derivative click(InfoPack pack) {
        pack.setModeswitch(true);
        pack.setMode("GameMode");
        return null;
    }

    @Override
    public void modeOP(Mode mode) {
        mode.setSave(true);
    }
}
