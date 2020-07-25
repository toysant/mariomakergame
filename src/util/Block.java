package util;

import java.util.ArrayList;
import java.util.List;

public abstract class Block extends Elements {
    private boolean eject=false;
    private List<Elements> surL=new ArrayList<Elements>();
    public void add(Elements e){
        surL.add(e);
    }
    public void remove(Elements e){
        surL.remove(e);
    }
    public void clearSurl(){
        surL.clear();
    }
    public boolean isEject() {
        return eject;
    }

    public void setEject(boolean eject) {
        this.eject = eject;
    }

    @Override
    public void istouchedright(int x,int y){

    }
    @Override
    public void istouchedleft(int x,int y){

    }

    @Override
    public void ispushedup(int x,int y) {

    }

    @Override
    public void isstepped(int x,int y) {

    }

    @Override
    public void ispushedup(Elements e) {

        if (e instanceof ControllableElements){
            surL.forEach(elements -> {
                elements.chained();
            });
            setInteractive(true);

        }
    }

    @Override
    public void in(Elements e) {
        if (isEject()&&e.isLogical()){
            if (e.getX() < getX() - e.getSize() / 2 + getSize() / 2 ) {
                e.setX(e.getX() - 1);
            } else if (e.getX() >= getX() - e.getSize() / 2 + getSize() / 2) {
                e.setX(e.getX() + 1);
            }
        }
    }

    @Override
    public Elements interact() {
        setInteractive(false);
        if (getInside()!=null) {
            Elements e = null;
            if (!getInside().equals(this.getClass().getSimpleName())) {
                try {
                    e = (Elements) Class.forName("util.utility." + getInside()).newInstance();
                    e.setY(getY() - sizetotall() - 1);
                    e.setX(getX());
                    e.setSize(getSize());
                    e.setLogical(false);
                    e.setVisible(false);
                    if (e instanceof Derivative) {
                        ((Derivative) e).setPlay(true);
                    }
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
            setInside(null);
            return e;
        }
        return null;

    }

}
