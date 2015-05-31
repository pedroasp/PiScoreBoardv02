package estg.mee.piscoreboard.model;

import java.util.ArrayList;

/**
 * Created by Pedro on 28/05/2015.
 */
public class PiScoreBoard {

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
}
