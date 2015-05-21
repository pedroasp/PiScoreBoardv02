package estg.mee.piscoreboard.controller;

//import android.app.Fragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.customlistview.EntryAdapter;
import estg.mee.piscoreboard.customlistview.EntryItem;
import estg.mee.piscoreboard.customlistview.EntryItemSwitch;
import estg.mee.piscoreboard.customlistview.EntryItemTwoButtons;
import estg.mee.piscoreboard.customlistview.Item;
import estg.mee.piscoreboard.customlistview.SectionItem;

/**
 * Created by Pedro on 07/05/2015.
 */
public class SettingsFragment extends Fragment {


    private View rootView = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        this.rootView = rootView;

        this.initSettingsFields();

        return rootView;
    }

    private void initSettingsFields(){


        ListView settingsList = (ListView)this.rootView.findViewById(R.id.settings_lv);
        ArrayList<Item> items = new ArrayList<Item>();

        items.add(new SectionItem(getResources().getString(R.string.sectionComunicacao)));
        items.add(new EntryItem(getResources().getString(R.string.itemIPAdress), getResources().getString(R.string.summaryitemIPAdress), 0));
        items.add(new EntryItem(getResources().getString(R.string.itemPorto), getResources().getString(R.string.summaryItemPorto), 0));

        items.add(new SectionItem(getResources().getString(R.string.sectionTemporizacao)));
        items.add(new EntryItem(getResources().getString(R.string.itemTemporizacao), getResources().getString(R.string.summaryItemTemporizacao), 0));

        items.add(new SectionItem(getResources().getString(R.string.sectionPublicidade)));
        items.add(new EntryItemSwitch(1, getResources().getString(R.string.itemPublicidade),"" , true));
        items.add(new EntryItem(getResources().getString(R.string.itemPeriodoPublicidade), getResources().getString(R.string.summaryItemPeriodoPublicidade), 0));

        items.add(new SectionItem(getResources().getString(R.string.sectionControloRemoto)));
        items.add(new EntryItemTwoButtons(1,getResources().getString(R.string.labelDesligar),2, getResources().getString(R.string.labelReiniciar) ));


        EntryAdapter adapter = new EntryAdapter(getActivity(), items);
        settingsList.setAdapter(adapter);




        settingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Object listItem = settingsList.getItemAtPosition(position);
                String[] temporizacao = {"Relógio", "Cronómetro"};
                switch (position){

                    case 1:
                        onCreateInputDialog("Ip:");
                    break;
                    case 2:
                        onCreateInputDialog("Porto");
                    break;
                    case 4:
                        Dialog dialogTemporizacao = onCreateDialogSingleChoice(temporizacao, "Método de temporização");
                        dialogTemporizacao.show();
                    break;
                }
            }
        });

    }

    public Dialog onCreateDialogSingleChoice(String[] array, String title ) {

//Initialize the Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//Source of the data in the DIalog
      // array = {"High", "Medium", "Low"};

// Set the dialog title
// Specify the list array, the items to be selected by default (null for none),
// and the listener through which to receive callbacks when items are selected
        builder.setTitle(title).setSingleChoiceItems(array, 1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                      TODO Auto-generated method stub

                    }
                })

// Set the action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
// User clicked OK, so save the result somewhere
// or return them to the component that opened the dialog

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    String m_Text = "";
    public String onCreateInputDialog(String title ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);

    // Set up the input
        final EditText input = new EditText(getActivity());
    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

    // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
        return m_Text;

    }
}