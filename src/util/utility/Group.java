package util.utility;

import util.Elements;

import java.util.ArrayList;
import java.util.List;

public class Group extends Elements{
    private List<Elements> elementsList=null;
    public Group(){
        elementsList=new ArrayList<Elements>();
    }

    public List<Elements> getElementsList() {
        return elementsList;
    }

    public void setElementsList(List<Elements> elementsList) {
        this.elementsList = elementsList;
    }
    public void add(Elements elements){
        elementsList.add(elements);
    }
    public void remove(Elements e){
        elementsList.remove(e);
    }

}
