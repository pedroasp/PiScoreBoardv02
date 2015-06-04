package estg.mee.piscoreboard.customlistview;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.controller.MainActivity;
import estg.mee.piscoreboard.model.Colors;


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
                final EntryItemButton eib = (EntryItemButton)i;

                v = vi.inflate(R.layout.list_item_entry_button, null);
                final Button button = (Button)v.findViewById(R.id.list_item_entry_button);

                    if (button != null)
                    button.setText(eib.text);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (eib.id){
                            case 1:
                                ArrayList<Colors> ColorsArrayList = new ArrayList<>();
                                ColorsArrayList.add(MainActivity.graphics.BackgroundCentralColor);
                                ColorsArrayList.add(MainActivity.graphics.BackgroundSideColor);
                                ColorsArrayList.add(MainActivity.graphics.FaultColor);
                                ColorsArrayList.add(MainActivity.graphics.NamesColor);
                                ColorsArrayList.add(MainActivity.graphics.PartColor);
                                ColorsArrayList.add(MainActivity.graphics.PartColor);
                                ColorsArrayList.add(MainActivity.graphics.ResultColor);
                                ColorsArrayList.add(MainActivity.graphics.TimeColor);
                                String stringToSend = new String();
                                String rgb;
                                for (Colors colors:ColorsArrayList){
                                    rgb = "@" + Color.red(colors.getColor()) + "," + Color.green(colors.getColor()) + "," + Color.blue(colors.getColor()) + "@";
                                    stringToSend = stringToSend.concat(colors.getCommand()).concat(rgb+"\r\n");
                                }
                                stringToSend = stringToSend.concat(context.getResources().getString(R.string.LocalName)).concat("@"+"Nome"+"@"+"\r\n");
                                stringToSend = stringToSend.concat(context.getResources().getString(R.string.VisitName)).concat("@"+"Nome"+"@");
                                //stringToSend = stringToSend.substring(0, stringToSend.length()-2);
                                MainActivity activity = (MainActivity) context;
                                activity.sendCommand(stringToSend,true);
                                ColorsArrayList.clear();

                                break;

                        }
                    }
                });
            } else if (i.isSettings()== 4) {

                // Entry Item Two Buttons
                final EntryItemTwoButtons eitb = (EntryItemTwoButtons)i;

                v = vi.inflate(R.layout.list_item_entry_twobuttons, null);
                final Button bt1 = (Button)v.findViewById(R.id.list_item_entry_button1);
                final Button bt2 = (Button)v.findViewById(R.id.list_item_entry_button2);


                bt1.setText(eitb.text1);
                bt2.setText(eitb.text2);

               bt1.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String stringToSend;
                       switch(eitb.id1){
                           case 2:
                               stringToSend = context.getResources().getString(R.string.Shutdown);
                               MainActivity activity = (MainActivity) context;
                               activity.sendCommand(stringToSend,true);
                               break;
                       }
                   }
               });

                bt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String stringToSend;
                        switch (eitb.id2){
                            case 3:
                                stringToSend = context.getResources().getString(R.string.Restart);
                                MainActivity activity = (MainActivity) context;
                                activity.sendCommand(stringToSend,true);
                                break;
                        }
                    }
                });
            } else {
				// Entry Item
				EntryItem ei = (EntryItem)i;
				v = vi.inflate(R.layout.list_item_entry, null);
				final TextView title = (TextView)v.findViewById(R.id.list_item_entry_title);
				final TextView subtitle = (TextView)v.findViewById(R.id.list_item_entry_summary);
                final TextView actualValue = (TextView)v.findViewById(R.id.list_item_entry_title2);
                ImageView image = (ImageView)v.findViewById(R.id.list_item_entry_drawable);

                if(ei.imagePath!=null) {
                    File imgFile = new File(ei.imagePath);

                    if (imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                        image.requestLayout();
                        final float scale = getContext().getResources().getDisplayMetrics().density;
                        int pixels = (int) (40 * scale + 0.5f);
                        image.getLayoutParams().height = pixels;
                        image.getLayoutParams().width = pixels;

                        image.setImageBitmap(myBitmap);

                    }
                }

				//final ImageView image = (ImageView)v.findViewById(R.id.list_item_entry_drawable);
				
				if (title != null)
					title.setText(ei.title);
				
				if (subtitle != null)
					if (ei.subtitle != null)
						subtitle.setText(ei.subtitle);
					else
						subtitle.setHeight(0);

                if (actualValue != null)
                    actualValue.setText(ei.actualValue);
				
//				if (ei.imageRId != 0) {
//                    image.requestLayout();
//                    final float scale = getContext().getResources().getDisplayMetrics().density;
//                    int pixels = (int) (40 * scale + 0.5f);
//                    image.getLayoutParams().height = pixels;
//                    image.getLayoutParams().width = pixels;
//                    image.setImageResource(ei.imageRId);
//                }
			}
		}
		return v;
	}
}
