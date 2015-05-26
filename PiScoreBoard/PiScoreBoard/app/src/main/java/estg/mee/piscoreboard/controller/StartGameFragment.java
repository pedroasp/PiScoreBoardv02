package estg.mee.piscoreboard.controller;

import android.app.AlertDialog;
import android.app.Dialog;
//import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.customlistview.EntryAdapter;
import estg.mee.piscoreboard.customlistview.EntryItem;
import estg.mee.piscoreboard.customlistview.EntryItemButton;
import estg.mee.piscoreboard.customlistview.Item;
import estg.mee.piscoreboard.customlistview.SectionItem;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by Pedro on 13/05/2015.
 */
public class StartGameFragment extends Fragment {


    private View rootView = null;

   private ArrayList<String> mSelectPath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings,container,false);

        this.rootView = rootView;

        this.initSettingsFields();

        return rootView;
    }


    private void initSettingsFields(){
        ListView settingsList = (ListView)this.rootView.findViewById(R.id.settings_lv);
        ArrayList<Item> items = new ArrayList<Item>();

        items.add(new SectionItem(getResources().getString(R.string.sectionModalidade)));
        items.add(new EntryItem(getResources().getString(R.string.itemModalidade), getResources().getString(R.string.summaryItemModalidade),"Futsal", null,0));

        items.add(new SectionItem(getResources().getString(R.string.sectionEquipas)));
        items.add(new EntryItem(getResources().getString(R.string.itemEquipaVisitada), getResources().getString(R.string.summaryitemEquipaVisitada),null,null,0));
        items.add(new EntryItem(getResources().getString(R.string.itemEquipaVisitante), getResources().getString(R.string.summaryitemEquipaVisitante),null, null,0));
        items.add(new EntryItem("File picker", null,null, null,0));

        //items.add(new SectionItem(getResources().getString(R.string.sectionDefinicoesAvancadas)));
        items.add(new EntryItemButton(10,getResources().getString(R.string.iniciarJogoButtonText)));

        EntryAdapter adapter = new EntryAdapter(getActivity(), items);
        settingsList.setAdapter(adapter);

        settingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Object listItem = settingsList.getItemAtPosition(position);
                String[] equipas = {"Benfica", "Sporting", "Porto"};
                String[] modalidades = {"Futsal", "Basquetebol"};
                switch (position){
                    case 1:
                        Dialog dialogModalidades = onCreateDialogSingleChoice(modalidades, "Escolha a modalidade:");
                        dialogModalidades.show();

                        break;
                    case 3:
                        Dialog dialogEquipaVisitada = onCreateDialogSingleChoice(equipas, "Escolha a equipa local:");
                        dialogEquipaVisitada.show();

                        break;
                    case 4:
                        Dialog dialogEquipaVisitante= onCreateDialogSingleChoice(equipas, "Escolha a equipa visitante:");
                        dialogEquipaVisitante.show();

                        break;
                    case 5: {

                        int selectedMode = MultiImageSelectorActivity.MODE_MULTI;

                        boolean showCamera = true;

                        int maxNum = 9;

                        Intent intent = new Intent(getActivity(), MultiImageSelectorActivity.class);

                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, showCamera);

                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);

                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);

                        if (mSelectPath != null && mSelectPath.size() > 0) {
                            intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mSelectPath);
                        }
                        startActivityForResult(intent, MainActivity.getRequestImage());

                    }break;
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
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }
}
