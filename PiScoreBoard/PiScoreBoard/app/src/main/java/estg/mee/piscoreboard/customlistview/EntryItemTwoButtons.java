package estg.mee.piscoreboard.customlistview;

/**
 * Created by Pedro on 13/05/2015.
 */
public class EntryItemTwoButtons implements Item {

    public final int id1, id2;
    public final String text1, text2;

    /**
     * Construtor
     * @param id1
     * @param text1
     * @param id2
     * @param text2
     */
    public EntryItemTwoButtons(int id1, String text1,int id2, String text2) {
        this.id1 = id1;
        this.text1 = text1;
        this.id2 = id2;
        this.text2 = text2;
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
        return 4;
    }
}

