package android.vogella.de.androidclient;

import java.text.SimpleDateFormat;

/**
 * Created by Rúben on 14-05-2015.
 */
public class Modality {
    private int id;
    private SimpleDateFormat gameTime = new SimpleDateFormat("HH:mm:ss");
    private int nParts;
    private int nFaults;

    public Modality() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SimpleDateFormat getGameTime() {
        return gameTime;
    }

    public void setGameTime(SimpleDateFormat gameTime) {
        this.gameTime = gameTime;
    }

    public int getnParts() {
        return nParts;
    }

    public void setnParts(int nParts) {
        this.nParts = nParts;
    }

    public int getnFaults() {
        return nFaults;
    }

    public void setnFaults(int nFaults) {
        this.nFaults = nFaults;
    }
}
