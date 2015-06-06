package estg.mee.piscoreboard.controller;

//import android.app.Fragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;

import java.util.ArrayList;

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.customlistview.EntryAdapter;
import estg.mee.piscoreboard.customlistview.EntryItem;
import estg.mee.piscoreboard.customlistview.EntryItemSwitch;
import estg.mee.piscoreboard.customlistview.EntryItemTwoButtons;
import estg.mee.piscoreboard.customlistview.Item;
import estg.mee.piscoreboard.customlistview.SectionItem;
import estg.mee.piscoreboard.model.PiScoreBoard;
import estg.mee.piscoreboard.utils.ColorPickerDialog;
import estg.mee.piscoreboard.utils.ColorPickerDialog.OnColorSelectedListener;


public class SettingsFragment extends Fragment {


    private View rootView = null;
    ColorPickerDialog colorPickerDialog;
    PiScoreBoard piScoreBoard = PiScoreBoard.getInstance();
    ArrayList<Item> items;
    ListView settingsList;
    EntryAdapter adapter;
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
        items = new ArrayList<Item>();

        items.add(new SectionItem(getResources().getString(R.string.sectionComunicacao)));

        items.add(new EntryItem(getResources().getString(R.string.itemIPAdress), getResources().getString(R.string.summaryitemIPAdress),piScoreBoard.getIpAdress(),null,0));
        items.add(new EntryItem(getResources().getString(R.string.itemPorto), getResources().getString(R.string.summaryItemPorto),String.valueOf(piScoreBoard.getPort()),null,0));
        items.add(new EntryItem(getResources().getString(R.string.itemPassword), getResources().getString(R.string.summaryItemPassword),piScoreBoard.getPassword(),null,0));

        items.add(new SectionItem(getResources().getString(R.string.sectionTemporizacao)));

        items.add(new EntryItem(getResources().getString(R.string.itemTemporizacao), getResources().getString(R.string.summaryItemTemporizacao),piScoreBoard.getStringTimeMode(), null,0));

        items.add(new SectionItem(getResources().getString(R.string.sectionPublicidade)));
        items.add(new EntryItemSwitch(1, getResources().getString(R.string.itemPublicidade),null , true));
        items.add(new EntryItem(getResources().getString(R.string.itemPeriodoPublicidade), getResources().getString(R.string.summaryItemPeriodoPublicidade),null,null,0));

        items.add(new SectionItem(getResources().getString(R.string.sectionControloRemoto)));
        items.add(new EntryItemTwoButtons(2,getResources().getString(R.string.labelDesligar),3, getResources().getString(R.string.labelReiniciar) ));

        items.add(new SectionItem(getResources().getString(R.string.sectionColors)));
        items.add(new EntryItem(getResources().getString(R.string.itemBackgroundCentralColor), getResources().getString(R.string.summaryItemBackgroundCentralColor),null,null,0));
        items.add(new EntryItem(getResources().getString(R.string.itemBackgroundSideColor), getResources().getString(R.string.summaryItemBackgroundSideColor),null,null,0));
        items.add(new EntryItem(getResources().getString(R.string.itemResultColor), getResources().getString(R.string.summaryItemResultColor),null,null,0));
        items.add(new EntryItem(getResources().getString(R.string.itemFaultColor), getResources().getString(R.string.summaryItemFaultColor),null,null,0));
        items.add(new EntryItem(getResources().getString(R.string.itemNameColor), getResources().getString(R.string.summaryItemNameColor),null,null,0));
        items.add(new EntryItem(getResources().getString(R.string.itemPartColor), getResources().getString(R.string.summaryItemPartColor),null,null,0));
        items.add(new EntryItem(getResources().getString(R.string.itemTimeColor), getResources().getString(R.string.summaryItemTimeColor),null,null,0));


        adapter = new EntryAdapter(getActivity(), items);
        settingsList.setAdapter(adapter);




        settingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // Object listItem = settingsList.getItemAtPosition(position);
                String[] temporizacao = {"Relógio", "Cronómetro"};
                switch (position){

                    case 1:
                        onCreateInputDialog(getResources().getString(R.string.itemIPAdress),position);

                        break;
                    case 2:
                        piScoreBoard.setPort(Integer.parseInt(onCreateInputDialog(getResources().getString(R.string.itemPorto),position))) ;

                        break;
                    case 3:
                        piScoreBoard.setPassword(onCreateInputDialog(getResources().getString(R.string.itemPassword),position));

                        break;
                    case 5:
                        Dialog dialogTemporizacao = onCreateDialogSingleChoice(temporizacao, "Método de temporização");
                        dialogTemporizacao.show();
                    break;
                    case 8:
                        OnCreateNumberPicker(getActivity(),"Temporização");
                        break;

                    case 12:
                        colorPickerDialog = new ColorPickerDialog(getActivity(), ((MainActivity)getActivity()).graphics.getBackgroundCentralColor(), new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                ((MainActivity)getActivity()).graphics.setBackgroundCentralColor(color);
                                ((MainActivity)getActivity()).graphics.sendColorCommand(((MainActivity)getActivity()).graphics.BackgroundCentralColor,true);
                            }
                        });
                        colorPickerDialog.show();
                        break;
                    case 13:
                        colorPickerDialog = new ColorPickerDialog(getActivity(), ((MainActivity)getActivity()).graphics.getBackgroundSideColor(), new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                ((MainActivity)getActivity()).graphics.setBackgroundSideColor(color);
                                ((MainActivity)getActivity()).graphics.sendColorCommand(((MainActivity)getActivity()).graphics.BackgroundSideColor,true);
                            }
                        });
                        colorPickerDialog.show();
                        break;
                    case 14:
                        colorPickerDialog = new ColorPickerDialog(getActivity(), ((MainActivity)getActivity()).graphics.getResultColor(), new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                ((MainActivity)getActivity()).graphics.setResultColor(color);
                                ((MainActivity)getActivity()).graphics.sendColorCommand(((MainActivity)getActivity()).graphics.ResultColor,true);
                            }
                        });
                        colorPickerDialog.show();
                        break;
                    case 15:
                        colorPickerDialog = new ColorPickerDialog(getActivity(), ((MainActivity)getActivity()).graphics.getFaultColor(), new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                ((MainActivity)getActivity()).graphics.setFaultColor(color);
                                ((MainActivity)getActivity()).graphics.sendColorCommand(((MainActivity)getActivity()).graphics.FaultColor,true);

                            }
                        });
                        colorPickerDialog.show();
                        break;
                    case 16:
                        colorPickerDialog = new ColorPickerDialog(getActivity(), ((MainActivity)getActivity()).graphics.getNamesColor(), new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                ((MainActivity)getActivity()).graphics.setNamesColor(color);
                                ((MainActivity)getActivity()).graphics.sendColorCommand(((MainActivity)getActivity()).graphics.NamesColor,true);
                            }
                        });
                        colorPickerDialog.show();
                        break;
                    case 17:
                        colorPickerDialog = new ColorPickerDialog(getActivity(), ((MainActivity)getActivity()).graphics.getPartColor(), new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                ((MainActivity)getActivity()).graphics.setPartColor(color);
                                ((MainActivity)getActivity()).graphics.sendColorCommand(((MainActivity)getActivity()).graphics.PartColor,true);
                            }
                        });
                        colorPickerDialog.show();
                        break;
                    case 18:
                        colorPickerDialog = new ColorPickerDialog(getActivity(), ((MainActivity)getActivity()).graphics.getTimeColor(), new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                ((MainActivity)getActivity()).graphics.setTimeColor(color);
                                ((MainActivity)getActivity()).graphics.sendColorCommand(((MainActivity)getActivity()).graphics.TimeColor,true);
                            }
                        });
                        colorPickerDialog.show();
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
        builder.setTitle(title).setSingleChoiceItems(array,piScoreBoard.isTimeMode()? 1 : 0, new DialogInterface.OnClickListener() {

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
                        ListView lw = ((AlertDialog)dialog).getListView();
                        int checkedItem = lw.getCheckedItemPosition();
                        String stringToSend;
                        switch (checkedItem){
                            case 0:
                                stringToSend = getActivity().getResources().getString(R.string.TimeMode).concat("@clock@");
                                ((MainActivity) getActivity()).sendCommand(stringToSend, true);
                                break;
                            case 1:
                                stringToSend = getActivity().getResources().getString(R.string.TimeMode).concat("@crono@");
                                ((MainActivity) getActivity()).sendCommand(stringToSend,true);
                                break;
                        }

                        piScoreBoard.setTimeMode(checkedItem != 0);
                        items.set(5,new EntryItem(getResources().getString(R.string.itemTemporizacao), getResources().getString(R.string.summaryItemTemporizacao),piScoreBoard.getStringTimeMode(), null,0));
                        ((MainActivity) getActivity()).saveData();
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    String m_Text;
    public String onCreateInputDialog(String title, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);

    // Set up the input
        final EditText input = new EditText(getActivity());
    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);
        switch (position){
            case 1:
                input.setText(piScoreBoard.getIpAdress());
                break;
            case 2:
                input.setText(String.valueOf(piScoreBoard.getPort()));
                break;
            case 3:
                input.setText(piScoreBoard.getPassword());
                break;
        }


    // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                m_Text = input.getText().toString();
                String stringToSend;
                switch (position){
                    case 1:
                        piScoreBoard.setIpAdress(input.getText().toString());
                        items.set(1,(new EntryItem(getResources().getString(R.string.itemIPAdress), getResources().getString(R.string.summaryitemIPAdress),piScoreBoard.getIpAdress(),null,0)));
                        //stringToSend = getActivity().getResources().getString(R.string.TimeMode).concat("@clock@");
                        //((MainActivity) getActivity()).sendCommand(stringToSend, true);
                        break;
                    case 2:
                        items.set(2,(new EntryItem(getResources().getString(R.string.itemPorto), getResources().getString(R.string.summaryItemPorto),String.valueOf(piScoreBoard.getPort()),null,0)));
                        break;
                    case 3:
                        items.set(3,(new EntryItem(getResources().getString(R.string.itemPassword), getResources().getString(R.string.summaryItemPassword),piScoreBoard.getPassword(),null,0)));

                        break;
                }
                ((MainActivity) getActivity()).saveData();
                adapter.notifyDataSetChanged();
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
    public void OnCreateNumberPicker(Context c, String title)
    {

        final Dialog d = new Dialog(c);
        d.setTitle(title);
        d.setContentView(R.layout.layout_numberpicker);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(100);
        np.setMinValue(0);
        np.setWrapSelectorWheel(false);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //tv.setText(String.valueOf(np.getValue()));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });

        d.show();

    }

}
