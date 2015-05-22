package estg.mee.piscoreboard.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import estg.mee.piscoreboard.R;

/**
 * Created by Pedro on 14/05/2015.
 */
public class DialogManager {
    String m_Text = "";

    public Dialog onCreateDialogSingleChoice(Context c, String[] array, String title ) {

//Initialize the Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
//Source of the data in the DIalog

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

    public String onCreateInputDialog(Context c, String title ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(title);

        // Set up the input
        final EditText input = new EditText(c);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
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

    public void onCreateInfoDialog(Context c, String title ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(title);

//        // Set up the input
//        final EditText input = new EditText(c);
//        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//        input.setInputType(InputType.TYPE_CLASS_TEXT );
//        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }
}
