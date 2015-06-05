package estg.mee.piscoreboard.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Created by Rúben on 14-05-2015.
 */
public class Modality implements Serializable{
    private int id;
    private SimpleDateFormat gameTime = new SimpleDateFormat("HH:mm:ss");
    private int nParts;
    private int nFaults;
    private String name;

    public Modality() {

    }

    public Modality (int id, int nParts, int nFaults, String name){
        this.id = id;
        this.nParts = nParts;
        this.nFaults = nFaults;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
