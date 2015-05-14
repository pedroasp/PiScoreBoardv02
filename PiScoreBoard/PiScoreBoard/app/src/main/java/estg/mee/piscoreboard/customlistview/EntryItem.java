package estg.mee.piscoreboard.customlistview;


public class EntryItem implements Item {
	public final String title;
	public final String subtitle;
	public final int imageRId;
	
	/**
	 * Construtor
	 * @param title
	 * @param subtitle
	 * @param imageRId
	 */
	public EntryItem(String title, String subtitle, int imageRId) {
		this.title = title;
		this.subtitle = subtitle;
		this.imageRId = imageRId;
	}

	@Override
	public boolean isSection() {
		return false;
	}

	@Override
	public int isSettings() {

		return 0;
	}
}
