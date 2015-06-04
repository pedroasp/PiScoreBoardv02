package estg.mee.piscoreboard.model;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;


import estg.mee.piscoreboard.controller.MainActivity;

/**
 * Created by Rúben Rodrigues on 27-05-2015.
 */
public class Graphics {
    public Colors BackgroundCentralColor = new Colors(Color.MAGENTA,"gcentralbgcolor");
    public Colors BackgroundSideColor = new Colors(Color.WHITE,"gsidesbgcolor");
    public Colors ResultColor  = new Colors(Color.BLACK,"grColor");
    public Colors FaultColor  = new Colors(Color.BLACK,"gfColor");
    public Colors NamesColor  = new Colors(Color.BLACK,"gnColor");
    public Colors PartColor  = new Colors(Color.BLACK,"gPColor");
    public Colors TimeColor  = new Colors(Color.BLACK,"gcColor");

    private Context context;


    public Graphics(Context context) {
        this.context = context;
    }

    public int getBackgroundCentralColor() {
        return BackgroundCentralColor.getColor();
    }

    public void setBackgroundCentralColor(int backgroundCentralColor) {
        BackgroundCentralColor.setColor(backgroundCentralColor);
    }


    public int getBackgroundSideColor() {
        return BackgroundSideColor.getColor();
    }

    public void setBackgroundSideColor(int backgroundSideColor) {
        BackgroundSideColor.setColor(backgroundSideColor);
    }

    public int getResultColor() {
        return ResultColor.getColor();
    }

    public void setResultColor(int resultColor) {
        ResultColor.setColor(resultColor);
    }

    public int getFaultColor() {
        return FaultColor.getColor();
    }

    public void setFaultColor(int faultColor) {
        FaultColor.setColor(faultColor);
    }

    public int getNamesColor() {
        return NamesColor.getColor();
    }

    public void setNamesColor(int namesColor) {
        NamesColor.setColor(namesColor);
    }

    public int  getPartColor() {
        return PartColor.getColor();
    }

    public void setPartColor(int partColor) {
        PartColor.setColor(partColor);
    }

    public int getTimeColor() {
        return TimeColor.getColor();
    }

    public void setTimeColor(int timeColor) {
        TimeColor.setColor(timeColor);
    }


    public void sendColorCommand (Colors destination, int color,boolean checkConnection){
        String rgb = "@" + Color.red(color) + "," + Color.green(color) + "," + Color.blue(color) + "@";
        MainActivity activity = (MainActivity) context;
        activity.sendCommand(destination.getCommand().concat(rgb),checkConnection);
    }
    public void sendColorCommand (Colors destination,boolean checkConnection){
        String rgb = "@" + Color.red(destination.getColor()) + "," + Color.green(destination.getColor()) + "," + Color.blue(destination.getColor()) + "@";
        MainActivity activity = (MainActivity) context;
        activity.sendCommand(destination.getCommand().concat(rgb),checkConnection);
    }
    public void sendColorCommandArray (ArrayList<Colors> destination,boolean checkConnection){

        String stringToSend = new String();
        String rgb;
        for (Colors colors:destination){
            rgb = "@" + Color.red(colors.getColor()) + "," + Color.green(colors.getColor()) + "," + Color.blue(colors.getColor()) + "@";
            stringToSend = stringToSend.concat(colors.getCommand()).concat(rgb+"\r\n");
        }
        MainActivity activity = (MainActivity) context;
        activity.sendCommand(stringToSend,checkConnection);
    }
}
