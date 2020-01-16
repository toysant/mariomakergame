package util;

public abstract class Elements {
    private String resourcetype;
    private String resource;
    private int size;

    private int x,y;
    private int imgx,imgy;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getResourcetype() {
        return resourcetype;
    }

    protected void setResourcetype(String resourcetype) {
        this.resourcetype = resourcetype;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getImgx() {
        return imgx;
    }

    protected void setImgx(int imgx) {
        this.imgx = imgx;
    }

    public int getImgy() {
        return imgy;
    }

    protected void setImgy(int imgy) {
        this.imgy = imgy;
    }

    //    public Elements(String type){
//        this.type=type;
//}
}
