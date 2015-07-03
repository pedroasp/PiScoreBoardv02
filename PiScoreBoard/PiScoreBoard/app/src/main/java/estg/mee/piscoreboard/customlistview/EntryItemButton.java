package estg.mee.piscoreboard.customlistview;

/**
 * @author Pedro Pires
 * @version 1.0 13/05/2015
 * Esta class
 */
public class EntryItemButton implements Item{

    public final int id;
    public final String text;

    /**
     * Construtor
     * @param id
     * @param text
     */
    public EntryItemButton(int id, String text) {
        this.id = id;
        this.text = text;
    }
    @Override
    public String getTitle() {
        return "";

    }
    @Override
    public boolean isSection() {
        return false;
    }

    @Override
    public int isSettings() {
        return 3;
    }
}
