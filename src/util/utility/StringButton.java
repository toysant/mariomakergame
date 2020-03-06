package util.utility;

import util.Button;
import util.Derivative;
import util.InfoPack;

public class StringButton extends Button {
    String resourcename;

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public StringButton(){
        setResource("AAAAAAAAAAAAAA");
        setLength(550);
        setResourcetype("String");
    }
    @Override
    public Derivative click(InfoPack pack) {
        setResource("");
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
