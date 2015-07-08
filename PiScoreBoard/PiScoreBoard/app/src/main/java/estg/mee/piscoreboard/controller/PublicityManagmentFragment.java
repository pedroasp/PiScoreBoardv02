package estg.mee.piscoreboard.controller;

//import android.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
<<<<<<< HEAD
import android.support.v4.app.Fragment;
import android.text.Editable;
=======
        import android.text.Editable;
import android.text.InputType;
>>>>>>> origin/master
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
<<<<<<< HEAD
=======
import android.widget.TextView;
>>>>>>> origin/master
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
 * Created by Pedro on 13/05/2015.
 */
public class PublicityManagmentFragment extends Fragment implements Filterable{

    private View rootView = null;

    private static final int puBID = 3;
    EntryAdapter adapter;
    private ListView lv;


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
                ShowDetailsDialog(getActivity(), currentGame.getPublictyList().get(position).toString());
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
        this.refreshPubList();
    }

    private void refreshPubList(){

        for(Iterator<String> i = currentGame.getPublictyList().iterator(); i.hasNext(); ) {
            String item = i.next();
            items.add(new EntryItem(getImageName(item), null, null, item,0));
        }
        adapter = new EntryAdapter(getActivity(), items);
        lv.setAdapter(adapter);

    }

    private String getImageName(String imagePath){

        int index = imagePath.lastIndexOf('/');

        String name = imagePath.substring(index+1,imagePath.lastIndexOf("."));

        return name;
    }

    private String getImage(String imagePath){

        int index = imagePath.lastIndexOf('/');

        String name = imagePath.substring(index+1,imagePath.length());

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
        menu.findItem(R.id.action_addVideo).setVisible(false);
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
        Bitmap myBitmap, myScaledBitmap;
        final ImageView img = new ImageView(c);
        File imgFile = new File(pubPath);
        myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
<<<<<<< HEAD

        final float scale = c.getResources().getDisplayMetrics().density;
        int dpixeis = (int) (100 * scale + 0.5f);

=======

        final float scale = c.getResources().getDisplayMetrics().density;
        int dpixeis = (int) (100 * scale + 0.5f);

>>>>>>> origin/master
        myScaledBitmap = resize(myBitmap,dpixeis,dpixeis);
        img.setImageBitmap(myScaledBitmap);

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

    private static Bitmap resize(Bitmap image, int maxWidth, int maxHeight) {

        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > 1) {
                finalWidth = (int) ((float)maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float)maxWidth / ratioBitmap);
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
            return image;
        } else {
            return image;
        }
    }

<<<<<<< HEAD
    public void ShowDeleteDialog(final Context c, final int position) {
=======
    public void ShowDeleteDialog(Context c, final int position) {
>>>>>>> origin/master


        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setIcon(R.drawable.ic_delete_black_24dp);
        builder.setTitle("Remover publicidade");
        builder.setMessage("Tem a certeza que pretende remover " + getImageName(currentGame.getPublictyList().get(position).toString()) + " da lista de publicidades?");

        //Set up the buttons
        builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
<<<<<<< HEAD
                async_sftp.removePub(c,getImage(currentGame.getPublictyList().get(position).toString()));
=======
>>>>>>> origin/master
                currentGame.getPublictyList().remove(position);
                onResume();
                ((MainActivity) getActivity()).saveData();
            }
        });

        builder.show();

    }
}
