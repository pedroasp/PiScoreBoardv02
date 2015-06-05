package estg.mee.piscoreboard.model;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Pedro on 28/05/2015.
 */
public class PiScoreBoard implements Serializable{
    boolean TimeMode = true;
    Context context;

    private ArrayList<Team> listOfTeams=  new ArrayList<Team>();
    private ArrayList<Modality> listOfModalities = new ArrayList<Modality>();
    private static PiScoreBoard instance = null;
    private boolean pubEnable ;
    private int pubPeriod;
    private String ipAdress;
    private int port;
    private String password;

    public PiScoreBoard() {
        listOfModalities.add(new Modality(1, 2, 0, "Futsal"));
        listOfModalities.add(new Modality(2, 4, 0, "Basquetebol"));
        listOfModalities.add(new Modality(3, 2, 0, "Andebol"));
    }

    public static PiScoreBoard getInstance(){
        if (instance == null){
            instance = new PiScoreBoard();
        }
        return instance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public int getPubPeriod() {
        return pubPeriod;
    }

    public void setPubPeriod(int pubPeriod) {
        this.pubPeriod = pubPeriod;
    }

    public boolean isPubEnable() {
        return pubEnable;
    }

    public void setPubEnable(boolean pubEnable) {
        this.pubEnable = pubEnable;
    }

    public  ArrayList<Team> getListOfTeams() {
        return listOfTeams;
    }

    public ArrayList<Modality> getListOfModalities() {

        return listOfModalities;
    }

    public String [] getModalitiesName(ArrayList<Modality> listOfModalities) {
        String[] array = new String[listOfModalities.size()];
        int i = 0;

        for (Modality modality : listOfModalities) {
            array[i] = modality.getName();
            i++;
        }
        return array;
    }

    public String [] getTeamsName(ArrayList<Team> listOfTeams){
        String [] array = new String[listOfTeams.size()];
        int i = 0;

        for(Team team : listOfTeams){
            array[i] = team.getName();
            i++;
        }

        return array;
    }

    public boolean isTimeMode() {
        return TimeMode;
    }

    public void setTimeMode(boolean timeMode) {
        TimeMode = timeMode;
    }

    public String getStringTimeMode (){
        if(this.isTimeMode()){
            return "Cronómetro";
        }else{
            return "Relógio";
        }
    }
}
