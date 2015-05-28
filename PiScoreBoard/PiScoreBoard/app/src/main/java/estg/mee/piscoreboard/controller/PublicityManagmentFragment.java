package estg.mee.piscoreboard.controller;

import android.app.AlertDialog;
import android.app.Dialog;
//import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.customlistview.EntryAdapter;
import estg.mee.piscoreboard.customlistview.EntryItem;
import estg.mee.piscoreboard.customlistview.EntryItemSwitch;
import estg.mee.piscoreboard.customlistview.Item;
import estg.mee.piscoreboard.customlistview.SectionItem;
import estg.mee.piscoreboard.model.Game;
import estg.mee.piscoreboard.utils.DialogManager;
import estg.mee.piscoreboard.utils.ListViewSwipeGesture;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by Pedro on 13/05/2015.
 */
public class PublicityManagmentFragment extends Fragment {

    private View rootView = null;

    private static final int puBID = 3;
    EntryAdapter adapter;
    // List view
    private ListView lv;

    private Game jogo;

    // Search EditText
    EditText inputSearch;

    //ArrayList<String> pubs = new ArrayList<String>();
    //String pubs;
    // ArrayList for Listview
    ArrayList<Item> items = new ArrayList<Item>();

    public PublicityManagmentFragment(Game jogo) {
        this.jogo = jogo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.edit, container, false);
        this.rootView = rootView;

        setHasOptionsMenu(true);


//        items.add(new EntryItem("Café",null ,null, "/storage/sdcard0/DCIM/Camera/football_ball.png",0));
//        items.add(new EntryItem("Padaria",null ,null, "/storage/sdcard1/DCIM/Camera/IMG_20150526_155949.jpg",0));


        lv = (ListView) this.rootView.findViewById(R.id.list_viewaa);
        lv.setTextFilterEnabled(true);
        inputSearch = (EditText) this.rootView.findViewById(R.id.inputSearch);

        final ListViewSwipeGesture touchListener = new ListViewSwipeGesture( lv, swipeListener, getActivity());
        touchListener.SwipeType	=	ListViewSwipeGesture.Double;    //Set two options at background of list item

        lv.setOnTouchListener(touchListener);

        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {
            ArrayList<String> searchArray = new ArrayList<String>();
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                //adapter.getFilter().filter(cs);
                // adapter.getFilter().convertResultToString(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        //this.initSettingsFields();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.refreshPubList();
    }

    private void refreshPubList(){

        for(Iterator<String> i = jogo.getPublictyList().iterator(); i.hasNext(); ) {
            String item = i.next();
            items.add(new EntryItem(getImageName(item), null, null, item, 0));
        }
        adapter = new EntryAdapter(getActivity(), items);
        lv.setAdapter(adapter);
    }

    private String getImageName(String imagePath){

        int index = imagePath.lastIndexOf('/');

        String name = imagePath.substring(index+1,imagePath.lastIndexOf("."));

        return name;
    }

    ListViewSwipeGesture.TouchCallbacks swipeListener = new ListViewSwipeGesture.TouchCallbacks() {

        @Override
        public void FullSwipeListView(int position) {
            // TODO Auto-generated method stub
            Toast.makeText(getActivity(), "Action_2", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void HalfSwipeListView(int position) {
            // TODO Auto-generated method stub
            Toast.makeText(getActivity(), "Action_1", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void LoadDataForScroll(int count) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onDismiss(ListView listView, int[] reverseSortedPositions) {
            // TODO Auto-generated method stub
            Toast.makeText(getActivity(), "Delete", Toast.LENGTH_SHORT).show();
//            for(int i:reverseSortedPositions){
//                listdata.remove(i);
//                listAdapter.notifyDataSetChanged();
//            }
        }

        @Override
        public void OnClickListView(int position) {
            // TODO Auto-generated method stub.
            //  startActivity(new Intent(getApplicationContext(),TestActivity.class));

        }

    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit, menu);
        menu.findItem(R.id.action_addPub).setVisible(true);
        menu.findItem(R.id.action_addTeams).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_addPub:{

                int selectedMode = MultiImageSelectorActivity.MODE_MULTI;

                boolean showCamera = true;

                int maxNum = 9;

                Intent intent = new Intent(getActivity(), MultiImageSelectorActivity.class);

                intent.putExtra(MultiImageSelectorActivity.EXTRA_FRAGMENT_ID, puBID);

                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, showCamera);

                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);

                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);

                if (MainActivity.getmSelectPath() != null && MainActivity.getmSelectPath().size() > 0) {
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, MainActivity.getmSelectPath());
                }
                getActivity().startActivityForResult(intent, MainActivity.getRequestImage());

            } break;
        }
        return super.onOptionsItemSelected(item);
    }
}
