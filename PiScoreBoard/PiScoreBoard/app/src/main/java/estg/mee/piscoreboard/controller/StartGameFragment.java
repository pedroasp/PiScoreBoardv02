package estg.mee.piscoreboard.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.customlistview.EntryAdapter;
import estg.mee.piscoreboard.customlistview.EntryItem;
import estg.mee.piscoreboard.customlistview.EntryItemButton;
import estg.mee.piscoreboard.customlistview.Item;
import estg.mee.piscoreboard.customlistview.SectionItem;
import estg.mee.piscoreboard.model.Game;
import estg.mee.piscoreboard.model.PiScoreBoard;
import estg.mee.piscoreboard.utils.Async_SFTP;

//import android.app.Fragment;

/**
 * Created by Rúben on 13/05/2015.
 */
public class StartGameFragment extends Fragment{


    private View rootView = null;
    PiScoreBoard piScoreBoard = PiScoreBoard.getInstance();

    Game currentGame = Game.getInstance();
    ArrayList<Item> items;
    EntryAdapter adapter;
//    public StartGameFragment(Game jogo) {
//       this.jogo = jogo;
//   }

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
        items = new ArrayList<Item>();

        items.add(new SectionItem(getResources().getString(R.string.sectionModalidade)));

        items.add(new EntryItem(getResources().getString(R.string.itemModalidade), getResources().getString(R.string.summaryItemModalidade),currentGame.getModality().getName(), null,currentGame.getModality().getImageRid()));

        items.add(new SectionItem(getResources().getString(R.string.sectionEquipas)));
        items.add(new EntryItem(getResources().getString(R.string.itemEquipaVisitada), getResources().getString(R.string.summaryitemEquipaVisitada),currentGame.getEquipaLocal().getName(),currentGame.getEquipaLocal().getLogotipo(),0));
        items.add(new EntryItem(getResources().getString(R.string.itemEquipaVisitante), getResources().getString(R.string.summaryitemEquipaVisitante),currentGame.getEquipaVisitante().getName(), currentGame.getEquipaVisitante().getLogotipo(),0));
        items.add(new EntryItem("File picker", null,null, null,0));

        //items.add(new SectionItem(getResources().getString(R.string.sectionDefinicoesAvancadas)));
        items.add(new EntryItemButton(1,getResources().getString(R.string.iniciarJogoButtonText)));

        adapter = new EntryAdapter(getActivity(), items);
        settingsList.setAdapter(adapter);

        settingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 1:
                        Dialog dialogModalidades = onCreateDialogSingleChoice(position,piScoreBoard.getModalitiesName(piScoreBoard.getListOfModalities()),currentGame.getModality().getName(), "Escolha a modalidade:");
                        dialogModalidades.show();

                        break;
                    case 3:
                        Dialog dialogEquipaVisitada = onCreateDialogSingleChoice(position,piScoreBoard.getTeamsName(piScoreBoard.getListOfTeams()),currentGame.getEquipaLocal().getName(), "Escolha a equipa local:");
                        dialogEquipaVisitada.show();

                        break;
                    case 4:
                        Dialog dialogEquipaVisitante= onCreateDialogSingleChoice(position,piScoreBoard.getTeamsName(piScoreBoard.getListOfTeams()),currentGame.getEquipaVisitante().getName(), "Escolha a equipa visitante:");
                        dialogEquipaVisitante.show();

                        break;
                    case 5: {
                        new Async_SFTP().uploadPubs(getActivity(), currentGame.getPublictyList());

                        //new Async_SFTP().listVideos(getActivity());
                    }break;

                }
            }
        });

    }


    public Dialog onCreateDialogSingleChoice(final int position, final String[] array, String string, String title ) {


//Initialize the Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//Source of the data in the DIalog
        // array = {"High", "Medium", "Low"};

// Set the dialog title
// Specify the list array, the items to be selected by default (null for none),
// and the listener through which to receive callbacks when items are selected

        builder.setTitle(title).setSingleChoiceItems(array, piScoreBoard.getMatchStringArray(string, array), new DialogInterface.OnClickListener() {

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

                        ListView lw = ((AlertDialog) dialog).getListView();
                        int checkedItem = lw.getCheckedItemPosition();
                        String stringToSend;
                        if (array.length != 0) {
                            switch (position) {
                                case 1:
                                    //stringToSend = getActivity().getResources().getString(R.string.TimeMode).concat("@clock@");
                                    //((MainActivity) getActivity()).sendCommand(stringToSend, true);
                                    currentGame.setModality(piScoreBoard.getListOfModalities().get(checkedItem));
                                    items.set(position, new EntryItem(getResources().getString(R.string.itemModalidade), getResources().getString(R.string.summaryItemModalidade), currentGame.getModality().getName(), null,currentGame.getModality().getImageRid()));

                                    break;
                                case 3:
                                    currentGame.setEquipaLocal(piScoreBoard.getListOfTeams().get(checkedItem));
                                    items.set(position,new EntryItem(getResources().getString(R.string.itemEquipaVisitada), getResources().getString(R.string.summaryitemEquipaVisitada), currentGame.getEquipaLocal().getName(), currentGame.getEquipaLocal().getLogotipo(),0));
                                    //stringToSend = getActivity().getResources().getString(R.string.TimeMode).concat("@crono@");
                                    //((MainActivity) getActivity()).sendCommand(stringToSend,true);

                                    break;
                                case 4:
                                    currentGame.setEquipaVisitante(piScoreBoard.getListOfTeams().get(checkedItem));
                                    items.set(position,new EntryItem(getResources().getString(R.string.itemEquipaVisitante), getResources().getString(R.string.summaryitemEquipaVisitante), currentGame.getEquipaVisitante().getName(), currentGame.getEquipaVisitante().getLogotipo(),0));
                                    break;
                            }
                            adapter.notifyDataSetChanged();
                            ((MainActivity) getActivity()).saveData();
                        }

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
