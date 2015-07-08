package estg.mee.piscoreboard.model;

import java.io.Serializable;

/**
 * @author Rúben Rodrigues
 * @version 1.0 27-05-2015
 * Esta class guarda o valor da cor e respetivo comando para o raspberry-pi
 */
public class Colors implements Serializable {
    private int color;
    private String command;

    /**
     *
     * @param color
     * @param command
     */
    public Colors(int color, String command) {
        this.color = color;
        this.command = command;
    }

    /**
     *
     * @param color
     */
    public Colors(int color) {
        this.color = color;
    }

    /**
     *
     * @return color
     */
    public int getColor() {
        return color;
    }

    /**
     *
     * @param color
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     *
     * @return Command
     */
    public String getCommand() {
        return command;
    }

    /**
     *
     * @param command
     */
    public void setCommand(String command) {
        this.command = command;
    }

}
