package util.utility;

import util.Button;
import util.Mode;

public class SaveButton extends Button {
    public SaveButton(){
        setLength(100);
        setSize(100);
        setResource("Save");
        setResourcetype("String");
    }
    @Override
    public void modeOP(Mode mode) {
        mode.setSave(true);
    }
}
