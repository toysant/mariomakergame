package util;


import java.util.ArrayList;
import java.util.List;

public class View {
    private List<Elements> universalelements=null;
    private int mul;
    private String filename,layout;
    private boolean xoffsetable=false,yoffsetable=false;
    private int maxx=0,maxy=0;
    public int getMul() {
        return mul;
    }

    public void setMul(int mul) {
        this.mul = mul;
    }

    public boolean isXoffsetable() {
        return xoffsetable;
    }

    public void setXoffsetable(boolean xoffsetable) {
        this.xoffsetable = xoffsetable;
    }

    public boolean isYoffsetable() {
        return yoffsetable;
    }

    public void setYoffsetable(boolean yoffsetable) {
        this.yoffsetable = yoffsetable;
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

    public View(String filename){
        universalelements=new ArrayList<Elements>();
        this.filename=filename;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public List<Elements> getUniversalelements() {
        return universalelements;
    }
    public void setUniversalelements(List<Elements> universalelements) {
        this.universalelements = universalelements;
    }
    public synchronized void add(Elements elements){
        universalelements.add(elements);

    }
    public synchronized void remove(Elements elements){

        universalelements.remove(elements);

    }
    public void clear(){
        universalelements.clear();
    }
    public synchronized void addAll(List<Elements> list){

        universalelements.addAll(list);

    }
    public String  getFilename(){
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
