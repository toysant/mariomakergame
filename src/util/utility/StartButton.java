package util.utility;

import util.Button;
import util.Derivative;
import util.Elements;
import util.InfoPack;

public class StartButton extends Button {
    public StartButton(){
        super.setResource("start");
        super.setResourcetype("String");
        setLength(150);
    }
    @Override
    public Derivative click(InfoPack pack){
        return null;
    }

    @Override
    public void hover() {

    }

    @Override
    public Elements click() {
        return new FileChosenMenu(300,100);
    }
}
