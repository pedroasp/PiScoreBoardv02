package estg.mee.piscoreboard.controller;

//import android.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
        import android.os.Bundle;
import android.support.annotation.Nullable;
        import android.text.Editable;
        import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
        import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.customlistview.EntryAdapter;
import estg.mee.piscoreboard.customlistview.EntryItem;
        import estg.mee.piscoreboard.customlistview.Item;
        import estg.mee.piscoreboard.model.Game;
import estg.mee.piscoreboard.model.Team;
import estg.mee.piscoreboard.utils.ListViewSwipeGesture;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by Pedro on 13/05/2015.
 */
public class PublicityManagmentFragment extends Fragment implements Filterable{

    private View rootView = null;

    private static final int puBID = 3;
    EntryAdapter adapter;
    // List view
    private ListView lv;

  //  private Game jogo;

    // Search EditText
    EditText inputSearch;

    // ArrayList for Listview
    ArrayList<Item> items = new ArrayList<Item>();

    Game currentGame = Game.getInstance();

//    public PublicityManagmentFragment(Game jogo) {
//        this.jogo = jogo;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.edit, container, false);
        this.rootView = rootView;

        setHasOptionsMenu(true);

        lv = (ListView) this.rootView.findViewById(R.id.list_viewaa);
        lv.setTextFilterEnabled(true);
     //   inputSearch = (EditText) this.rootView.findViewById(R.id.inputSearch);

        final ListViewSwipeGesture touchListener = new ListViewSwipeGesture( lv, swipeListener, getActivity());
        touchListener.SwipeType	=	ListViewSwipeGesture.Double;    //Set two options at background of list item

        lv.setOnTouchListener(touchListener);

//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ShowDetailsDialog(getActivity(), currentGame.getPublictyList().get(position).toString());
//            }
//        });

//        /**
//         * Enabling Search Filter
//         * */
//        inputSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
//                // When user changed the Text
//                getFilter().filter(cs);
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
//                                          int arg3) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                // TODO Auto-generated method stub
//
//            }
//        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        items.clear();
        this.refreshPubList();
    }

    private void refreshPubList(){

        for(Iterator<String> i = currentGame.getPublictyList().iterator(); i.hasNext(); ) {
            String item = i.next();
            items.add(new EntryItem(getImageName(item), null, null, item, 0));
        }
        adapter = new EntryAdapter(getActivity(), items);
        lv.setAdapter(adapter);

//        Set<String> set = new HashSet<String>();
//        set.addAll(jogo.getPublictyList());
//        MainActivity.editor.putStringSet("Publist", set);
//        MainActivity.editor.commit();
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
            ShowDetailsDialog(getActivity(), currentGame.getPublictyList().get(position).toString());

        }

    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit, menu);
        menu.findItem(R.id.action_addPub).setVisible(true);
        menu.findItem(R.id.action_addTeams).setVisible(false);
    }

    public Filter getFilter(){
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                constraint = constraint.toString().toLowerCase();
                FilterResults result = new FilterResults();

                if (constraint != null && constraint.toString().length() > 0) {
                    ArrayList<String> founded = new ArrayList<String>();

                    for(Iterator<String> i = currentGame.getPublictyList().iterator(); i.hasNext(); ) {
                        String item = i.next();
                        if(getImageName(item.toString()).toLowerCase().contains(constraint)){
                            founded.add(item);
                        }
                    }

                    result.values = founded;
                    result.count = founded.size();
                }else {
                    result.values = currentGame.getPublictyList();
                    result.count = currentGame.getPublictyList().size();
                }
                return result;


            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                items.clear();

                for (String item : (ArrayList<String>) results.values) {
                    items.add(new EntryItem(getImageName(item), null, null, item, 0));
                }
                adapter = new EntryAdapter(getActivity(), items);
                lv.setAdapter(adapter);

            }

        };
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

                if (currentGame.getPublictyList() != null && currentGame.getPublictyList().size() > 0) {
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, currentGame.getPublictyList());
                }
                getActivity().startActivityForResult(intent, MainActivity.getRequestImage());

            } break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ShowDetailsDialog(Context c , String pubPath) {

        final ImageView img = new ImageView(c);
        File imgFile = new File(pubPath);
        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        img.setImageBitmap(myBitmap);

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(getImageName(pubPath));

        builder.setView(img);

        //Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();

    }
}
