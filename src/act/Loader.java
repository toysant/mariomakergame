package act;


import util.Untill;

import static util.Untill.getUntill;

public class Loader {
    public static void main(String[] args) {
        try {
            Class.forName("util.Untill");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        getUntill().getI();
        getUntill().setI(2);
        Untill untill=new Untill();
        untill.setI(1);
        untill.getUntill().getI();
        untill.getI();
        getUntill().getI();
    }
}
