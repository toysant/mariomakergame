package util;

public abstract class Derivative extends Elements {
    private boolean highspeed=false;
    boolean isin=false;
    String animename=null;
    boolean play=false;

    public boolean isHighspeed() {
        return highspeed;
    }

    public void setHighspeed(boolean highspeed) {
        this.highspeed = highspeed;
    }

    public Anime getAnime(){
        try {
            Anime anime=(Anime) Class.forName("util.utility."+getAnimename()).newInstance();
            anime.setX(getX());
            anime.setY(getY()+getSize());
            anime.setSize(getSize());
            anime.setInsideob(this);
            return anime;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public String getAnimename() {
        return animename;
    }

    public void setAnimename(String animename) {
        this.animename = animename;
    }

    public boolean isIsin() {
        return isin;
    }

    public void setIsin(boolean isin) {
        this.isin = isin;
    }

    public abstract Elements deal(Elements e) ;
    public abstract void go();
}
