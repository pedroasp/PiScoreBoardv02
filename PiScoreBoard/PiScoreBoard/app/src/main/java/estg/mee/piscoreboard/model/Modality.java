package estg.mee.piscoreboard.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * @author Rúben Rodrigues
 * @version 1.0 14/05/2015
 * Class das modalidades. Contém as caracteristicas de cada modalidade.
 */
public class Modality implements Serializable{
    private int id;
    private SimpleDateFormat gameTime = new SimpleDateFormat("HH:mm:ss");
    private int nParts;
    private int nFaults;
    private String name;
    private int imageRid;

    public Modality() {

    }

    /**
     *
     * @param id
     * @param nParts
     * @param nFaults
     * @param name
     * @param imageRid
     */
    public Modality (int id, int nParts, int nFaults, String name, int imageRid){
        this.id = id;
        this.nParts = nParts;
        this.nFaults = nFaults;
        this.name = name;
        this.imageRid = imageRid;
    }

    /**
     *
     * @return String - Nome da modalidade
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name - Nome da modalidade
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return id - Devolve o Id da modalidade
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id - Id da modalidade
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return gameTime - devolve o tempo das modalidades
     */
    public SimpleDateFormat getGameTime() {
        return gameTime;
    }

    /**
     *
     * @param gameTime - tempo da modalidade
     */
    public void setGameTime(SimpleDateFormat gameTime) {
        this.gameTime = gameTime;
    }

    /**
     *
     * @return nParts - Devolve o numero de partes que contem um jogo
     */
    public int getnParts() {
        return nParts;
    }

    /**
     *
     * @param nParts - numero de partes que contem um jogo
     */
    public void setnParts(int nParts) {
        this.nParts = nParts;
    }

    public int getnFaults() {
        return nFaults;
    }

    public void setnFaults(int nFaults) {
        this.nFaults = nFaults;
    }

    public int getImageRid() {
        return imageRid;
    }

    public void setImageRid(int imageRid) {
        this.imageRid = imageRid;
    }
}
