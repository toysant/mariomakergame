package util.utility;

import util.Button;
import util.Derivative;
import util.Elements;
import util.InfoPack;

public class StartButton extends Button {
    public StartButton(){
        super.setResource("loginbt.jpg");
        super.setResourcetype("Image");
        setSmul(0.5f);
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
