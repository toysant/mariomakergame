package util.utility;

import util.Button;
import util.Derivative;
import util.Elements;
import util.InfoPack;

import java.awt.*;

public class StringButton extends Button {
    String resourcename;

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public StringButton(){
        setResource("");
        setLength(550);
        setResourcetype("String");
        Elements e=new Elements();
        e.setResourcetype("Image");
        e.setResource("white.png");
        e.setSize(700);
        e.setSmul(0);
        e.setTplus(100);
        e.setX(0);
        e.setY(0);
        setFocusedEffect(e);
    }
    @Override
    public Derivative click(InfoPack pack) {
        //setResource("");
        return null;
    }

    @Override
    public void hover() {

    }

    @Override
    public void focusmethod(int code) {
        StringBuffer stringBuffer=new StringBuffer(getResource());
        if (code!=8) {
            stringBuffer.append((char) code);
        }
        else if (stringBuffer.length()!=0){
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
        }
        setResource(stringBuffer.toString());
    }


}
