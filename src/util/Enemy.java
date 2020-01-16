package util;


public abstract class Enemy extends Elements {
    private Thread thread=null;
    private boolean togo=false;
    private boolean ison=false;
    private int gravity=1;
    private int a=0;
    private int s=0;
    public int getA() {
        return a;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public void setIson(boolean ison) {
        this.ison = ison;
    }

    public boolean isIson() {
        return ison;
    }

    public int getGravity() {
        return gravity;
    }

    public void gravity(){
        if (!isIson()) {
            setY(getY() + getA());
            if (getA() < 5) {
                setA(getA() + getGravity());
            }
        }
        else {
            setA(0);
        }
    }
    public boolean isTogo() {
        return togo;
    }

    public void setTogo(boolean togo) {
        this.togo = togo;
    }

    public void setA(int a) {
        this.a = a;
    }
    public  void start(){
        setTogo(true);
        thread=new Thread(()->{
            while (togo){
                method();
                sleep();
            }
        });
        thread.start();
    }
    private void sleep(){
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public abstract void method();
    public abstract void istouched();
    public void destroy(){
        togo=false;
    }

}
