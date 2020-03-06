package util;


public class Elements{
    private String resourcetype;
    private String resource;
    private int size;
    private float smul=1;
    private int splus=0,tplus=0;
    private int s=0,maxs=0,sre=0,dropv=0,maxv=9,dropvre=0;
    private int x,y;
    private int imgx,imgy,imgxto,imgyto;
    private int offset=0;
    private boolean visible=true,gram=true;
    private boolean logical=true;
    private String inside=null;
    private Elements insideob=null;
    private boolean destroy=false,interactive=false;

    public int getPaintx() {
        return x;
    }


    public int getPainty() {
        return y;
    }

    public void focusmethod(int code){

    }
    public Elements focus(){
        return null;
    }
    public int getPaintsize() {
        return size+splus;
    }

    public int getPainttall() {
        return ((int)(getSize()*smul+tplus));
    }


    public int getTplus() {
        return tplus;
    }

    public void setTplus(int tplus) {
        this.tplus = tplus;
    }

    public Elements getInsideob() {
        return insideob;
    }

    public void setInsideob(Elements insideob) {
        this.insideob = insideob;
    }

    public boolean isInteractive() {
        return interactive;
    }

    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }
    public String getInside() {
        return inside;
    }

    public void setInside(String inside) {
        this.inside = inside;
    }

    public void cling(int line, Elements e){
    }
    public void chained(){

    }
    public Elements destroyed(){
        return null;
    }
    public Elements interact(){
        return null;
    }
    public void stocked(boolean f1,boolean f2,boolean f3,boolean f4){
        if (f2&&f4) {
            setSre(0);
            setOffset(0);
        }
    }

    public boolean isLogical() {
        return logical;
    }

    public void setLogical(boolean logical) {
        this.logical = logical;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }



    public boolean isGram() {
        return gram;
    }

    public void setGram(boolean gram) {
        this.gram = gram;
    }

    public void gravity(int g){
        if (getDropv()<getMaxv()){
            setDropv(getDropv()+g);
        }
    }

    public int getMaxs() {
        return maxs;
    }

    public void setMaxs(int maxs) {
        this.maxs = maxs;
    }

    public int getDropvre() {
        return dropvre;
    }

    public void setDropvre(int dropvre) {
        this.dropvre = dropvre;
    }

    public void offset(){
        setX(getX()+getOffset());
        setOffset(0);
    }
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getDropv() {
        return dropv;
    }

    public void setDropv(int dropv) {
        this.dropv = dropv;
    }

    public int getMaxv() {
        return maxv;
    }

    public void setMaxv(int maxv) {
        this.maxv = maxv;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getSre() {
        return sre;
    }

    public void setSre(int sre) {
        this.sre = sre;
    }

    public int getSplus() {
        return splus;
    }

    public void setSplus(int splus) {
        this.splus = splus;
    }

    public float getSmul() {
        return smul;
    }

    public void setSmul(float smul) {
        this.smul = smul;
    }

    public int getSize() {
        return (size+splus);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(String resourcetype) {
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

    public void setImgx(int imgx) {
        this.imgx = imgx;
    }

    public int getImgy() {
        return imgy;
    }
    public  void isstepped(int x,int y){};
    public  void ispushedup(int x,int y){};
    public  void istouchedright(int x,int y){};
    public  void istouchedleft(int x,int y){};
    public  void isstepped(Elements e){};
    public  void ispushedup(Elements e){};
    public  void istouchedright(Elements e){};
    public  void istouchedleft(Elements e){};
    public void leftre(int sub){};
    public void rightre(int sub){};
    public void steppedre(int sub){};
    public void pushedupre(int sub){};
    public void destroy(){
        destroy=true;
    }
    public void in(Elements e){
    }

    public int getImgxto() {
        return imgxto;
    }

    public void setImgxto(int imgxto) {
        this.imgxto = imgxto;
    }

    public int getImgyto() {
        return imgyto;
    }

    public void setImgyto(int imgyto) {
        this.imgyto = imgyto;
    }

    public void setImgy(int imgy) {
        this.imgy = imgy;
    }
    public int sizetotall(){
        return (int)(getSize()*smul+tplus);
    }
    public void method(){

    }
    public int getRealsize(){
        return size;
    }
    //    public Elements(String type){
//        this.type=type;
//}
}
