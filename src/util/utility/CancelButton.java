package util.utility;

import util.Button;
import util.Derivative;
import util.InfoPack;
import util.Menu;

public class CancelButton extends Button {
    private Menu menu;
    public CancelButton(Menu menu){
        setResource("Cancel");
        setResourcetype("String");
        this.menu=menu;
        setLength(230);

    }

    @Override
    public Derivative click(InfoPack pack) {
        menu.setDestroy(true);
        return null;
    }
}
