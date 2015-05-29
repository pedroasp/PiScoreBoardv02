package estg.mee.piscoreboard.model;

/**
 * Created by Rúben Rodrigues on 27-05-2015.
 */
public class Colors {
    private int color;
    private String command;

    public Colors(int color, String command) {
        this.color = color;
        this.command = command;
    }

    public Colors(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
