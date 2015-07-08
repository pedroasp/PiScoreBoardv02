package estg.mee.piscoreboard.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.customlistview.EntryAdapter;
import estg.mee.piscoreboard.customlistview.EntryItem;
import estg.mee.piscoreboard.customlistview.Item;
import estg.mee.piscoreboard.model.Game;
import estg.mee.piscoreboard.utils.Async_SFTP;
import estg.mee.piscoreboard.utils.ListViewSwipeGesture;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by Pedro Pires on 07/07/2015.
 */
public class VideosManagmentFragment extends Fragment implements Filterable {

        private View rootView = null;

        private static final int puBID = 3;
        EntryAdapter adapter;
        private ListView lv;

        String videoState = "videoStoped";
        EditText inputSearch;

        // ArrayList for Listview
        ArrayList<Item> items = new ArrayList<Item>();

        Game currentGame = Game.getInstance();

    Async_SFTP async_sftp = Async_SFTP.getInstance();

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.edit, container, false);
            this.rootView = rootView;

            setHasOptionsMenu(true);

            lv = (ListView) this.rootView.findViewById(R.id.list_viewaa);
            lv.setTextFilterEnabled(true);
            inputSearch = (EditText) this.rootView.findViewById(R.id.inputSearch);

            final ListViewSwipeGesture touchListener = new ListViewSwipeGesture( lv, swipeListener, getActivity());
            touchListener.SwipeType	=	ListViewSwipeGesture.Double;    //Set two options at background of list item

            lv.setOnTouchListener(touchListener);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String name , path;
                    switch (videoState){

                        case "videoStoped":
                           int index = currentGame.getVideosList().get(position).lastIndexOf('/');

                            name = currentGame.getVideosList().get(position).substring(index + 1, currentGame.getVideosList().get(position).length());
                            path = "singlevideo@start:"+name+"@";
                            ((MainActivity) getActivity()).sendCommand(path.toLowerCase(), true);
                            videoState = "videoPlaying";
                            break;
                        case "videoPlaying":

                            path = "singlevideo@stop:dummy@";
                            ((MainActivity) getActivity()).sendCommand(path.toLowerCase(), true);
                            videoState = "videoStoped";
                            break;
                    }


                }
            });

            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {


                    PopupMenu popup = new PopupMenu(getActivity(), view);
                    popup.getMenuInflater().inflate(R.menu.popup_menu_delete, popup.getMenu());


                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()) {
                                case R.id.delete_option:

                                    ShowDeleteDialog(getActivity(), position);
                                    break;
                            }
                            return true;
                        }
                    });

                    popup.show();//showing popup menu
                    return true;
                }
            });

            /**
             * Enabling Search Filter
             * */
            inputSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    // When user changed the Text
                    getFilter().filter(cs);
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

            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
            items.clear();
            this.refreshVideosList();
        }

        private void refreshVideosList(){

            for(Iterator<String> i = currentGame.getVideosList().iterator(); i.hasNext(); ) {
                String item = i.next();
                items.add(new EntryItem(getImageName(item), null, null, null,0));
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
                //ShowDetailsDialog(getActivity(), currentGame.getVideosList().get(position).toString());

            }

        };

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            //super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.edit, menu);
            menu.findItem(R.id.action_addVideo).setVisible(true);
            menu.findItem(R.id.action_addTeams).setVisible(false);
            menu.findItem(R.id.action_addPub).setVisible(false);
        }

        public Filter getFilter(){
            return new Filter(){
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    constraint = constraint.toString().toLowerCase();
                    FilterResults result = new FilterResults();

                    if (constraint != null && constraint.toString().length() > 0) {
                        ArrayList<String> founded = new ArrayList<String>();

                        for(Iterator<String> i = currentGame.getVideosList().iterator(); i.hasNext(); ) {
                            String item = i.next();
                            if(getImageName(item.toString()).toLowerCase().contains(constraint)){
                                founded.add(item);
                            }
                        }

                        result.values = founded;
                        result.count = founded.size();
                    }else {
                        result.values = currentGame.getVideosList();
                        result.count = currentGame.getVideosList().size();
                    }
                    return result;


                }
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    items.clear();

                    for (String item : (ArrayList<String>) results.values) {
                        items.add(new EntryItem(getImageName(item), null, null, item,0));
                    }
                    adapter = new EntryAdapter(getActivity(), items);
                    lv.setAdapter(adapter);

                }

            };
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()){

                case R.id.action_addVideo:{

                    Intent i = new Intent();
                    i.setType("video/*"); //For choosing both images and/or videos
                    i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); //This should allow multiple selection
                    i.setAction(Intent.ACTION_GET_CONTENT);
                    getActivity().startActivityForResult(Intent.createChooser(i, "Select Video"), 1);

                } break;
            }
            return super.onOptionsItemSelected(item);
        }

        public void ShowDeleteDialog(Context c, final int position) {


            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setIcon(R.drawable.ic_delete_black_24dp);
            builder.setTitle("Remover video");
            builder.setMessage("Tem a certeza que pretende remover " + getImageName(currentGame.getVideosList().get(position).toString()) + " da lista de videos?");

            //Set up the buttons
            builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    currentGame.getVideosList().remove(position);

                    onResume();
                    ((MainActivity) getActivity()).saveData();

                    int index = currentGame.getVideosList().get(position).lastIndexOf('/');
                    String name = currentGame.getVideosList().get(position).substring(index + 1, currentGame.getVideosList().get(position).length()-1);

                    async_sftp.removeVideo(getActivity(),name);
                }
            });

            builder.show();

        }
    }

