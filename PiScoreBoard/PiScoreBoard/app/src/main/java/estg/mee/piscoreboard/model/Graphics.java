package estg.mee.piscoreboard.model;

import android.content.Context;
import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;


import estg.mee.piscoreboard.controller.MainActivity;

/**
 * @author Rúben Rodrigues
 * @version 1.0 27/05/2015
 * Contém todos os dados relativos ao ambiente gráfico da aplicação do raspberry-pi
 */
<<<<<<< HEAD
public class Graphics implements Serializable {

    public Colors BackgroundCentralColor = new Colors(Color.RED,"gcentralbgcolor");
=======
public class Graphics {

    public Colors BackgroundCentralColor = new Colors(Color.MAGENTA,"gcentralbgcolor");
>>>>>>> origin/master
    public Colors BackgroundSideColor = new Colors(Color.WHITE,"gsidesbgcolor");
    public Colors ResultColor  = new Colors(Color.BLACK,"grColor");
    public Colors FaultColor  = new Colors(Color.BLACK,"gfColor");
    public Colors NamesColor  = new Colors(Color.BLACK,"gnColor");
    public Colors PartColor  = new Colors(Color.BLACK,"gPColor");
    public Colors TimeColor  = new Colors(Color.BLACK,"gcColor");

    private Context context;

    /**
     *
     * @param context
     */
    public Graphics(Context context) {
        this.context = context;
    }

    /**
     *
     * @return backgroundCentralColor
     */
    public int getBackgroundCentralColor() {
        return BackgroundCentralColor.getColor();
    }

    /**
     *
     * @param backgroundCentralColor
     */
    public void setBackgroundCentralColor(int backgroundCentralColor) {
        BackgroundCentralColor.setColor(backgroundCentralColor);
    }

    /**
     *
     * @return backgroundSideColor
     */
    public int getBackgroundSideColor() {
        return BackgroundSideColor.getColor();
    }

    /**
     *
     * @param backgroundSideColor
     */
    public void setBackgroundSideColor(int backgroundSideColor) {
        BackgroundSideColor.setColor(backgroundSideColor);
    }

    /**
     *
     * @return resultColor
     */
    public int getResultColor() {
        return ResultColor.getColor();
    }

    /**
     *
     * @param resultColor
     */
    public void setResultColor(int resultColor) {
        ResultColor.setColor(resultColor);
    }

    /**
     *
     * @return faultColor
     */
    public int getFaultColor() {
        return FaultColor.getColor();
    }

    /**
     *
     * @param faultColor
     */
    public void setFaultColor(int faultColor) {
        FaultColor.setColor(faultColor);
    }

    /**
     *
     * @return namesColor
     */
    public int getNamesColor() {
        return NamesColor.getColor();
    }

    /**
     *
     * @param namesColor
     */
    public void setNamesColor(int namesColor) {
        NamesColor.setColor(namesColor);
    }

    /**
     *
     * @return partColor
     */
    public int  getPartColor() {
        return PartColor.getColor();
    }

    /**
     *
     * @param partColor
     */
    public void setPartColor(int partColor) {
        PartColor.setColor(partColor);
    }

    /**
     *
     * @return timeColor
     */
    public int getTimeColor() {
        return TimeColor.getColor();
    }

    /**
     *
     * @param timeColor
     */
    public void setTimeColor(int timeColor) {
        TimeColor.setColor(timeColor);
    }

    /**
     *
     * @param destination
     * @param color
     * @param checkConnection
     */
    public void sendColorCommand (Colors destination, int color,boolean checkConnection){
        String rgb = "@" + Color.red(color) + "," + Color.green(color) + "," + Color.blue(color) + "@";
        MainActivity activity = (MainActivity) context;
        activity.sendCommand(destination.getCommand().concat(rgb),checkConnection);
    }

    /**
     *
     * @param destination
     * @param checkConnection
     */
    public void sendColorCommand (Colors destination,boolean checkConnection){
        String rgb = "@" + Color.red(destination.getColor()) + "," + Color.green(destination.getColor()) + "," + Color.blue(destination.getColor()) + "@";
        MainActivity activity = (MainActivity) context;
        activity.saveData();
        activity.sendCommand(destination.getCommand().concat(rgb),checkConnection);
    }

    /**
     *
     * @param destination
     * @param checkConnection
     */
    public void sendColorCommandArray (ArrayList<Colors> destination,boolean checkConnection){

        String stringToSend = new String();
        String rgb;
        for (Colors colors:destination){
            rgb = "@" + Color.red(colors.getColor()) + "," + Color.green(colors.getColor()) + "," + Color.blue(colors.getColor()) + "@";
            stringToSend = stringToSend.concat(colors.getCommand()).concat(rgb+"\r\n");
        }
        MainActivity activity = (MainActivity) context;
        activity.saveData();
        activity.sendCommand(stringToSend,checkConnection);
    }
}
