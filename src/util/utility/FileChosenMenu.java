package util.utility;

import util.Elements;
import util.Menu;

import java.io.File;

public class FileChosenMenu extends Menu {
    String[] filelist;
    FileChosenMenu(int x,int y){
        String path="./res/assembly";

        File dir=new File(path);
        if(dir.list()==null){
            System.out.println("file read fail");
        }

        filelist=dir.list();
        setX(x);
        setY(y);
        setSize(1000);
        Elements e;

        e=new Elements();
        e.setResource("windows.png");
        e.setResourcetype("Image");
        e.setSize(800);
        e.setSmul(1f);
        setBack(e);
        e=new CancelButton(this);
        e.setX(450);
        e.setY(650);
        e.setSize(100);
        getContain().add(e);
        for (int i=0;i<Math.min(filelist.length,6);i++){
            e=new Item(filelist[i],100,i*100+50,100);
            ((Item) e).setLength(550);
            getContain().add(e);
        }
        e=new FileChosenMenuConfirmButton();
        e.setX(100);
        e.setY(650);
        e.setSize(100);
        ((FileChosenMenuConfirmButton) e).setFileChosenMenu(this);
        getContain().add(e);
    }

    @Override
    public void dragged(int x, int y) {
        setX(getX()-x);
        setY(getY()-y);
    }
}
