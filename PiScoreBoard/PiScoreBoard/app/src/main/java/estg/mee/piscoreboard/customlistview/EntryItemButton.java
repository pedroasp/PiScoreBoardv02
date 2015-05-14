package estg.mee.piscoreboard.customlistview;

/**
 * Created by Pedro on 13/05/2015.
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
    public boolean isSection() {
        return false;
    }

    @Override
    public int isSettings() {
        return 3;
    }
}
