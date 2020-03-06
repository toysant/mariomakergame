package util;


public abstract class ControllableElements extends Elements{
    private int mul;
    private boolean rnD=true,lnD=true;
    private boolean up=false,down=false,right=false,left=false,ctrl=false;
    private Derivative rdirection=new Direct(1),ldirection=new Direct(0);
    class Direct extends Derivative{
        int d;
        Direct(int d){
            setVisible(false);
            setLogical(false);
            this.d=d;
        }
        @Override
        public Derivative deal(Elements e) {
            return null;
        }

        @Override
        public void go() {
            if (d==1){
                setX(ControllableElements.this.getX()+2);
            }
            if (d==0){
                setX(ControllableElements.this.getX()-2);
            }
            setY(ControllableElements.this.getY());
            setSize(ControllableElements.this.getSize());
            setSmul(ControllableElements.this.getSmul());
        }
    }
    public abstract int gameover();
    public String getViewto(){
        return null;
    }
    public String getModeto(){
        return null;
    }
    public int getMul() {
        return mul;
    }

    public void setMul(int mul) {
        this.mul = mul;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isCtrl() {
        return ctrl;
    }

    public void setCtrl(boolean ctrl) {
        this.ctrl = ctrl;
    }

    public Derivative right(boolean status){
        setRight(status);
        if (rnD){
            rnD=false;
            return rdirection;
        }
        else {
            return null;
        }
    }
    public Derivative left(boolean status){
        setLeft(status);
        if (lnD){
            lnD=false;
            return ldirection;
        }
        else {
            return null;
        }
    }

    public Derivative up(boolean status){ setUp(status);return null;}
    public Derivative down(boolean status){ setDown(status);return null;}
    public Derivative ctrl(boolean status){setCtrl(status);return null;}
    public void operate(){

    }

    public Derivative getRdirection() {
        return rdirection;
    }

    public void setRdirection(Derivative rdirection) {
        this.rdirection = rdirection;
    }

    public Derivative getLdirection() {
        return ldirection;
    }

    public void setLdirection(Derivative ldirection) {
        this.ldirection = ldirection;
    }

    @Override
    public void istouchedright(int x,int y){
        setY(y);
        setX(x-1);
        if (getS()>0){
            setS(0);
        }
    }
    @Override
    public void istouchedleft(int x,int y){
        setY(y);
        setX(x+1);
        if (getS()<0){
            setS(0);
        }
    }

    @Override
    public void ispushedup(int x,int y) {
        setY(y);
        setX(x);
        if (getDropv()>0){
            setDropv(0);
        }
    }

    @Override
    public void isstepped(int x,int y) {
        setY(y);
        setX(x);
        if (getDropv()<0){
            setDropv(0);
        }
    }

    @Override
    public void leftre(int sub) {
        if (getS()<0)
        setSre(0);
    }

    @Override
    public void rightre(int sub) {
        if (getS()>0)
        setSre(0);
    }

    @Override
    public void pushedupre(int sub) {
        if (getDropv()>0)
        setDropvre(0);
    }

    @Override
    public void steppedre(int sub) {
        if (getDropv()<0)
        setDropvre(0);
    }
}
