package estg.mee.piscoreboard.customlistview;


public class SectionItem implements Item {
	private final String title;
	
	/**
	 * Construtor
	 * @param title
	 */
	public SectionItem(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	@Override
	public boolean isSection() {
		return true;
	}

	@Override
	public int isSettings() {
		return 0;
	}
}
