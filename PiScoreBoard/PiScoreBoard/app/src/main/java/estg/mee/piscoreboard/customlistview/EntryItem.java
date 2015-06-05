package estg.mee.piscoreboard.customlistview;


public class EntryItem implements Item {
	public final String title;
	public final String subtitle;
    public final String actualValue;
	public final String imagePath;
   // public final int imageRId;
	
	/**
	 * Construtor
	 * @param title
	 * @param subtitle
	 * @param imagePath
	 */
	public EntryItem(String title, String subtitle,String actualValue, String imagePath) {
		this.title = title;
		this.subtitle = subtitle;
        this.actualValue = actualValue;
		this.imagePath = imagePath;
      //  this.imageRId = imageRId;
	}

    @Override
    public String getTitle() {
        return title;
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
