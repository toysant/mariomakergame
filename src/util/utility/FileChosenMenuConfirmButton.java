package util.utility;

import util.Button;
import util.Derivative;
import util.Elements;
import util.InfoPack;


public class FileChosenMenuConfirmButton extends Button {
    FileChosenMenu fileChosenMenu=null;
    String link=null;
    public FileChosenMenu getFileChosenMenu() {
        return fileChosenMenu;
    }

    public void setFileChosenMenu(FileChosenMenu fileChosenMenu) {
        this.fileChosenMenu = fileChosenMenu;
    }


    public FileChosenMenuConfirmButton(){
        setResource("Confirm");
        setResourcetype("String");
        setLength(230);
    }

    @Override
    public Derivative click(InfoPack pack) {
        if (link!=null) {
            pack.setViewswitch(true);
            pack.setModeswitch(true);
            pack.setMode("GameMode");
            pack.setView("./res/assembly/" + link);
        }
        return null;
    }

    public void click(Elements elements){
        if (elements.getResource()==null){

        }
        else {
            for (int i=0;i<fileChosenMenu.filelist.length;i++){
                if (elements.getResource().equals(fileChosenMenu.filelist[i])){
                    link = elements.getResource();
                }
            }
        }
    }

}
