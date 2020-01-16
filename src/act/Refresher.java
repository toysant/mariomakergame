package act;

public class Refresher extends Thread {
    private GraphicsPanel panel=null;
    private static long stime,wtime;
    private static long fps=60,fpsnow=0;
    Refresher(GraphicsPanel panel){
        this.panel=panel;
        stime=1000L/fps;
    }
    @Override
    public void run() {
        while(true){
            refresh();
            try {
                Thread.sleep(wtime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    private void refresh(){
        panel.display();
        if (panel.getPainttime()>stime&&panel.getPainttime()>=1){
            wtime=panel.getPainttime();
        }
        else {
            wtime=stime;
        }
        fpsnow=1000L/wtime;
    }
    static void setFps(int f){
        if(f<=1000){
            fps=f;
            stime=1000L/f;
        }
        else {
            fps=1;
            stime=1;
        }
    }

}
