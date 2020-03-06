package act;


import util.Elements;

import java.util.ArrayList;
import java.util.List;

public class Square {
    private List<Elements> space=new ArrayList<Elements>();


    public void add(Elements e){
        space.add(e);
    }
    public void remove(Elements e){
        space.remove(e);
    }

    public List<Elements> getSpace() {
        return space;
    }
    public void clear(){
        space.clear();
    }
}
