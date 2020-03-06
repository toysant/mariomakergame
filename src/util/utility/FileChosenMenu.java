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
        for (int i=0;i<filelist.length;i++){
            e=new Item(filelist[i],50,i*100+100,100);
            getContain().add(e);
        }
        e=new FileChosenMenuConfirmButton();
        e.setX(100);
        e.setY(0);
        e.setSize(100);
        ((FileChosenMenuConfirmButton) e).setFileChosenMenu(this);
        getContain().add(e);
    }

    @Override
    public void dragged(int x, int y) {
//        setYoffset(getYoffset()+y);
    }
}
