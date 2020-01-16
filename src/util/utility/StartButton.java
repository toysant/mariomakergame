package util.utility;

import act.Updater;
import util.Button;
import util.Elements;
import util.View;

public class StartButton extends Button {
    public StartButton(){
        super.setResource("loginbt.jpg");
        super.setResourcetype("Image");
    }
    @Override
    public void click(Updater updater){
        updater.clear();
        updater.view=new View("./res/assembly/well.xml");
        updater.getViewContent(updater.view);
        updater.loader();
    }
}
