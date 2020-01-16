package res;


import java.awt.*;

class ImagewithName {
    private Image pic;
    private String picname;
    ImagewithName(String picname,Image pic){
        setPic(pic);
        setPicname(picname);
    }
    Image getPic() {
        return pic;
    }

    private void setPic(Image pic) {
        this.pic = pic;
    }

    String getPicname() {
        return picname;
    }

    private void setPicname(String picname) {
        this.picname = picname;
    }


}
