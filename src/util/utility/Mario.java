package util.utility;

import util.ControllableElements;
import util.Derivative;
import util.Elements;

public class Mario extends ControllableElements {
    int ui=50;
    private static int life=3;
    private boolean onground=false;
    private boolean squat=false,unstoppable=false;
    private Derivative d=null;
    private int setmaxs=7;
    int index=0,i=0,b=6,opi;
    int[] mark={3,34,65,96,155,1,32,63,94,155,186};
    boolean adult=false;
    public Mario(){
        setResource("mario.png");
        setImgx(3);
        setImgxto(17);
        setImgyto(17);
        setSplus(-18);
        setSmul(17f/14f);
        setResourcetype("Sprite");
        setMaxs(setmaxs);
    }
    class Stand extends Derivative{
        Stand(){
            setVisible(false);
            setLogical(false);
        }
        @Override
        public Derivative deal(Elements e) {
            return null;
        }

        @Override
        public void go() {
            setX(Mario.this.getX());
            setY(Mario.this.getY()-45);
            setSize(Mario.this.getSize());
            setSmul(Mario.this.getSmul());
        }
    }

    @Override
    public int gameover() {
        if (isDestroy()&&!unstoppable) {
            if (!adult) {
                if (life > 0) {
                    life--;
                    return 1;
                }
                if (life == 0) {
                    life = 3;
                    return 0;
                }
            } else {
                    tochild();
            }
            unstoppable=true;
            ui=50;
        }
        if (unstoppable){
            setDestroy(false);
            ui--;
            if (ui%4==0){
                setVisible(!isVisible());
            }
            if (ui==0){
                unstoppable=false;
                setVisible(true);
            }
        }
        return 9;
    }
    @Override
    public Derivative down(boolean status) {
        if (adult) {
            setDown(status);
            if (d == null) {
                d = new Stand();
                return d;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public void method() {
        if (!adult){
            child();
        }
        else {
            adult();
        }
        index++;
    }
    private void adult(){
        if (!squat) {
            setImgy(56);
            setImgyto(90);
            if (getS() < 0) {
                if (isOnground()) {
                    if (index % (30 / getS()) == 0) {
                        setImgx(mark[i % 3 + 6] + 16);
                        setImgxto(mark[i % 3 + 6]);
                        i++;
                    }
                } else {
                    setImgxto(mark[9]);
                    setImgx(mark[9] + 16);
                }
            }
            if (getS() > 0) {
                if (isOnground()) {
                    if (index % (30 / getS()) == 0) {
                        setImgx(mark[i % 3 + 6]);
                        setImgxto(mark[i % 3 + 6] + 16);
                        i++;
                    }
                } else {
                    setImgxto(mark[9] + 16);
                    setImgx(mark[9]);
                }
            }
            if (getS() == 0) {
                if (isOnground()) {
                    if (belong(getImgx())) {
                        setImgx(1);
                        setImgxto(17);
                    } else {
                        setImgx(17);
                        setImgxto(1);
                    }
                } else {
                    if (belong(getImgx())) {
                        setImgxto(mark[9] + 16);
                        setImgx(mark[9]);
                    } else {
                        setImgxto(mark[9]);
                        setImgx(mark[9] + 16);
                    }
                }
            }
        }
        else {
            setImgy(62);
            setImgyto(86);
            if (getS()>0) {
                setImgx(mark[10]);
                setImgxto(mark[10]+17);
            } else if ((getS()<0)){
                setImgx(mark[10]+17);
                setImgxto(mark[10]);
            }
            if (getS()==0){
                if (belong(getImgx())){
                    setImgx(mark[10]);
                    setImgxto(mark[10]+17);
                }
                else {
                    setImgx(mark[10]+17);
                    setImgxto(mark[10]);
                }
            }
        }
    }
    public Derivative toadult(){
        if (!adult) {
            setSmul(7 / 4f);
            setImgy(56);
            setImgyto(90);
            adult = true;
            squat=true;
            setTplus(-45);
            d=new Stand();
            d.setIsin(true);
            return d;
        }
        return null;
    }
    private void tochild(){
        if (adult){
            adult=false;
            setSmul(17f/14f);
            setImgyto(17);
            setImgy(0);
            setTplus(0);
            setY(getY()+44);
        }
    }

    private void child(){
        if (getS()<0){
            if (isOnground()) {
                if (index % (30 / getS()) == 0) {
                    setImgx(mark[i % 3 + 1] + 14);
                    setImgxto(mark[i % 3 + 1]);
                    i++;
                }
            }
            else {
                setImgxto(mark[4]);
                setImgx(mark[4]+17);
            }
        }
        if (getS()>0){
            if (isOnground()) {
                if (index % (30 / getS()) == 0) {
                    setImgx(mark[i % 3 + 1]);
                    setImgxto(mark[i % 3 + 1] + 14);
                    i++;
                }
            }
            else {
                setImgxto(mark[4]+17);
                setImgx(mark[4]);
            }
        }
        if (getS()==0){
            if (isOnground()) {
                if (belong(getImgx())) {
                    setImgx(3);
                    setImgxto(17);
                } else {
                    setImgx(17);
                    setImgxto(3);
                }
            }
            else {
                if (belong(getImgx())) {
                    setImgxto(mark[4]+17);
                    setImgx(mark[4]);
                } else {
                    setImgxto(mark[4]);
                    setImgx(mark[4]+17);
                }
            }
        }
    }
    private  boolean belong(int x){
        for (int i=0;i<mark.length;i++){
            if (mark[i]==x){
                return true;
            }
        }
        return false;
    }
    @Override
    public void operate(){
        if (!(isLeft()||isRight())||(isOnground()&&squat)){
            if (opi%5==0) {
                if (getS() > 0) {
                    setS(getS() - 1);
                }
                if (getS() < 0) {
                    setS(getS() + 1);
                }
            }
        }
        if (!getRdirection().isIsin()) {
            if (isRight()) {
                if (!(squat && isOnground())) {
                    if (opi % 2 == 0) {
                        if (getS() < Math.abs(getMaxs())) {
                            setS(getS() + 1);
                        }
                    }
                }
            }
        }
        if (!getLdirection().isIsin()) {
            if (isLeft()) {
                if (!(squat && isOnground())) {
                    if (opi % 2 == 0) {
                        if (getS() > -Math.abs(getMaxs())) {
                            setS(getS() - 1);
                        }
                    }
                }

            }
        }
        if (isUp()){
            if (isOnground()||b>0) {
                setDropv(-20);
                setOnground(false);
                b--;
            }
        }
        else {
            b=0;
        }
        if (isDown()){
            if (!squat) {
                setY(getY() + 45);
            }
            setTplus(-45);
            squat=true;
        }
        else {
            if (squat&&!d.isIsin()){
                squat=false;
                d=null;
                setTplus(0);
                setY(getY()-45);
            }
        }
        setMaxs(setmaxs);
        if (isCtrl()&&!squat){
            setMaxs(2*setmaxs);
        }
        opi++;
    }


    public boolean isOnground() {
        return onground;
    }

    public void setOnground(boolean onground) {
        this.onground = onground;
    }

    @Override
    public void gravity(int g) {
        super.gravity(g);
        setOnground(false);
    }

    @Override
    public void ispushedup(int x, int y) {
        super.ispushedup(x, y);
        setOnground(true);
        b=6;
    }
}
