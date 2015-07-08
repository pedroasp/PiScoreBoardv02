package estg.mee.piscoreboard.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
<<<<<<< HEAD
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.RectF;
import android.opengl.Matrix;
=======
import android.graphics.RectF;
import android.opengl.Matrix;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.graphics.RectF;
import android.opengl.Matrix;

>>>>>>> origin/master
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
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.customlistview.EntryAdapter;
import estg.mee.piscoreboard.customlistview.EntryItem;
import estg.mee.piscoreboard.customlistview.Item;
import estg.mee.piscoreboard.model.Game;
import estg.mee.piscoreboard.model.PiScoreBoard;
import estg.mee.piscoreboard.model.Team;
import estg.mee.piscoreboard.utils.Async_SFTP;
import estg.mee.piscoreboard.utils.ListViewSwipeGesture;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * @author Pedro Pires
 * @version 1.0 22/05/2015
 * Esta class
 */
public class TeamsManagmentFragment extends Fragment implements Filterable{

    private View rootView = null;
    String stringToSend;
    Game currentGame = Game.getInstance();
    EntryAdapter adapter;
    // List view
    private ListView lv;

    // Search EditText
    EditText inputSearch;

    private static final int teamsFragmentID = 4;
    private static final int addTeamID = 5;

    int selectedMode = MultiImageSelectorActivity.MODE_SINGLE;

    boolean showCamera = true;

    int maxNum = 9;

    PiScoreBoard piScoreBoard = PiScoreBoard.getInstance();

    // ArrayList for Listview
    ArrayList<Item> items = new ArrayList<Item>();

    ArrayList<String> TeamsName = new ArrayList<String>();
    ArrayList<String> TeamsLogo= new ArrayList<String>();
    ArrayList<String> TeamsID= new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.edit, container, false);
        this.rootView = rootView;

        setHasOptionsMenu(true);

        lv = (ListView) this.rootView.findViewById(R.id.list_viewaa);
        inputSearch = (EditText) this.rootView.findViewById(R.id.inputSearch);


        adapter = new EntryAdapter(getActivity(), items);
        lv.setAdapter(adapter);

        final ListViewSwipeGesture touchListener = new ListViewSwipeGesture( lv, swipeListener, getActivity());
        touchListener.SwipeType	=	ListViewSwipeGesture.Double;    //Set two options at background of list item

       // lv.setOnTouchListener(touchListener);
        lv.setClickable(false);

    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ShowDetailsDialog(getActivity(), piScoreBoard.getListOfTeams().get(position));
        }
    });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {


                PopupMenu popup = new PopupMenu(getActivity(), view);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());


                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.edit_option:
                                Object listItem = lv.getItemAtPosition(position);

                                //EditTeamDialog(getActivity(), (Team)listItem);
                                EditTeamDialog(getActivity(), piScoreBoard.getListOfTeams().get(position));
                                break;
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

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        items.clear();

        for(Iterator<Team> i = piScoreBoard.getListOfTeams().iterator(); i.hasNext(); ) {
            Team item = i.next();
            items.add(new EntryItem(item.getName(), null, null, item.getLogotipo(),0));
        }
        adapter = new EntryAdapter(getActivity(), items);
        lv.setAdapter(adapter);
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

        }

    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit, menu);
        menu.findItem(R.id.action_addTeams).setVisible(true);
        menu.findItem(R.id.action_addPub).setVisible(false);
        menu.findItem(R.id.action_addVideo).setVisible(false);
    }

    public Filter getFilter(){
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                constraint = constraint.toString().toLowerCase();
                FilterResults result = new FilterResults();

                if (constraint != null && constraint.toString().length() > 0) {
                    ArrayList<Team> founded = new ArrayList<Team>();

                    for(Iterator<Team> i = piScoreBoard.getListOfTeams().iterator(); i.hasNext(); ) {
                        Team item = i.next();
                        if(item.getName().toLowerCase().contains(constraint)){
                            founded.add(item);
                        }
                    }
                    result.values = founded;
                    result.count = founded.size();
                }else {
                    result.values = piScoreBoard.getListOfTeams();
                    result.count = piScoreBoard.getListOfTeams().size();
                }
                return result;


            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                items.clear();

                for (Team item : (ArrayList<Team>) results.values) {
                    items.add(new EntryItem(item.getName(), null, null, item.getLogotipo(),0));
                }
                adapter = new EntryAdapter(getActivity(), items);
                lv.setAdapter(adapter);

            }

        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_addTeams:{

                  AddTeamDialog(getActivity());

            } break;
        }

        return super.onOptionsItemSelected(item);
    }

    private static Bitmap resize(Bitmap image, int maxWidth, int maxHeight) {

        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;
<<<<<<< HEAD

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

    public void EditTeamDialog(final Context c, final Team team) {
=======

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

    public void EditTeamDialog(Context c, final Team team) {
>>>>>>> origin/master
        Bitmap myScaledBitmap,myBitmap;
        final ImageView img = new ImageView(c);
        //File imgFile = new File("/storage/sdcard0/DCIM/Camera/football_ball.png");
        File imgFile = new File(team.getLogotipo());
        myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
<<<<<<< HEAD

        final float scale = c.getResources().getDisplayMetrics().density;
        int dpixeis = (int) (70 * scale + 0.5f);
        myScaledBitmap = resize(myBitmap,dpixeis,dpixeis);

=======

        final float scale = c.getResources().getDisplayMetrics().density;
        int dpixeis = (int) (50 * scale + 0.5f);
        myScaledBitmap = resize(myBitmap,dpixeis,dpixeis);
>>>>>>> origin/master

        //img.setImageBitmap(Bitmap.createScaledBitmap(myBitmap, 100, 100, false));
        img.setImageBitmap(myScaledBitmap);

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Editar equipa");

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        final TextView editImage= new TextView(c);
        editImage.setText("Editar imagem:");
        final TextView editNome = new TextView(c);
        editNome.setText("Editar nome:");

        final EditText input = new EditText(c);

        input.setInputType(InputType.TYPE_CLASS_TEXT );
        input.setHint(team.getName());

        LinearLayout ll=new LinearLayout(c);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(editImage);
        ll.addView(img);
        ll.addView(editNome);
        ll.addView(input);
        builder.setView(ll);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MultiImageSelectorActivity.class);

                intent.putExtra(MultiImageSelectorActivity.EXTRA_FRAGMENT_ID, addTeamID);

                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, showCamera);

                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);

                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);

                getActivity().startActivityForResult(intent, MainActivity.getRequestImage());


            }
        });

        //Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!input.getText().toString().isEmpty()) {
                    team.setName(input.getText().toString());
<<<<<<< HEAD

                 }

                if( MainActivity.newTeamPath!=null) {
                    team.setLogotipo(MainActivity.newTeamPath);
                }

=======
                    }

                if( MainActivity.newTeamPath!=null) {
                    team.setLogotipo(MainActivity.newTeamPath);
                }

>>>>>>> origin/master
                if(!input.getText().toString().isEmpty() || MainActivity.newTeamPath!=null){
                    onResume();
                    ((MainActivity) getActivity()).saveData();
                    onResume();
                }
                MainActivity.newTeamPath = null;
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();

    }
int actualID = 0;
    public void AddTeamDialog(Context c ) {

        if(piScoreBoard.getListOfTeams().size()!=0)
            actualID = piScoreBoard.getListOfTeams().get((piScoreBoard.getListOfTeams().size())-1).getId();
        else
            actualID = 0;

        Team novaEquipa = new Team();
        final ImageView img = new ImageView(c);
        if(MainActivity.newTeamPath == null) {
            img.setImageResource(R.drawable.question_icon);
        }else{
            File imgFile = new File(MainActivity.newTeamPath);
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Drawable drawable = new BitmapDrawable(getResources(), myBitmap);
            //img.setImageBitmap(myBitmap);
            img.setImageDrawable(drawable);
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Adicionar equipa");

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MultiImageSelectorActivity.class);

                intent.putExtra(MultiImageSelectorActivity.EXTRA_FRAGMENT_ID, addTeamID);

                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, showCamera);

                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);

                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);

                getActivity().startActivityForResult(intent, MainActivity.getRequestImage());


            }
        });

        final TextView editImage= new TextView(c);
        editImage.setText("Definir imagem:");
        final TextView editNome = new TextView(c);
        editNome.setText("Definir nome:");

        final EditText input = new EditText(c);

        input.setInputType(InputType.TYPE_CLASS_TEXT);

        LinearLayout ll=new LinearLayout(c);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(editImage);
        ll.addView(img);
        img.requestLayout();
        final float scale = c.getResources().getDisplayMetrics().density;
        int pixels = (int) (40 * scale + 0.5f);
        img.getLayoutParams().height = pixels;
        img.getLayoutParams().width = pixels;
        ll.addView(editNome);
        ll.addView(input);
        builder.setView(ll);


        //Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Team newTeam = new Team();
                newTeam.setName(input.getText().toString());
                newTeam.setLogotipo(MainActivity.newTeamPath);
                newTeam.setId(++actualID);
                newTeam.setModality(piScoreBoard.getListOfModalities().get(0));
                piScoreBoard.getListOfTeams().add(newTeam);
<<<<<<< HEAD
                final ArrayList<String> arrayList = new ArrayList<>();
                final Async_SFTP async_sftp = new Async_SFTP();
                arrayList.add(newTeam.getLogotipo());
                async_sftp.uploadLogos(getActivity(),arrayList);
                ((MainActivity) getActivity()).saveData();
                //MainActivity.newTeamPath = null;
=======


                final ArrayList<String> arrayList = new ArrayList<>();
                final Async_SFTP async_sftp = new Async_SFTP();
                arrayList.add(newTeam.getLogotipo());

                async_sftp.uploadLogos(getActivity(), arrayList);

                ((MainActivity) getActivity()).saveData();

>>>>>>> origin/master
                MainActivity.newTeamPath = null;
                onResume();


            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();

    }
    public void ShowDetailsDialog(Context c , Team team) {
        Bitmap myBitmap, myScaledBitmap;
        final ImageView img = new ImageView(c);
        File imgFile = new File(team.getLogotipo());
        myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        final float scale = c.getResources().getDisplayMetrics().density;
        int dpixeis = (int) (100 * scale + 0.5f);

        myScaledBitmap = resize(myBitmap,dpixeis,dpixeis);

        img.setImageBitmap(myScaledBitmap);

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(team.getName());

        builder.setView(img);

        //Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();

    }

    public void ShowDeleteDialog(Context c, final int position) {


        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setIcon(R.drawable.ic_delete_black_24dp);
        builder.setTitle("Remover equipa");
<<<<<<< HEAD
        builder.setMessage("Tem a certeza que pretende remover " + piScoreBoard.getListOfTeams().get(position).getName() + "da lista de equipas?");
=======
        builder.setMessage("Tem a certeza que pretende remover " + piScoreBoard.getListOfTeams().get(position).getName() + " da lista de equipas?");
>>>>>>> origin/master

        //Set up the buttons
        builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                piScoreBoard.getListOfTeams().remove(position);
                onResume();
                ((MainActivity) getActivity()).saveData();
            }
        });

        builder.show();

    }
<<<<<<< HEAD
=======

>>>>>>> origin/master
}
