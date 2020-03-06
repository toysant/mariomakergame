package act;
import util.*;
import res.XMLManager;
import util.utility.Group;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class Updater extends Thread {
    private int prx,pry;
    private Menu focus=null;
    private List<Elements> removeList=new ArrayList<Elements>();
    private List<Thread> threadList=new ArrayList<Thread>();
    private Derivative temp=null;
    int g=1,index;
    private JFrame frame=null;
    public View view=null;
    private XMLManager xmlManager=null;
    private SquareMap loadmap=null;
    List<Button> buttonList=null;
    List<Enemy> enemyList=null;
    List<Block> blockList=null;
    List<Derivative> derivativeList=null;
    List<Anime> animeList=null;
    List<Elements> addlist=null;
    List<Menu> menuList=new ArrayList<Menu>();
    ControllableElements you=null;
    private int xoffset=0;
    private int yoffset=0;
    private boolean xoffsetable=false,yoffsetable=false;
    private Configs conf=new Configs();
    private Mode mode=null;
    Updater(View view){
        this.view=view;
        xmlManager=new XMLManager();
        getViewContent(view);
        initlists();
        xmlManager.readconf(conf);
        loadmap=new SquareMap(view,conf,this);
        mode=getModebs(conf.getMode());
        camera();
        loader(loadmap.getAll(xoffset,yoffset));
    }

    public Mode getMode() {
        return mode;
    }

    private Mode getModebs(String s){
        Mode m=null;
        try {
            m=(Mode) Class.forName("util.utility."+s).newInstance();
            m.setMaxx(loadmap.getMaxx());
            m.setMaxy(loadmap.getMaxy());
            m.setVisix(loadmap.getVisix());
            m.setVisiy(loadmap.getVisiy());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return m;
    }
    public List<Derivative> getDerivativeList() {
        return derivativeList;
    }

    public List<Button> getButtonList() {
        return buttonList;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    public int getXoffset() {
        return xoffset;
    }

    public void setXoffset(int xoffset) {
        this.xoffset = xoffset;
    }

    public int getYoffset() {
        return yoffset;
    }

    public void setYoffset(int yoffset) {
        this.yoffset = yoffset;
    }

    @Override
    public void run() {
//        new Thread(()->{
//            getLists();
//        }).start();
        adjustframe();
        threadList.add(eject(enemyList,blockList));
        threadList.add(eject(derivativeList,blockList));
        if (you!=null){
            threadList.add(ejectyou(blockList));
        }
        threadList.add(new Thread(()->{
            while (true){
                if (mode.isMethod()) {
                    synchronized (blockList) {
                        blockList.forEach(block -> block.method());
                    }
                    synchronized (derivativeList) {
                        derivativeList.forEach(derivative -> {
                            if (derivative.isLogical()) {
                                derivative.method();
                            }
                        });
                    }
                    synchronized (enemyList) {
                        enemyList.forEach(enemy ->
                        {
                            if (enemy.isLogical()) {
                                enemy.method();
                            }
                        });
                    }
                }
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }));
        threadList.forEach(Thread::start);
        while(true){
//            long b=System.currentTimeMillis();
            if (!mode.isPause()) {
                index++;
                anime();
                if (mode.isAct()) {
                    gravity();
                    enemyList.forEach(enemy -> {
                        enemy.go();
                    });
                    travDerivat();
                    istouched();
                    youmovement();
                    if (you != null) {
                        you.setX(you.getX() + you.getSre());
                    }
                }
                if (mode.isCamera()) {
                    camera();
                }
                if (!mode.isViewedit()) {
                    dynamicloader();
                } else {
                    removeList.addAll(blockList);
                    removeList.addAll(enemyList);
                    removeList.addAll(buttonList);
                    removeList.addAll(derivativeList);
                    removeList.add(you);
                    loadmap.reload(view);
                    addlist.addAll(loadmap.getAll(xoffset, yoffset));
                }
                menuList.forEach(menu -> {
                    if (menu.isDestroy()) {
                        removeList.add(menu);
                    }
                });
                if (mode.isGamelogical()) {
                    solver();
                }
            }
            modeact();
            if (mode.isViewedit()) {
                vieweditor();
            }
            synchronized (enemyList) {
                synchronized (derivativeList) {
                    synchronized (blockList) {
                        synchronized (removeList) {
                            listRemover();
                        }
                        synchronized (addlist) {
                            adder();
                        }

                    }
                }
            }

//            System.out.println(System.currentTimeMillis()-b);
            try {
                Thread.sleep(mode.getDelaytime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
    private void modeact(){
        if (mode.isCamera()){

        }
        else {
            xoffset=mode.getXoffset();
            yoffset=mode.getYoffset();
        }
        if (mode.isControl()){
            mode.setFocus(you);
        }
        if (mode.save()){
            xmlManager.build(view);
            mode.setSave(false);
        }
        addlist.addAll(mode.getAddlist());
        mode.getAddlist().clear();
        removeList.addAll(mode.getRemovelist());
        mode.getRemovelist().clear();
    }
    public void vieweditor(){
        synchronized (mode.getRemoveview()) {
            mode.getRemoveview().forEach(elements -> {
                removeview(elements, view.getUniversalelements());
            });
            mode.getRemoveview().clear();
        }
        synchronized (mode.getAddview()) {
            mode.getAddview().forEach(elements -> {
                addview(elements, view.getUniversalelements());
            });
            mode.getAddview().clear();
        }
    }
    public void removeview(Elements e,List<Elements> l){
        for (int i=l.size()-1;i>=0;i--){
            if (!(l.get(i) instanceof Group)){
                if (e.getX()/view.getMul()==l.get(i).getX()/view.getMul()&&e.getY()/view.getMul()==l.get(i).getY()/view.getMul()){
                    l.remove(l.get(i));
                }
            }
            else {
                if (((Group) l.get(i)).getElementsList().size()!=0) {
                    removeview(e, ((Group) l.get(i)).getElementsList());
                }
                else {
                    l.remove(l.get(i));
                }
            }
        }

    }
    public void addview(Elements e,List<Elements> l){
        boolean addable=true;
        for (int i=0;i<l.size();i++){
            if (!(l.get(i)instanceof Group)){
            if (e.getX()/view.getMul()==l.get(i).getX()/view.getMul()&&e.getY()/view.getMul()==l.get(i).getY()/view.getMul()){
                    l.get(i).setInside(e.getClass().getSimpleName());
                System.out.println(l.get(i).getInside());
                    addable=false;
            }
            else {
            }
            }
            else {
                addview(e,((Group)l.get(i)).getElementsList());
            }
        }

        if (addable){
            l.add(e);
        }
    }
    public void switchmode(String name){
        this.mode=getModebs(name);
        mode.setXoffset(xoffset);
        mode.setYoffset(yoffset);
        menuList.clear();
    }
    private void adder(){
        addlist.forEach(elements -> {
            filtelements(elements);
        });
        addlist.clear();

    }

    private void anime(){
        derivativeList.forEach(derivative -> {
            if (derivative.isPlay()) {
                animeList.add(derivative.getAnime());
                derivative.setPlay(false);
            }
        });
        animeList.forEach(anime -> {
            if (anime.lifetimeover()){
                anime.destroy();
            }
        });
    }
    private void dynamicloader(){
            for (int i = enemyList.size() - 1; i >= 0; i--) {
                if (loadmap.outofrange(enemyList.get(i))) {
                    removeList.add(enemyList.get(i));
                }
            }

            for (int i = derivativeList.size() - 1; i >= 0; i--) {
                if (loadmap.outofrange(derivativeList.get(i))) {
                    removeList.add(derivativeList.get(i));
                }
            }
        if (you!=null) {
                if (loadmap.outofrange(you)) {
                    you.setDestroy(true);
                }
        }
        List<List<Elements>> ll=loadmap.getLists(xoffset,yoffset);
        if (ll!=null) {
            remover(ll.get(0));
            loader(ll.get(1));
        }
    }
    private void listRemover(){
        removeList.forEach(elements -> {
            remove(elements);
        });
        removeList.clear();
    }
    private void travDerivat(){
        derivativeList.forEach(derivative -> {
            if (!derivative.isHighspeed()) {
                derivative.go();
                if (!derivative.isLogical()) {
                    int index = 0;
                    for (int i = 0; i < blockList.size(); i++) {
                        if (isIn(derivative, blockList.get(i))) {
                            addlist.add(derivative.deal(blockList.get(i)));
                            index++;
                        }
                    }
                    if (index > 0) {
                        derivative.setIsin(true);
                    } else {
                        derivative.setIsin(false);
                    }
                }
                enemyList.forEach(enemy -> {
                    if (isIn(derivative, enemy)) {
                        addlist.add(derivative.deal(enemy));
                    }
                });
                if (isIn(derivative, you)) {
                    addlist.add(derivative.deal(you));
                }
            }
            else {
                derivative.go();
                derivativeList.forEach(derivative1 -> {
                    multtracing(derivative,blockList);
                });
                enemyList.forEach(enemy -> {
                    if (isIn(derivative, enemy)) {
                        addlist.add(derivative.deal(enemy));
                    }
                });
                if (isIn(derivative, you)) {
                    addlist.add(derivative.deal(you));
                }
            }
        });
    }
    private void youmovement(){
        if (you!=null) {
            collisionbetween(you, enemyList);
            you.operate();
            you.method();
        }
    }
    private void init(){
        clear();
        xoffsetable=false;
        yoffsetable=false;
        xoffset=0;
        yoffset=0;
    }
    public void switchview(String name){
        init();
        view=new View(name);
        getViewContent(view);
        loadmap=new SquareMap(view,conf,this);
        camera();
        loader(loadmap.getAll(xoffset,yoffset));
    }
    public void switchview(String name,InfoPack infoPack){
        init();
        view=new View(name);
        view.setXoffsetable(infoPack.isXoffsetable());
        view.setYoffsetable(infoPack.isYoffsetable());
        view.setMaxx(infoPack.getMaxx());
        view.setMaxy(infoPack.getMaxy());
        viewload(view);
        you=null;
        loadmap=new SquareMap(view,conf,this);
        loader(loadmap.getAll(xoffset,yoffset));
    }
    private void camera(){
        if (you!=null) {
            if (xoffsetable && view.getMaxx() != 0) {
                if (you.getX() > xoffset + conf.getX() * 7 / 10 && xoffset + conf.getX() < view.getMaxx()) {
                    xoffset = you.getX() - conf.getX() * 7 / 10;
                    if (xoffset + conf.getX() > view.getMaxx()) {
                        xoffset = view.getMaxx() - conf.getX();
                    }
                }
                if (you.getX() < xoffset + conf.getX() * 3 / 10 && xoffset > 0) {
                    xoffset = you.getX() - conf.getX() * 3 / 10;
                    if (xoffset < 0) {
                        xoffset = 0;
                    }
                }
            }
            if (yoffsetable && view.getMaxy() != 0) {

            }
        }
    }
    private void gravity(){
        if (you!=null){
            if (you.isGram()){
            you.setY(you.getY()+you.getDropvre());
            you.gravity(g);
            }
        }
        enemyList.forEach(enemy -> {
            if (enemy.isLogical()) {
                if (enemy.isGram()) {
                    enemy.setY(enemy.getY() + enemy.getDropvre());
                    enemy.gravity(g);

                }
            }
        });
        derivativeList.forEach(derivative -> {
            if (derivative.isLogical()){
                derivative.setY(derivative.getY()+derivative.getDropvre());
                derivative.gravity(g);
            }
        });
//        for (int i=0;i<enemyList.size();i++){
//            enemyList.get(i).setY(enemyList.get(i).getY() +enemyList.get(i).getDropvre());
//            enemyList.get(i).gravity(g);
//        }
    }
    private boolean isIn(Elements ejed,Elements toej){
        int toejsize,toejtall,ejedsize,ejedtall;
            toejsize = toej.getSize() - 1;
            ejedsize = ejed.getSize() - 1;
            toejtall = toej.sizetotall() - 1;
            ejedtall = ejed.sizetotall() - 1;
            if (ejed.getY() > toej.getY() - ejedtall && ejed.getY() < toej.getY() + toejtall) {
                if ( ejed.getX() > toej.getX() - ejedsize&&ejed.getX() < toej.getX() + toejsize){
                    toej.in(ejed);
                    return true;
                }
            }
        return false;
    }
    private void collisionbetween(Elements you,List e){
        for (int i=0;i<e.size();i++){
            detectionAABB(you,(Elements) e.get(i));
        }
    }
    private void detectionAABB(Elements B1,Elements B2){
        int b1size,b1tall,b2size,b2tall,x,y;
        b1size=B1.getSize()-1;
        b1tall=B1.sizetotall()-1;
        b2size=B2.getSize()-1;
        b2tall=B2.sizetotall()-1;
        x=B1.getX()-B2.getX()+b1size;
        y=B1.getY()-B2.getY()+b1tall;
        if (x>=0&&x<=b1size+b2size&&y>=0&&y<=b1tall+b2tall){
            if (x*(b1tall+b2tall)/(b1size+b2size)<=y){
                if (b1tall+b2tall-x*(b1tall+b2tall)/(b1size+b2size)<=y){
                    B2.ispushedup(B1);
                    //b2down
                }
                if (b1tall+b2tall-x*(b1tall+b2tall)/(b1size+b2size)>y){
                    B2.istouchedleft(B1);
                    //b2left
                }
            }
            if (x*(b1tall+b2tall)/(b1size+b2size)>y) {
                if (b1tall + b2tall - x * (b1tall + b2tall) / (b1size + b2size) <= y) {
                    B2.istouchedright(B1);
                    //b2right
                }
                if (b1tall + b2tall - x * (b1tall + b2tall) / (b1size + b2size) > y) {
                    B2.isstepped(B1);
                    //b2up
                }
            }
        }
    }
    private Thread ejectyou(List toejl){
        return new Thread(()->{
            int t=25;
            int boost=0;
            while (true){
                Elements ejed=you;
                if(you!=null) {
                    synchronized (toejl) {
                        for (int x = 0; x < toejl.size(); x++) {
                            if (isIn(ejed, (Elements) toejl.get(x))) {
                                boost++;
                            }
                        }
                    }
                }
                if (boost>0){
                    t=2;
                    boost=0;
                }
                else {
                    t=50;
                }
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private Thread eject(List ejedl,List toejl){
        return new Thread(()->{
            int t=25;
            int boost=0;
            while (true){
                Elements ejed=null;
                synchronized (ejedl) {
                    synchronized (toejl) {
                        for (int i = 0; i < ejedl.size(); i++) {
                            ejed = (Elements) ejedl.get(i);
                            if (ejed.isLogical()) {
                                for (int x = 0; x < toejl.size(); x++) {
                                    if (isIn(ejed, (Elements) toejl.get(x))) {
                                        boost++;
                                    }
                                }
                            }
                        }
                    }
                }
                if (boost>0){
                    t=2;
                    boost=0;
                }
                else {
                    t=50;
                }
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void loader(List<Elements> l){
        addlist.addAll(l);
    }
    public void remover(List<Elements> l){
        removeList.addAll(l);
    }
    private void remove(Elements elements){
        if(elements instanceof Block){
            blockList.remove(elements);
        }
        else if(elements instanceof Button) {
            buttonList.remove(elements);
        }
        else if(elements instanceof Enemy){
            enemyList.remove(elements);
        }
        else if (elements instanceof Derivative){
            derivativeList.remove(elements);
        }
        else if (elements instanceof Anime){
            animeList.remove(elements);
        }
        else if (elements instanceof Menu){
            menuList.remove(elements);
        }
        else if (elements instanceof ControllableElements){
            you=null;
        }
    }
    class CrossPoint{
        int x;
        int y;
        int line;
        int blocknum;
        CrossPoint(int x,int y,int line,int blocknum){
            this.x=x;
            this.y=y;
            this.line=line;
            this.blocknum=blocknum;
        }
    }
    private void istouched(){
        derivativeList.forEach(derivative -> {
            if (derivative.isLogical()){
                derivative.setSre(derivative.getS());
                derivative.setDropvre(derivative.getDropv());
                multtracing(derivative,blockList);
            }
        });
        for(int i=0;i<enemyList.size();i++){
            if (enemyList.get(i).isLogical()) {
                enemyList.get(i).setSre(enemyList.get(i).getS());
                enemyList.get(i).setDropvre(enemyList.get(i).getDropv());
                multtracing(enemyList.get(i), blockList);
            }
        }
        if (you!=null) {
            you.setDropvre(you.getDropv());
            you.setSre(you.getS());
            multtracing(you, blockList);
        }
    }
    private void multtracing(Elements tohit,List hittedList){
        List<CrossPoint> crossPointsbuffer=null;
        while (tohit.getSre()!=0||tohit.getDropvre()!=0){
            crossPointsbuffer=raytracing(tohit,hittedList,crossPointsbuffer);
            if (crossPointsbuffer==null){
                tohit.offset();
                break;
            }
        }
    }
    private List<CrossPoint> raytracing(Elements tohit,List hittedList,List<CrossPoint> crossPointsbuffer){
        int nextX=tohit.getX()+tohit.getSre();
        int nextY=tohit.getY()+tohit.getDropvre();
        int nowX=tohit.getX();
        int nowY=tohit.getY();
        int tohitsize=tohit.getSize()-1;
        int tohittall=tohit.sizetotall()-1;
        List<CrossPoint> crossPointList=new ArrayList<CrossPoint>();
        CrossPoint thenext=null;
        for(int b=0;b<hittedList.size();b++){
//            boolean tocontinue=false;
//            if (crossPointsbuffer!=null){
//                for (int i=0;i<crossPointsbuffer.size();i++){
//                    if (crossPointsbuffer.get(i).blocknum==b){
//                        tocontinue=true;
//                    }
//                }
//            }
//            if (tocontinue){
//                continue;
//            }
            Block hitted=(Block) hittedList.get(b);
            int hittedsize=hitted.getSize()-1;
            int hittedtall=hitted.sizetotall()-1;
            CrossPoint crossPoint=null;
            crossPoint=getPoint(nowX,nowY,nextX,nextY,hitted.getY()-tohittall,1,b);
            if (crossPoint!=null){
                if (between(crossPoint.x,nowX,nextX)&&between(crossPoint.y,nowY,nextY)&&between(crossPoint.x,hitted.getX()-tohitsize,hitted.getX()+hittedsize))
                {
                    crossPointList.add(crossPoint);
                }}
            crossPoint=getPoint(nowX,nowY,nextX,nextY,hitted.getX()+hittedsize,2,b);
            if (crossPoint!=null){
                if (between(crossPoint.x,nowX,nextX)&&between(crossPoint.y,nowY,nextY)&&between(crossPoint.y,hitted.getY()-tohittall+1,hitted.getY()+hittedtall-1))
                {
                    crossPointList.add(crossPoint);
                } }
            crossPoint=getPoint(nowX,nowY,nextX,nextY,hitted.getY()+hittedtall,3,b);
            if (crossPoint!=null){
                if (between(crossPoint.x,nowX,nextX)&&between(crossPoint.y,nowY,nextY)&&between(crossPoint.x,hitted.getX()-tohitsize,hitted.getX()+hittedsize))
                {
                    crossPointList.add(crossPoint);
                }}
            crossPoint=getPoint(nowX,nowY,nextX,nextY,hitted.getX()-tohitsize,4,b);
            if (crossPoint!=null){
                if (between(crossPoint.x,nowX,nextX)&&between(crossPoint.y,nowY,nextY)&&between(crossPoint.y,hitted.getY()-tohittall+1,hitted.getY()+hittedtall-1))
                {
                    crossPointList.add(crossPoint);
                } }
        }
        if (crossPointsbuffer!=null){
                for (int a = crossPointList.size() - 1; a >= 0; a--) {
                    if (crossPointsbuffer.get(0).x == crossPointList.get(a).x && crossPointsbuffer.get(0).y == crossPointList.get(a).y) {
                        crossPointList.remove(a);
                    }
                }
        }
        if (crossPointList.size()!=0) {
            thenext=crossPointList.get(0);
            boolean f1=false,f3=false, f2=false,f4=false;
            for (int a = 0; a < crossPointList.size(); a++) {
                if (Math.pow(nowX-crossPointList.get(a).x,2)+Math.pow(nowY-crossPointList.get(a).y,2)<Math.pow(nowX-thenext.x,2)+Math.pow(nowY-thenext.y,2)){
                    thenext=crossPointList.get(a);
                }
            }
            for (int a=crossPointList.size()-1;a>=0;a--){
                if (Math.pow(nowX-crossPointList.get(a).x,2)+Math.pow(nowY-crossPointList.get(a).y,2)>Math.pow(nowX-thenext.x,2)+Math.pow(nowY-thenext.y,2)){
                    crossPointList.remove(a);
                }
            }
            for (int a=0;a<crossPointList.size();a++){
                thenext=crossPointList.get(a);
                if (thenext.line == 1||thenext.line == 3) {
                    tohit.setSre(nextX-thenext.x);
                }
                if (thenext.line == 2||thenext.line == 4) {
                    tohit.setDropvre(nextY-thenext.y);
                }
            }
            for (int a=0;a<crossPointList.size();a++){
                thenext=crossPointList.get(a);
                Elements hitted=(Elements) hittedList.get(thenext.blocknum);
                if (thenext.line == 1) {
                    tohit.pushedupre(nextY-thenext.y);
                    tohit.ispushedup(thenext.x,thenext.y);
                    hitted.isstepped(tohit);
                    tohit.cling(1,hitted);
                    f1=true;
                }
                if (thenext.line == 3){
                    tohit.steppedre(nextY-thenext.y);
                    tohit.isstepped(thenext.x,thenext.y);
                    hitted.ispushedup(tohit);
                    tohit.cling(2,hitted);
                    f3=true;
                }
                if (thenext.line == 2) {
                    tohit.leftre(nextX-thenext.x);
                    tohit.istouchedleft(thenext.x,thenext.y);
                    hitted.istouchedright(tohit);
                    tohit.cling(3,hitted);
                    if (tohit.getSre()==0) {
                        tohit.setOffset(1);
                    }
                    f2=true;
                }
                if (thenext.line == 4) {
                    tohit.rightre(nextX-thenext.x);
                    tohit.istouchedright(thenext.x,thenext.y);
                    hitted.istouchedleft(tohit);
                    tohit.cling(4,hitted);
                    if (tohit.getSre()==0) {
                        tohit.setOffset(-1);
                    }
                    f4=true;
                }
            }
            tohit.stocked(f1,f2,f3,f4);
            return crossPointList;
        }
        return null;
    }
    private boolean between(int a,int n1,int n2){
        if (n1>n2){
            if (a<=n1&&a>=n2){
                return true;
            }
            else return false;
        }
        else {
            if (a>=n1&&a<=n2){
                return true;
            }
            else return false;
        }
    }
    private CrossPoint getPoint(int x,int y,int nx,int ny,int b,int linenum,int blocknum){
        CrossPoint crossPoint;
        if (nx==x&&ny==y){
            if((linenum==1||linenum==3)&&nx==b){
                crossPoint=new CrossPoint(x,b,linenum,blocknum);
                return crossPoint;
            }
            else if((linenum==2||linenum==4)&&ny==b){
                crossPoint=new CrossPoint(b,y,linenum,blocknum);
                return crossPoint;
            }
            else return null;
        }
        if ((linenum==2||linenum==4)&&nx-x!=0){
            int py = (b-x)*(y-ny)/(x-nx)+y;
            crossPoint=new CrossPoint(b,py,linenum,blocknum);
            return crossPoint;
        }
        if((linenum==1||linenum==3)&&y-ny!=0) {
            int px =(b-y)*(x - nx) /(y - ny) + x;
            crossPoint=new CrossPoint(px, b,linenum,blocknum);
            return crossPoint;
        }
        return null;
    }
//    public void dogravity(){
//        for(int i=0;i<gravityList.size();i++){
//            gravityList.get(i).gravity();
//        }
//    }
    public void initlists(){
//        gravityList=new ArrayList<Gravity>();
        enemyList=new ArrayList<Enemy>();
        buttonList=new ArrayList<Button>();
        blockList=new ArrayList<Block>();
        derivativeList=new ArrayList<Derivative>();
        animeList=new ArrayList<Anime>();
        addlist=new ArrayList<Elements>();
    }
    public void filtelements(Elements elements){
        if(elements instanceof Block){
            blockList.add((Block)elements);
        }
        else if(elements instanceof Button) {
            buttonList.add((Button)elements);
        }
        else if(elements instanceof Enemy){
            enemyList.add((Enemy)elements);
        }
        else if (elements instanceof Derivative){
            derivativeList.add((Derivative) elements);
        }
        else if (elements instanceof Menu){
            if (((Menu)elements).isXmlcontain()){
                xmlManager.readmenu((Menu)elements);
            }
            menuList.add((Menu) elements);
        }
        else if (elements instanceof ControllableElements){
            if (you==null) {
                you = (ControllableElements) elements;
            }
        }
//        if(elements instanceof Gravity){
//            gravityList.add((Gravity)elements);
//        }
    }
    private void viewload(View v){
        yoffsetable=view.isYoffsetable();
        xoffsetable=view.isXoffsetable();
        if (you!=null){
            you.setSize(you.getRealsize()*(view.getMul()/you.getMul()));
        }
        for (int i=0;i<v.getUniversalelements().size();i++){
            if (you==null&&v.getUniversalelements().get(i) instanceof ControllableElements){
                you=(ControllableElements) v.getUniversalelements().get(i);
                you.setMul(view.getMul());
            }
        }
    }
    public void clear(){
        clearLists();
        you=null;
    }


    public void getViewContent(View v) {
        xmlManager.read(v);
        viewload(v);
    }

    public void click(int x,int y){
        InfoPack pack=new InfoPack();
        boolean menuclicked=false;
        if (menuList.size()!=0) {
            for (int m = menuList.size()-1; m >= 0; m--) {
                if (menuclicked){

                }else {
                    if (x < menuList.get(m).getX() || x > menuList.get(m).getX() + menuList.get(m).getSize() || y < menuList.get(m).getY() || y > menuList.get(m).getY() + menuList.get(m).sizetotall()) {

                    } else {
                        menuclicked = true;
                        for (int i = 0; i < menuList.get(m).getContain().size(); i++) {
                            Elements elements = menuList.get(m).getContain().get(i);
                            if (x > elements.getX() + menuList.get(m).getX()-menuList.get(m).getXoffset() && y > elements.getY() + menuList.get(m).getY()-menuList.get(m).getYoffset() && x < elements.getX() + menuList.get(m).getX() -menuList.get(m).getXoffset()+ elements.getSize() && y < elements.getY()-menuList.get(m).getYoffset() + menuList.get(m).getY() + elements.sizetotall()) {
                                if (elements instanceof Button) {
                                    ((Button) elements).click(mode.getFocus());
                                    ((Button) elements).click(pack);
                                    filtelements(((Button) elements).click());
                                }
                                mode.setFocus(elements);
                            } else if (elements.getResourcetype().equals("String")) {
                                if (x > elements.getX() + menuList.get(m).getX()-menuList.get(m).getXoffset() && y > elements.getY() + menuList.get(m).getY()-menuList.get(m).getYoffset() && x < elements.getX() + menuList.get(m).getX()-menuList.get(m).getXoffset() + ((Button) elements).getLength() && y < elements.getY() -menuList.get(m).getYoffset()+ menuList.get(m).getY() + elements.sizetotall()) {
                                    if (elements instanceof Button) {
                                        ((Button) elements).click(mode.getFocus());
                                        ((Button) elements).click(pack);
                                        filtelements(((Button) elements).click());
                                    }
                                    mode.setFocus(elements);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!menuclicked){
            for (int i = 0; i < buttonList.size(); i++) {
                if (x > buttonList.get(i).getX() - xoffset && y > buttonList.get(i).getY() - yoffset && x < buttonList.get(i).getX() - xoffset + buttonList.get(i).getSize() && y < buttonList.get(i).getY() - yoffset + buttonList.get(i).sizetotall()) {
                    buttonList.get(i).click(pack);
                    mode.setFocus(buttonList.get(i));
                    filtelements(buttonList.get(i).click());
                } else if (buttonList.get(i).getResourcetype().equals("String")) {
                    if (x > buttonList.get(i).getX() - xoffset && y > buttonList.get(i).getY() - yoffset && x < buttonList.get(i).getX() - xoffset + buttonList.get(i).getLength() && y < buttonList.get(i).getY() - yoffset + buttonList.get(i).sizetotall()) {
                        buttonList.get(i).click(pack);
                        mode.setFocus(buttonList.get(i));
                        filtelements(buttonList.get(i).click());
                    }
                }
            }
        }
        if (pack.isViewswitch()) {
            if (pack.getView() != null) {
                if (pack.getView().equals("")) {
                    switchview(pack.getView(), pack);
                } else {
                    switchview(pack.getView());
                }
            }
            else {
                switchview(view.getFilename());
            }
        }
        if (pack.isModeswitch()) {
            if (pack.getMode() != null) {
                if (pack.isViewswitch()){
                    if (!pack.getView().equals("")) {
                        switchmode(pack.getMode());
                        mode.construct(pack.getConstructPack());
                    } else {
                        switchmode(pack.getMode());
                        mode.construct(pack.getConstructPack());
                        mode.opView(view);
                    }
                }else {
                    switchmode(pack.getMode());
                    mode.setSize(view.getMul());
                }
            }
        }
        prx=0;
        pry=0;

    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    public void adjustframe(){
        frame.setTitle(conf.getTitle());
        frame.setSize(conf.getX(),conf.getY());
        Refresher.setFps(conf.getFps());
        //frame.validate();
    }
    public void solver(){
        if (you!=null) {
            switch (you.gameover()){
                case 0:gameover();break;
                case 1:switchview(view.getFilename());break;
                case 2:switchview(you.getViewto());break;
                case 99:switchview(you.getViewto());switchmode(you.getModeto());break;
                default:;
            }
        }
        for (int i=enemyList.size()-1;i>=0;i--){
            if (enemyList.get(i).isDestroy()){
                addlist.add(enemyList.get(i).destroyed());
                removeList.add(enemyList.get(i));
            }
        }
        for (int i=derivativeList.size()-1;i>=0;i--){
            if (derivativeList.get(i).isDestroy()){
                addlist.add(derivativeList.get(i).destroyed());
                removeList.add(derivativeList.get(i));
            }
        }
        for (int i=animeList.size()-1;i>=0;i--){
            if (animeList.get(i).isDestroy()){
                removeList.add(animeList.get(i));
            }
        }
        blockList.forEach(block -> {
            block.clearSurl();
            if (block.isInteractive()){
                addlist.add(block.interact());
            }
        });
    }
    private void clearLists(){
        enemyList.clear();
        blockList.clear();
        buttonList.clear();
        derivativeList.clear();
        animeList.clear();
        loadmap.clear();
        addlist.clear();
        removeList.clear();
        menuList.clear();
    }
    public void changeview(String name,int x,int y){
        clearLists();
        init();
        view=new View(name);
        getViewContent(view);
        loadmap=new SquareMap(view,conf,this);
        camera();
        loader(loadmap.getAll(xoffset,yoffset));
    }
    public void gameover(){
        switchview("./res/gameover.xml");
    }
    public void drag(int x,int y){
        if (focus!=null){
                focus.dragged(prx-x, pry-y);
        }
        prx=x;
        pry=y;
    }
    public void press(int x,int y){
        boolean focused=false;
        for (int i=0;i<menuList.size();i++){
            if (menuList.get(i).getX()<x&&menuList.get(i).getY()<y&&menuList.get(i).getX()+menuList.get(i).getSize()>x&&menuList.get(i).getY()+menuList.get(i).sizetotall()>y){
                focus= menuList.get(i);
                focused=true;
            }
            else {

            }
        }
        if (!focused){
            focus=null;
        }
        else {
            menuList.remove(focus);
            menuList.add(focus);
        }
        prx=x;
        pry=y;
    }
}
