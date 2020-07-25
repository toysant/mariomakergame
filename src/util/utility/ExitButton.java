package util.utility;

import util.Button;
import util.Derivative;
import util.InfoPack;

public class ExitButton extends Button {
    public ExitButton(){
        super.setResource("exit");
        super.setResourcetype("String");
        setLength(120);
    }


    @Override
    public Derivative click(InfoPack pack) {
        System.exit(0);
        return null;
    }

    @Override
    public void hover() {

    }
}
