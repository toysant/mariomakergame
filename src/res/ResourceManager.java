package res;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
    private List<ImagewithName> imageList=new ArrayList<ImagewithName>();
    public ResourceManager(){
        String path="./res/pic";
        File dir=new File(path);

        if(dir.list()==null){
            System.out.println("file read fail");
        }

        String[] filelist=dir.list();
        for(int i=0;i<filelist.length;i++){
            try {
                imageList.add(new ImagewithName(filelist[i],ImageIO.read(new File(path,filelist[i]))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public Image getImagebyString(String atr){
        Image img=null;
        for(int i=0;i<imageList.size();i++){
            if(imageList.get(i).getPicname().equals(atr)){
                img=imageList.get(i).getPic();
            }
        }
        return img;
    }
}
