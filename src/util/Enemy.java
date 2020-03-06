package util;



public abstract class Enemy extends Elements {
    abstract public void go();

    @Override
    public void istouchedright(int x,int y) {
            setY(y);
            setX(x);
            setS(-Math.abs(getS()));
    }

    @Override
    public void chained() {
        setDestroy(true);
    }

    @Override
    public void istouchedright(Elements e) {
        if (e instanceof ControllableElements){
            e.destroy();
        }
    }
    @Override
    public void cling(int line,Elements e) {
        if (getDropvre()==0&&line==1) {
            if (e instanceof Block) {
                ((Block)e).add(this);
            }
        }
    }

    @Override
    public void istouchedleft(int x,int y){
            setY(y);
            setX(x);
            setS(Math.abs(getS()));
    }

    @Override
    public void istouchedleft(Elements e) {
        if (e instanceof ControllableElements){
            e.destroy();
        }
    }

    @Override
    public void ispushedup(int x,int y){
                setDropv(0);
                setY(y);
                setX(x);
    }

    @Override
    public void ispushedup(Elements e) {
        if (e instanceof ControllableElements){
            e.destroy();
        }
    }

    @Override
    public void isstepped(int x,int y) {
                setDropv(0);
                setY(y);
                setX(x);
    }

    @Override
    public void isstepped(Elements e) {
        if (e instanceof ControllableElements){
            e.setDropv(-20);
            setDestroy(true);
        }
    }

    @Override
    public void leftre(int sub) {
        setSre(Math.abs(sub));

    }

    @Override
    public void rightre(int sub) {
        setSre(-Math.abs(sub));
    }

    @Override
    public void pushedupre(int sub) {
        setDropvre(0);
    }

    @Override
    public void steppedre(int sub) {
        setDropv(0);

    }
}
