package estg.mee.piscoreboard.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rúben on 01-05-2015.
 */
public class Game {
    private int id;
    private int nLocalFaults = 0;
    private int nVisitFaults = 0;
    private int nPart = 1;
    private int nVisit = 0;
    private int nLocal = 0;
    private SimpleDateFormat totalTime = new SimpleDateFormat("HH:mm:ss");
    private int gameState;
    private Date date = new Date();
    private Modality modality = new Modality();
    private Part[] part = new Part[2];

    ArrayList PublictyList = new ArrayList();


    public Game() {

    }

    public ArrayList getPublictyList() {
        return PublictyList;
    }

    public void setPublictyList(ArrayList publictyList) {
        PublictyList = publictyList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getnLocalFaults() {
        return nLocalFaults;
    }

    public void setnLocalFaults(int nLocalFaults) {
        this.nLocalFaults = nLocalFaults;
    }

    public int getnVisitFaults() {
        return nVisitFaults;
    }

    public void setnVisitFaults(int nVisitFaults) {
        this.nVisitFaults = nVisitFaults;
    }

    public int getnPart() {
        return nPart;
    }

    public void setnPart(int nPart) {
        this.nPart = nPart;
    }

    public int getnVisit() {
        return nVisit;
    }

    public void setnVisit(int nVisit) {
        this.nVisit = nVisit;
    }

    public int getnLocal() {
        return nLocal;
    }

    public void setnLocal(int nLocal) {
        this.nLocal = nLocal;
    }

    public SimpleDateFormat getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(SimpleDateFormat totalTime) {
        this.totalTime = totalTime;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Modality getModality() {
        return modality;
    }

    public void setModality(Modality modality) {
        this.modality = modality;
    }

    public Part[] getPart() {
        return part;
    }

    public void setPart(Part[] part) {
        this.part = part;
    }
}
