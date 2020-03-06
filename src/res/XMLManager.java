package res;

import util.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import util.utility.Background;
import util.utility.Group;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XMLManager {
    private SAXBuilder builder=new SAXBuilder();
    public void build(View v){
        Element root;
        root=new Element("GridLayout");
        root.setAttribute("bz",""+v.getMul());
        if (v.isXoffsetable()) {
            root.setAttribute("xoffsetable","true");
            root.setAttribute("maxx",""+v.getMaxx());
        }
        else {
            root.setAttribute("xoffsetable","false");
        }
        if (v.isYoffsetable()) {
            root.setAttribute("yoffsetable","true");
            root.setAttribute("maxy",""+v.getMaxy());
        }
        else {
            root.setAttribute("yoffsetable","false");
        }
        Element es;
        for (int i=0;i<v.getUniversalelements().size();i++){
            Elements elements=v.getUniversalelements().get(i);
            if (elements instanceof Group){
               List<Elements> l=((Group)elements).getElementsList();
                for (int x=0;x<l.size();x++){
                    es = new Element(l.get(x).getClass().getSimpleName());
                    es.setAttribute("x",""+l.get(x).getX()/v.getMul());
                    es.setAttribute("y",""+l.get(x).getY()/v.getMul());
                    es.setAttribute("size",""+1);
                    if (elements.getInside()!=null){
                        es.setAttribute("inside",l.get(x).getInside());
                    }
                    root.addContent(es);
                }
            }
            else {
                es = new Element(elements.getClass().getSimpleName());
                es.setAttribute("x",""+elements.getX()/v.getMul());
                es.setAttribute("y",""+elements.getY()/v.getMul());
                es.setAttribute("size",""+1);
                if (elements.getInside()!=null){
                    es.setAttribute("inside",elements.getInside());
                }
                root.addContent(es);
            }
        }
        Document doc=new Document(root);
        Format format=Format.getCompactFormat();
        format.setEncoding("UTF-8");
        XMLOutputter outter=new XMLOutputter(format);
        try {
            outter.output(doc,new FileOutputStream(v.getFilename()));
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
            String layout=root.getName();
            int mul=1;
            view.setLayout(layout);
            List<Element> list =root.getChildren();
            if(layout.equals("GridLayout")){
                if(root.getAttributes().size()!=0) {
                    if (root.getAttribute("bz")!=null) {
                        mul = root.getAttribute("bz").getIntValue();
                    }
                }

            }
            view.setMul(mul);
            if (root.getAttribute("xoffsetable")!=null){
                view.setXoffsetable(root.getAttribute("xoffsetable").getBooleanValue());
                if (root.getAttribute("maxx")!=null&&view.isXoffsetable()){
                    view.setMaxx(root.getAttribute("maxx").getIntValue());
                }
            }
            if (root.getAttribute("yoffsetable")!=null){
                view.setYoffsetable(root.getAttribute("yoffsetable").getBooleanValue());
                if (root.getAttribute("maxy")!=null&&view.isYoffsetable()){
                    view.setMaxx(root.getAttribute("maxy").getIntValue());
                }
            }
            Elements elements=null;
            Elements sube=null;
            int ty=0,tx=0,fx=0,fy=0,size=0;
            for(int i=0;i<list.size();i++){
                try {
                    elements= (Elements) Class.forName("util.utility."+list.get(i).getName()).newInstance();
                    if (elements instanceof Background){
                        elements.setResource(list.get(i).getAttribute("res").getValue());
                    }
                    if (elements instanceof Group){
                        String inside=null;
                        ty=list.get(i).getAttribute("ty").getIntValue();
                        tx=list.get(i).getAttribute("tx").getIntValue();
                        fx=list.get(i).getAttribute("x").getIntValue();
                        fy=list.get(i).getAttribute("y").getIntValue();
                        size=list.get(i).getAttribute("size").getIntValue();
                        if (list.get(i).getAttribute("inside")!=null) {
                            inside=list.get(i).getAttribute("inside").getValue();
                        }
                        for (int y=fy;y<=ty;y++){
                            for (int x=fx;x<=tx;x++){
                                sube=(Elements)Class.forName("util.utility."+list.get(i).getAttribute("type").getValue()).newInstance();
                                sube.setX(x*mul);
                                sube.setY(y*mul);
                                sube.setSize(size * mul);
                                if (inside!=null) {
                                    sube.setInside(inside);
                                }
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
                    elements.setX(list.get(i).getAttribute("x").getIntValue()*mul);
                    elements.setY(list.get(i).getAttribute("y").getIntValue()*mul);
                    elements.setSize(list.get(i).getAttribute("size").getIntValue()*mul);
                    if (list.get(i).getAttribute("inside")!=null) {
                        elements.setInside(list.get(i).getAttribute("inside").getValue());
                    }
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
            conf.setTitle(root.getChild("title").getText());
            conf.setFps(root.getChild("numinfo").getAttribute("fps").getIntValue());
            conf.setX(root.getChild("numinfo").getAttribute("resx").getIntValue());
            conf.setY(root.getChild("numinfo").getAttribute("resy").getIntValue());
            conf.setSize(root.getChild("numinfo").getAttribute("size").getIntValue());
            conf.setMode(root.getChild("Mode").getAttribute("name").getValue());
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readmenu(Menu menu){
        Document document= null;
        try {
            document = builder.build(menu.getXmlpath());
            Element root=document.getRootElement();
            int mul=1;
            List<Element> list =root.getChildren();
            if(root.getAttributes().size()!=0) {
                if (root.getAttribute("bz")!=null) {
                    mul = root.getAttribute("bz").getIntValue();
                }
            }
            Elements elements=null;
            for(int i=0;i<list.size();i++){
                try {
                    elements= (Elements) Class.forName("util.utility."+list.get(i).getName()).newInstance();
                    elements.setX(list.get(i).getAttribute("x").getIntValue()*mul);
                    elements.setY(list.get(i).getAttribute("y").getIntValue()*mul);
                    elements.setSize(list.get(i).getAttribute("size").getIntValue()*mul);
                    if (list.get(i).getAttribute("inside")!=null) {
                        elements.setInside(list.get(i).getAttribute("inside").getValue());
                    }
                    menu.getContain().add(elements);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
