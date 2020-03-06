package util.utility;

import util.Button;
import util.Derivative;
import util.InfoPack;

public class TitleButton extends Button {
    public TitleButton(){
        setResource("title");
        setResourcetype("String");
        setLength(100);
    }

    @Override
    public Derivative click(InfoPack pack) {
        pack.setViewswitch(true);
        pack.setView("./res/start.xml");
        pack.setModeswitch(true);
        pack.setMode("StartMode");
        return null;
    }
}
