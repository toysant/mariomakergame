package util.utility;

import util.Button;
import util.Derivative;
import util.InfoPack;

public class ToEditModeButton extends Button {
    public ToEditModeButton(){
        setResource("toEditMode");
        setResourcetype("String");
        setLength(150);
    }

    @Override
    public Derivative click(InfoPack pack) {
        pack.setMode("EditMode");
        pack.setModeswitch(true);
        return null;
    }
}
