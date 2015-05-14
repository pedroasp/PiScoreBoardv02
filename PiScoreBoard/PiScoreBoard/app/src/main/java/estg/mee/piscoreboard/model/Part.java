package android.vogella.de.androidclient;

import java.text.SimpleDateFormat;

/**
 * Created by Ruben on 14-05-2015.
 */
public class Part {
    private int nLocalFaults = 0;
    private int nVisitFaults = 0;
    private int nParts = 0;
    private int nVisit = 0;
    private int nLocal = 0;
    private SimpleDateFormat partTime = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat elapsedTime = new SimpleDateFormat("HH:mm:ss");

    public Part() {
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

    public int getnParts() {
        return nParts;
    }

    public void setnParts(int nParts) {
        this.nParts = nParts;
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


    public SimpleDateFormat getPartTime() {
        return partTime;
    }

    public void setPartTime(SimpleDateFormat partTime) {
        this.partTime = partTime;
    }

    public SimpleDateFormat getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(SimpleDateFormat elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
