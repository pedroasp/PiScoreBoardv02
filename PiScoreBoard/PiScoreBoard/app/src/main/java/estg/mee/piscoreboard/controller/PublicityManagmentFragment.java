package estg.mee.piscoreboard.controller;

import android.app.AlertDialog;
import android.app.Dialog;
//import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
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

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.customlistview.EntryAdapter;
import estg.mee.piscoreboard.customlistview.EntryItem;
import estg.mee.piscoreboard.customlistview.EntryItemSwitch;
import estg.mee.piscoreboard.customlistview.Item;
import estg.mee.piscoreboard.customlistview.SectionItem;
import estg.mee.piscoreboard.utils.DialogManager;
import estg.mee.piscoreboard.utils.ListViewSwipeGesture;

/**
 * Created by Pedro on 13/05/2015.
 */
public class PublicityManagmentFragment extends Fragment {

    private View rootView = null;

    EntryAdapter adapter;
    // List view
    private ListView lv;

    // Search EditText
    EditText inputSearch;


    // ArrayList for Listview
    ArrayList<Item> items = new ArrayList<Item>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.edit, container, false);
        this.rootView = rootView;

        setHasOptionsMenu(true);

        items.add(new EntryItem("Café",null ,null, "/storage/sdcard0/DCIM/Camera/football_ball.png",0));
        items.add(new EntryItem("Padaria",null ,null, "/storage/sdcard0/DCIM/Camera/football_ball.png",0));


        lv = (ListView) this.rootView.findViewById(R.id.list_viewaa);
        inputSearch = (EditText) this.rootView.findViewById(R.id.inputSearch);


        adapter = new EntryAdapter(getActivity(), items);
        lv.setAdapter(adapter);

        final ListViewSwipeGesture touchListener = new ListViewSwipeGesture( lv, swipeListener, getActivity());
        touchListener.SwipeType	=	ListViewSwipeGesture.Double;    //Set two options at background of list item

        lv.setOnTouchListener(touchListener);

        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
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
        menu.findItem(R.id.action_add).setVisible(true);
    }

}
