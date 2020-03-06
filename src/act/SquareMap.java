package act;

import util.Configs;
import util.ControllableElements;
import util.Elements;
import util.View;
import util.utility.Background;
import util.utility.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SquareMap {
    private HashMap<String,Square> map=new HashMap<String,Square>();
    private int maxx=0,maxy=0,visix=0,visiy=0;
    private int nowx=0,nowy=0;
    private int numx=0,numy=0;
    private int loadrange=2;
    private int size=300;
    private Updater updater=null;
    List<List<Elements>> ll=new ArrayList<List<Elements>>();
    List<Elements> le = new ArrayList<Elements>();
    private int[][] un=new int[2][2];
    public SquareMap(){

    }
    public SquareMap(View v, Configs conf, Updater updater){
        this.size=conf.getSize();
        this.updater=updater;
        visix=conf.getX();
        visiy=conf.getY();
        if (v.getMaxx()==0){
            this.maxx=visix;
        }
        else {
            this.maxx=v.getMaxx();
        }
        if (v.getMaxy()==0){
            this.maxy=visiy;
        }
        else{
            this.maxy=v.getMaxy();
        }
        numx=conf.getX()/size;
        numy=conf.getY()/size;
        loadview(v,false);
    }
    private void loadbackgroud(View v){
        updater.getViewContent(v);
        loadview(v,false);
    }
    public void reload(View v){
        clear();
        loadview(v,true);
    }
    public int getVisix() {
        return visix;
    }

    public int getVisiy() {
        return visiy;
    }

    private void loadview(View v,boolean reload){
        boolean you=false;
        for (int i=0;i<v.getUniversalelements().size();i++){
            if(v.getUniversalelements().get(i) instanceof Group){
                synchronized (v.getUniversalelements().get(i)) {
                    ((Group) v.getUniversalelements().get(i)).getElementsList().forEach(elements -> add(elements));
                }
            }
            else if(v.getUniversalelements().get(i) instanceof Background){
                View tv=new View(v.getUniversalelements().get(i).getResource());
                loadbackgroud(tv);
            }
            else if (v.getUniversalelements().get(i)instanceof ControllableElements){
                if (reload&&!you){
                    you=true;
                    add(v.getUniversalelements().get(i));
                }
            }
            else{
                add(v.getUniversalelements().get(i));
            }
        }
    }
    public List<Elements> getAll(int x,int y){
        List<Elements> l=new ArrayList<Elements>();
        for (int a=Math.max(x/size-loadrange,0);a<=Math.min(x/size+numx+loadrange,maxx/size);a++){
            for (int b=Math.max(y/size-loadrange,0);b<=Math.min(y/size+numy+loadrange,maxy/size);b++){
                if (map.get(a+"+"+b)!=null){
                    l.addAll(map.get(a+"+"+b).getSpace());
                    map.get(a + "+" + b).clear();
                }
            }
        }
        nowx=x/size;
        nowy=y/size;
        return l;
    }
    public boolean outofrange(Elements e){
        if (e.getX()/size<nowx-loadrange||e.getX()/size>nowx+numx+loadrange||e.getY()/size>nowy+numy+loadrange){
            if (e instanceof ControllableElements){
            }
            else {
                add(e);
            }
            return true;
        }
        return false;
    }
    public List<List<Elements>> getLists(int x,int y){
        boolean cross=false;
        if (nowx!=x/size||nowy!=y/size) {
            int xnum=x/size;
            int ynum=y/size;
            if (xnum>nowx&&ynum>nowy){
                if (xnum-loadrange<=nowx+loadrange+numx&&ynum-loadrange>=nowy+loadrange+numy) {
                    un[0][0] = xnum - loadrange;
                    un[1][0] = nowx + numx+loadrange;
                    un[0][1]=  ynum-loadrange;
                    un[1][1]=nowy+numy+loadrange;
                    cross=true;
                }
                else {
                    cross=false;
                }
            }
            else if (xnum>nowx){
                if (xnum-loadrange<=nowx+loadrange+numx&&ynum+loadrange+numy>=nowy-loadrange){
                    un[0][0] = xnum - loadrange;
                    un[1][0] = nowx + numx+loadrange;
                    un[0][1]=  nowy-loadrange;
                    un[1][1]=ynum+numy+loadrange;
                    cross=true;
                }
                else {
                    cross=false;
                }

            }
            else if (ynum>nowy){
                if (xnum+loadrange+numx>=nowx-loadrange&&ynum-loadrange<=nowy+loadrange+numy){
                    un[0][0] = nowx - loadrange;
                    un[1][0] = xnum+ numx+loadrange;
                    un[0][1]=  ynum-loadrange;
                    un[1][1]=nowy+numy+loadrange;
                    cross=true;
                }
                else {
                    cross=false;
                }
            }
            else {
                if (xnum+loadrange+numx>=nowx-loadrange&&ynum+loadrange+numy>=nowy-loadrange){
                    un[0][0] = nowx - loadrange;
                    un[1][0] = xnum + numx+loadrange;
                    un[0][1]=  nowy-loadrange;
                    un[1][1]=ynum+numy+loadrange;
                    cross=true;
                }
                else {
                    cross=false;
                }
            }
            ll.clear();
            le=new ArrayList<Elements>();
            if (cross) {
                updater.getBlockList().forEach(block -> {
                    if (block.getX()/size< un[0][0] || block.getY()/size < un[0][1] || block.getX()/size > un[1][0] || block.getY()/size > un[1][1]) {
                            le.add(block);
                            add(block);
                    }
                });
                updater.getEnemyList().forEach(enemy -> {
                    if (enemy.getX()/size< un[0][0] || enemy.getY()/size < un[0][1] || enemy.getX()/size > un[1][0] || enemy.getY()/size> un[1][1]) {
                        le.add(enemy);
                        add(enemy);
                    }
                });
                updater.getButtonList().forEach(button -> {
                    if (button.getX()/size< un[0][0] || button.getY()/size < un[0][1] ||button.getX()/size > un[1][0] || button.getY()/size > un[1][1]) {
                        le.add(button);
                        add(button);
                    }

                });
                for (int i=updater.getDerivativeList().size()-1;i>=0;i--){
                    if (updater.getDerivativeList().get(i).getX()/size< un[0][0] || updater.getDerivativeList().get(i).getY()/size < un[0][1] ||updater.getDerivativeList().get(i).getX()/size > un[1][0] || updater.getDerivativeList().get(i).getY()/size > un[1][1]) {
                        le.add(updater.getDerivativeList().get(i));
                    }
                }
            }
            else {
                updater.getButtonList().forEach(button -> {
                    le.add(button);
                    add(button);
                });
                updater.getEnemyList().forEach(enemy -> {
                    le.add(enemy);
                    add(enemy);
                });
                updater.getBlockList().forEach(block -> {
                    le.add(block);
                    add(block);
                });
                le.addAll(updater.getDerivativeList());
            }
            ll.add(le);
            le=new ArrayList<Elements>();
            for (int a=Math.max(xnum-loadrange,0);a<=Math.min(xnum+numx+loadrange,maxx/size);a++){
                for (int b=Math.max(ynum-loadrange,0);b<=Math.min(ynum+numy+loadrange,maxy/size);b++){
                    if (cross) {
                        if (a < un[0][0] || b < un[0][1] || a > un[1][0] || b > un[1][1]) {
                            if (map.get(a + "+" + b) != null) {
                                le.addAll(map.get(a + "+" + b).getSpace());
                                map.get(a + "+" + b).clear();
                            }
                        }
                    }
                    else {
                        if (map.get(a + "+" + b) != null) {
                            le.addAll(map.get(a + "+" + b).getSpace());
                            map.get(a + "+" + b).clear();
                        }
                    }
                }
            }
            ll.add(le);
            nowx=xnum;
            nowy=ynum;
            return ll;
        }
        else {
            return null;
        }
    }
    public boolean add(Elements e){
        if (e.getX()>=0-e.getSize()&&e.getY()>=0-e.getSize()&&e.getX()<=maxx&&e.getY()<=maxy) {
            if (map.get(e.getX() / size+"+"+e.getY()/size) != null) {
                map.get(e.getX() / size+"+"+e.getY()/size).getSpace().add(e);
            }
            else {
                Square square=new Square();
                square.add(e);
                map.put(e.getX() / size+"+"+e.getY()/size,square);
            }
            return true;
        }
        return false;
    }
    public void remove(Elements e){
        if (e.getX()>=0&&e.getY()>=0&&e.getX()<=maxx&&e.getY()<=maxy) {
            if (map.get(e.getX() / size+"+"+e.getY()/size) != null) {
                map.get(e.getX() / size+"+"+e.getY()/size).getSpace().remove(e);
            }
        }
    }

    public int getMaxx() {
        return maxx;
    }

    public void setMaxx(int maxx) {
        this.maxx = maxx;
    }

    public int getMaxy() {
        return maxy;
    }

    public void setMaxy(int maxy) {
        this.maxy = maxy;
    }
    public void clear(){
        map.forEach((s, square) -> {
            square.clear();
        });
    }

}
