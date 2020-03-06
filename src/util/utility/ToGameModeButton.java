package util.utility;

import util.Button;
import util.Derivative;
import util.InfoPack;

public class ToGameModeButton extends Button {
    public ToGameModeButton(){
        setResource("toGameMode");
        setResourcetype("String");
        setLength(150);
    }

    @Override
    public Derivative click(InfoPack pack) {
        pack.setModeswitch(true);
        pack.setMode("GameMode");
        return null;
    }
}
