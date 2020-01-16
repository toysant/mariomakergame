package util.utility;

import act.Updater;
import util.Button;

public class ExitButton extends Button {
    public ExitButton(){
        super.setResource("exit");
        super.setResourcetype("String");
    }
    @Override
    public void click(Updater updater) {
//        for(int i=0;i<updater.getEnemyList().size();i++){
//            updater.getEnemyList().get(i).destroy();
//        }
        System.exit(0);

    }
}
