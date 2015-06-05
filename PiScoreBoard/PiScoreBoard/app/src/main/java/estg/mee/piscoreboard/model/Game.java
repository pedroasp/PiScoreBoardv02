package estg.mee.piscoreboard.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rúben on 01-05-2015.
 */
public class Game implements Serializable {
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

    private Team equipaLocal = new Team();
    private Team equipaVisitante = new Team();

    private static Game instance = null;

    ArrayList <String> PublictyList = new ArrayList();


    public Game() {

    }

    public static Game getInstance(){
        if (instance == null){
            instance = new Game();
        }
        return instance;
    }

    public ArrayList getPublictyList() {
        return PublictyList;
    }

    public void setPublictyList(ArrayList<String> publictyList) {
        PublictyList = publictyList;
    }

    public Team getEquipaLocal() {
        return equipaLocal;
    }

    public void setEquipaLocal(Team equipaLocal) {
        this.equipaLocal = equipaLocal;
    }

    public Team getEquipaVisitante() {
        return equipaVisitante;
    }

    public void setEquipaVisitante(Team equipaVisitante) {
        this.equipaVisitante = equipaVisitante;
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
