package estg.mee.piscoreboard.model;

import java.io.Serializable;
import java.util.ArrayList;

import estg.mee.piscoreboard.R;

/**
 * Created by Pedro on 28/05/2015.
 */


public class PiScoreBoard implements Serializable {

    boolean TimeMode = false;

    private ArrayList<Team> listOfTeams=  new ArrayList<Team>();
    private ArrayList<Modality> listOfModalities = new ArrayList<Modality>();
    private static PiScoreBoard instance = null;
    private boolean pubEnable ;
    private boolean faultsEnable = true ;
    private int pubPeriod;
    private String ipAdress = "10.5.5.1";
    private int port = 9999;
    private String password;
    private boolean videoState = false;

    public PiScoreBoard() {
        listOfModalities.add(new Modality(0, 2, 6, "Futsal", R.drawable.football_ball));
<<<<<<< HEAD
        listOfModalities.add(new Modality(1, 2, 0, "Basquetebol",R.drawable.basketball_ball));
=======
        listOfModalities.add(new Modality(1, 4, 0, "Basquetebol",R.drawable.basketball_ball));
>>>>>>> origin/master
        listOfModalities.add(new Modality(2, 2, 0, "Andebol",R.drawable.handball_ball));
    }

    public static PiScoreBoard getInstance(){
        if (instance == null){
            instance = new PiScoreBoard();
        }
        return instance;
    }

    public void setListOfTeams(ArrayList<Team> listOfTeams) {
        this.listOfTeams = listOfTeams;
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

    public boolean isFaultsEnable() {
        return faultsEnable;
    }

    public void setFaultsEnable(boolean faltsEnable) {
        this.faultsEnable = faltsEnable;
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

    public int getMatchStringArray (String string, String[] array){
        int i;
        int output = 0;
        for(i=0;i<array.length;i++){
            if (array[i].equals(string)){
                output  = i;
                break;
            }
        }
        return output;
    }

    public boolean isVideoState() {
        return videoState;
    }

    public void setVideoState(boolean videoState) {
        this.videoState = videoState;
    }
}

