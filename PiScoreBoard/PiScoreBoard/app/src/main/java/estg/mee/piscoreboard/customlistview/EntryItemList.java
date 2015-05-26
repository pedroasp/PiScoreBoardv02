package estg.mee.piscoreboard.customlistview;

/**
 * Created by Pedro on 11/05/2015.
 */
public class EntryItemList implements Item {

    public final int id;
    public final String title;
    public final String subtitle;
   // public final boolean checkboxState;

    /**
     * Construtor
     * @param title
     * @param subtitle
     * @param state
     */
    public EntryItemList(int id, String title, String subtitle, boolean state) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        //this.checkboxState = state;
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
