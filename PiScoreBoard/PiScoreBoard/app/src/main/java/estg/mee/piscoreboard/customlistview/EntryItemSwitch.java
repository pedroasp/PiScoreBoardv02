package estg.mee.piscoreboard.customlistview;


public class EntryItemSwitch implements Item {
	public final int id;
	public final String title;
	public final String subtitle;
	public final boolean switchState;
	
	/**
	 * Construtor
	 * @param title
	 * @param subtitle
	 * @param state
	 */
	public EntryItemSwitch(int id, String title, String subtitle, boolean state) {
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.switchState = state;
	}
    @Override
    public String getTitle() {
        return "";

    }
    public boolean isSwitch() {
        return true;
    }
	@Override
	public boolean isSection() {
		return false;
	}

	@Override
	public int isSettings() {
		return 1;
	}
}
