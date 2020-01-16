package act;


import util.Elements;
import util.View;
import util.utility.Group;

import java.util.List;

class Filter {
    void gridlayoutinit(View view){
        view.getUniversalelements().forEach(elements->{
            elements.setX(elements.getX()*100);
            elements.setY(elements.getY()*100);
            elements.setSize(elements.getSize()*100);
            if(elements instanceof Group){
                ((Group)elements).getElementsList().forEach(elements1 -> {

                    elements1.setX(elements1.getX()*100);
                    elements1.setY(elements1.getY()*100);
                    elements1.setSize(elements1.getSize()*100);
                });
            }
        });
    }

}
