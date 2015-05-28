package estg.mee.piscoreboard.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.customlistview.EntryAdapter;
import estg.mee.piscoreboard.customlistview.EntryItem;
import estg.mee.piscoreboard.customlistview.Item;
import estg.mee.piscoreboard.utils.ListViewSwipeGesture;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by Pedro on 22/05/2015.
 */
public class TeamsManagmentFragment extends Fragment{

    private View rootView = null;

    private static final int teamsID = 4;

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

        items.add(new EntryItem("FC Porto",null ,null, null,0));
        items.add(new EntryItem("Leos de Porto Salvo",null ,null, null,0));
        items.add(new EntryItem("Benfica",null ,null, null,0));

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
            // TODO Auto-generated method stub
            //  startActivity(new Intent(getApplicationContext(),TestActivity.class));
            onCreateInfoDialog(getActivity(), "Benfica");
        }

    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit, menu);
        menu.findItem(R.id.action_addTeams).setVisible(true);
        menu.findItem(R.id.action_addPub).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_addTeams:{

                int selectedMode = MultiImageSelectorActivity.MODE_SINGLE;

                boolean showCamera = true;

                int maxNum = 9;

                Intent intent = new Intent(getActivity(), MultiImageSelectorActivity.class);

                intent.putExtra(MultiImageSelectorActivity.EXTRA_FRAGMENT_ID, teamsID);

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

    public void onCreateInfoDialog(Context c, String title ) {

        final ImageView img = new ImageView(c);
        File imgFile = new File("/storage/sdcard0/DCIM/Camera/football_ball.png");
        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        img.setImageBitmap(myBitmap);

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(title);

      //  builder.setMessage("Editar imagem:");
        //builder.setView(img);


        final TextView editImage= new TextView(c);
        editImage.setText("Editar imagem:");
        final TextView editNome = new TextView(c);
        editNome.setText("Editar nome:");
        editNome.setHint("Benfica");

        // Set up the input
        final EditText input = new EditText(c);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
//        builder.setView(input);

        LinearLayout ll=new LinearLayout(c);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(editImage);
        ll.addView(img);
        ll.addView(editNome);
        ll.addView(input);
        builder.setView(ll);

        //Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();

    }
}
