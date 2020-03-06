package util;

public class InfoPack {
    private String mode;
    private String view;
    private boolean xoffsetable,yoffsetable;
    private boolean viewswitch,modeswitch;
    private int maxx,maxy;
    private ConstructPack constructPack;


    public boolean isViewswitch() {
        return viewswitch;
    }

    public void setViewswitch(boolean viewswitch) {
        this.viewswitch = viewswitch;
    }

    public boolean isModeswitch() {
        return modeswitch;
    }

    public void setModeswitch(boolean modeswitch) {
        this.modeswitch = modeswitch;
    }

    public boolean isYoffsetable() {
        return yoffsetable;
    }

    public void setYoffsetable(boolean yoffsetable) {
        this.yoffsetable = yoffsetable;
    }

    public boolean isXoffsetable() {
        return xoffsetable;
    }

    public void setXoffsetable(boolean xoffsetable) {
        this.xoffsetable = xoffsetable;
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

    public ConstructPack getConstructPack() {
        return constructPack;
    }

    public void setConstructPack(ConstructPack constructPack) {
        this.constructPack = constructPack;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
