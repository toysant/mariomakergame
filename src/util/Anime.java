package util;

public abstract class Anime extends Elements {
    private int lifetime=0;

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
    public abstract boolean lifetimeover();
}
