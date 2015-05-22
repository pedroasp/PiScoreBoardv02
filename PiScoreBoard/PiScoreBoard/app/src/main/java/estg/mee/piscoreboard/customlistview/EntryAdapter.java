package estg.mee.piscoreboard.customlistview;
import java.util.ArrayList;

import android.content.Context;
import android.preference.ListPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import estg.mee.piscoreboard.R;


public class EntryAdapter extends ArrayAdapter<Item> {
	private Context context;
	private ArrayList<Item> items;
	private LayoutInflater vi;
		
	/**
	 * Constructor
	 * @param context
	 * @param items
	 */
	public EntryAdapter(Context context, ArrayList<Item> items) {
		super(context, 0, items);
		this.context = context;
		this.items = items;
		vi = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	@Override
 	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		
		final Item i = this.items.get(position);
		if (i != null) {
			if (i.isSection()) {
				// Section Item
				SectionItem si = (SectionItem)i;
				v = vi.inflate(R.layout.list_item_section, null);
				
				v.setOnClickListener(null);
				v.setOnLongClickListener(null);
				v.setLongClickable(false);
				
				final TextView sectionView = (TextView)v.findViewById(R.id.list_item_section_text);
				sectionView.setText(si.getTitle());

			} else if (i.isSettings()==1) {

				// Entry Item Switch
				EntryItemSwitch eis = (EntryItemSwitch)i;

				v = vi.inflate(R.layout.list_item_entry_switch, null);
				final TextView title = (TextView)v.findViewById(R.id.list_item_entry_title);
				final TextView subtitle = (TextView)v.findViewById(R.id.list_item_entry_summary);
				final Switch switch1 = (Switch)v.findViewById(R.id.list_item_entry_switch);

				if (title != null)
					title.setText(eis.title);
				
				if (subtitle != null)
					if (eis.subtitle != null)
						subtitle.setText(eis.subtitle);
					else
						subtitle.setHeight(0);
				
				if (switch1 != null)
					switch1.setChecked(eis.switchState);

				final int eisId = eis.id;
				switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// Log.d("TAGX", "Switch " + eisId + " is " + (isChecked ? "on" : "off"));
						//SettingsFragment.switchOnClick(eisId, isChecked);
					}
				});
            } else if (i.isSettings()== 2) {

                // Entry Item Checkbox
                EntryItemCheckbox eicb = (EntryItemCheckbox)i;

                v = vi.inflate(R.layout.list_item_entry_checkbox, null);
                final TextView title = (TextView)v.findViewById(R.id.list_item_entry_title);
                final TextView subtitle = (TextView)v.findViewById(R.id.list_item_entry_summary);
                final CheckBox checkbox1 = (CheckBox)v.findViewById(R.id.list_item_entry_checkbox);

                if (title != null)
                    title.setText(eicb.title);

                if (subtitle != null)
                    if (eicb.subtitle != null)
                        subtitle.setText(eicb.subtitle);
                    else
                        subtitle.setHeight(0);

                if (checkbox1 != null)
                    checkbox1.setChecked(eicb.checkboxState);

                final int eicbId = eicb.id;

                checkbox1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // Log.d("TAGX", "Switch " + eisId + " is " + (isChecked ? "on" : "off"));
                        //SettingsFragment.switchOnClick(eisId, isChecked);
                    }
                });
            } else if (i.isSettings()== 3) {

                // Entry Item Button
                EntryItemButton eib = (EntryItemButton)i;

                v = vi.inflate(R.layout.list_item_entry_button, null);
                final Button button = (Button)v.findViewById(R.id.list_item_entry_button);

                if (button != null)
                    button.setText(eib.text);

            } else if (i.isSettings()== 4) {

                // Entry Item Two Buttons
                EntryItemTwoButtons eitb = (EntryItemTwoButtons)i;

                v = vi.inflate(R.layout.list_item_entry_twobuttons, null);
                final Button bt1 = (Button)v.findViewById(R.id.list_item_entry_button1);
                final Button bt2 = (Button)v.findViewById(R.id.list_item_entry_button2);


                bt1.setText(eitb.text1);
                bt2.setText(eitb.text2);

            } else {
				// Entry Item
				EntryItem ei = (EntryItem)i;
				v = vi.inflate(R.layout.list_item_entry, null);
				final TextView title = (TextView)v.findViewById(R.id.list_item_entry_title);
				final TextView subtitle = (TextView)v.findViewById(R.id.list_item_entry_summary);
                final TextView actualValue = (TextView)v.findViewById(R.id.list_item_entry_title2);
				final ImageView image = (ImageView)v.findViewById(R.id.list_item_entry_drawable);
				
				if (title != null)
					title.setText(ei.title);
				
				if (subtitle != null)
					if (ei.subtitle != null)
						subtitle.setText(ei.subtitle);
					else
						subtitle.setHeight(0);

                if (actualValue != null)
                    actualValue.setText(ei.actualValue);
				
				if (ei.imageRId != 0) {
                    image.requestLayout();
                    final float scale = getContext().getResources().getDisplayMetrics().density;
                    int pixels = (int) (40 * scale + 0.5f);
                    image.getLayoutParams().height = pixels;
                    image.getLayoutParams().width = pixels;
                    image.setImageResource(ei.imageRId);
                }
			}
		}
		return v;
	}
}
