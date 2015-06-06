package estg.mee.piscoreboard.model;

import java.io.Serializable;

/**
 * Created by Rúben Rodrigues on 01-05-2015.
 */
public class Team implements Serializable{
    private int id;
    private String name = new String();
    private String logotipo;
    private boolean uploadstate;
    private Modality modality ;

    public Team() {
        this.id = 0;
        this.name = null;
        this.logotipo = null;
        this.uploadstate = false;
    }

    public Modality getModality() {
        return modality;
    }

    public void setModality(Modality modality) {

        this.modality = modality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }

    public boolean isUploadstate() {
        return uploadstate;
    }

    public void setUploadstate(boolean uploadstate) {
        this.uploadstate = uploadstate;
    }

    public String getLogoName(){

        int index = logotipo.lastIndexOf('/');

        String name = logotipo.substring(index+1,logotipo.length());

        return name;
    }
}
