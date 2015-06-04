package estg.mee.piscoreboard.model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Pedro on 28/05/2015.
 */
public class PiScoreBoard {
    boolean TimeMode = true;
    Context context;

    ArrayList<Team> listOfTeams=  new ArrayList<Team>();
    ArrayList<Modality> listOfModalities = new ArrayList<Modality>();
    private static PiScoreBoard instance = null;

    public PiScoreBoard() {

    }

    public static PiScoreBoard getInstance(){
        if (instance == null){
            instance = new PiScoreBoard();
        }
        return instance;
    }

    public  ArrayList<Team> getListOfTeams() {
        return listOfTeams;
    }

    public ArrayList<Modality> getListOfModalities() {
        return listOfModalities;
    }

    public int aaa(){

        return 1;
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
