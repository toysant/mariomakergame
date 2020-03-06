package util.utility;

import util.Button;
import util.Derivative;
import util.Elements;
import util.InfoPack;

import java.util.ArrayList;
import java.util.List;

public class EditModeSettingsMenuConfirmButton extends Button {
    private int size;
    List<Elements> linkedList=new ArrayList<Elements>();
    public EditModeSettingsMenuConfirmButton(){
        setResource("Confirm");
        setResourcetype("String");
        setLength(230);
    }
    @Override
    public Derivative click(InfoPack pack) {
        pack.setMode("EditMode");
        linkedList.forEach(elements -> {
            if (elements instanceof StringButton){
                if (((StringButton)elements).getResourcename().equals("maxx")){
                    pack.setMaxx(Integer.parseInt(elements.getResource()));
                    if (pack.getMaxx()>0) {
                        pack.setXoffsetable(true);
                    }
                }
                if (((StringButton)elements).getResourcename().equals("maxy")){
                    pack.setMaxy(Integer.parseInt(elements.getResource()));
                    if (pack.getMaxy()>0) {
                        pack.setYoffsetable(true);
                    }

                }
                if (((StringButton)elements).getResourcename().equals("filename")){
                    pack.setConstructPack(new EditPack(100,"./res/assembly/"+elements.getResource()+".xml"));
                }
            }
        });
        pack.setViewswitch(true);
        pack.setModeswitch(true);
        pack.setView("");
        return null;
    }

    @Override
    public void hover() {

    }
    public void addLinked(Elements e){
        linkedList.add(e);
    }
}
