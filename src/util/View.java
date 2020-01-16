package util;


import java.util.ArrayList;
import java.util.List;

public class View {
    private List<Elements> universalelements=null;
    private String filename,layout;
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
