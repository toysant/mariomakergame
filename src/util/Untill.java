package util;

public class Untill {
    int i=0;
    static Untill untill;
    public static void haha(){
        System.out.println("haha");
    }
    static {
        System.out.println("loaded");
        untill=new Untill();
    }
    public void wuwu(){
        System.out.println("wuwu");
    }

    public void getI() {
        System.out.println(i);
    }
    public static Untill getUntill(){
        return untill;
    }
    public void setI(int i) {
        this.i = i;
    }
}
