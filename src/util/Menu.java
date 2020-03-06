package util;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu extends Elements {
    private int xoffset,yoffset;
    private List<Elements> contain=new ArrayList<Elements>();
    private Elements back=null;
    private boolean xmlcontain=false;
    private String xmlpath=null;

    public String getXmlpath() {
        return xmlpath;
    }

    public void setXmlpath(String xmlpath) {
        this.xmlpath = xmlpath;
    }

    public boolean isXmlcontain() {
        return xmlcontain;
    }

    public void setXmlcontain(boolean xmlcontain) {
        this.xmlcontain = xmlcontain;
    }

    public int getXoffset() {
        return xoffset;
    }

    public void setXoffset(int xoffset) {
        this.xoffset = xoffset;
    }

    public int getYoffset() {
        return yoffset;
    }

    public void setYoffset(int yoffset) {
        this.yoffset = yoffset;
    }

    public Elements getBack() {
        return back;
    }

    public void setBack(Elements back) {
        this.back = back;
    }

    public List<Elements> getContain() {
        return contain;
    }

    public void setContain(List<Elements> contain) {
        this.contain = contain;
    }
    public void dragged(int x,int y){

    }
}
