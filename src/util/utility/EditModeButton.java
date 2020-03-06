package util.utility;

import util.Button;
import util.Derivative;
import util.Elements;
import util.InfoPack;

public class EditModeButton extends Button {
    public EditModeButton(){
        super.setResource("editmode");
        super.setResourcetype("String");
        setLength(150);
    }
    @Override
    public Derivative click(InfoPack pack) {
        return null;
    }

    @Override
    public void hover() {

    }

    @Override
    public Elements click() {
        return new EditModeSettingsMenu(300,100);
    }
}
