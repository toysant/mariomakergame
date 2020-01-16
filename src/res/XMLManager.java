package res;

import util.Elements;
import util.View;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import util.utility.Background;
import util.utility.Configs;
import util.utility.Group;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XMLManager {
    private SAXBuilder builder=new SAXBuilder();
    public void build(){
        Element root;
        root=new Element("aa");
        Document doc=new Document(root);
        Format format=Format.getCompactFormat();
        format.setEncoding("UTF-8");
        XMLOutputter outter=new XMLOutputter(format);
        try {
            outter.output(doc,new FileOutputStream("./res/assembly/haha.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void read(View view){
        try {
            Document document=builder.build(view.getFilename());
            Element root=document.getRootElement();
            view.setLayout(root.getName());
            List<Element> list =root.getChildren();
            Elements elements=null;
            Elements sube=null;
            int ty=0,tx=0,fx=0,fy=  0,size=0;
            for(int i=0;i<list.size();i++){
                try {
                    elements= (Elements) Class.forName("util.utility."+list.get(i).getName()).newInstance();
                    if (elements instanceof Background){
                        elements.setResource(list.get(i).getAttribute("res").getValue());
                    }
                    if (elements instanceof Group){
                        ty=list.get(i).getAttribute("ty").getIntValue();
                        tx=list.get(i).getAttribute("tx").getIntValue();
                        fx=list.get(i).getAttribute("x").getIntValue();
                        fy=list.get(i).getAttribute("y").getIntValue();
                        size=list.get(i).getAttribute("size").getIntValue();
                        for (int y=fy;y<=ty;y++){
                            for (int x=fx;x<=tx;x++){
                                sube=(Elements)Class.forName("util.utility."+list.get(i).getAttribute("type").getValue()).newInstance();
                                sube.setX(x);
                                sube.setY(y);
                                sube.setSize(size);
                                ((Group) elements).add(sube);
                            }
                        }

                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    elements.setX(list.get(i).getAttribute("x").getIntValue());
                    elements.setY(list.get(i).getAttribute("y").getIntValue());
                    elements.setSize(list.get(i).getAttribute("size").getIntValue());
                } catch (DataConversionException e) {
                    e.printStackTrace();
                }
                view.add(elements);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readconf(Configs conf){
        try {
            Document document=builder.build("./res/conf.xml");
            Element root=document.getRootElement();
            List<Element> list =root.getChildren();
            conf.setTitle(list.get(0).getText());
            conf.setFps(list.get(1).getAttribute("fps").getIntValue());
            conf.setX(list.get(1).getAttribute("resx").getIntValue());
            conf.setY(list.get(1).getAttribute("resy").getIntValue());
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
