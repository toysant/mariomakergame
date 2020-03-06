package util.utility;

import util.ConstructPack;

public class EditPack extends ConstructPack {
    private int size;
    private String name;
    EditPack(int size,String name){
        this.size=size;
        this.name=name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
