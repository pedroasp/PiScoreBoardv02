package estg.mee.piscoreboard.controller;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.model.PiScoreBoard;

/**
 * Created by Rúben on 01-07-2015.
 */

public class MultimediaDialogFragment extends DialogFragment {
    Button mButton;
    String message;
    PiScoreBoard piScoreBoard = PiScoreBoard.getInstance();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        inflater.inflate(R.layout.dialog_multimedia, null);
        builder.setView(inflater.inflate(R.layout.dialog_multimedia, null))
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();*/

        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.dialog_multimedia);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        mButton = (Button) dialog.findViewById(R.id.button1);
        ImageView videoPrevious = (ImageView) dialog.findViewById(R.id.videoPrevious);
        ImageView videoNext = (ImageView) dialog.findViewById(R.id.videoNext);
        ImageView videoPause = (ImageView) dialog.findViewById(R.id.videoPause);
        ImageView videoPlay = (ImageView) dialog.findViewById(R.id.videoPlay);
        ImageView videoStop = (ImageView) dialog.findViewById(R.id.videoStop);
        ImageView publicityPlay = (ImageView) dialog.findViewById(R.id.publicityPlay);
        ImageView publicityStop = (ImageView) dialog.findViewById(R.id.publicityStop);



        //mEditText = (EditText) dialog.findViewById(R.id.editText1);
        //mEditText.setText(text);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // mListener.setOnSubmitListener(mEditText.getText().toString());
                dismiss();
            }
        });

        videoPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        videoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = getString(R.string.Video).concat("@"+ "next" +"@");
                ((MainActivity) getActivity()).sendCommand(message, true);
            }
        });
        videoPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = getString(R.string.Video).concat("@"+ "pause" +"@");
                ((MainActivity) getActivity()).sendCommand(message, true);
            }
        });
        videoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!piScoreBoard.isVideoState()) {
                    message = getString(R.string.Video).concat("@" + "start" + "@");
                    ((MainActivity) getActivity()).sendCommand(message, true);
                    piScoreBoard.setVideoState(true);
                }else{
                    message = getString(R.string.Video).concat("@" + "pause" + "@");
                    ((MainActivity) getActivity()).sendCommand(message, true);
                }


            }
        });
        videoStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = getString(R.string.Video).concat("@"+ "stop" +"@");
                ((MainActivity) getActivity()).sendCommand(message, true);
                piScoreBoard.setVideoState(false);
            }
        });
        publicityPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (piScoreBoard.isPubEnable()){
                    message = getString(R.string.Publicity).concat("@"+ "on" +"@");
                    ((MainActivity) getActivity()).sendCommand(message, true);
                }else{
                    Toast.makeText(getActivity(), "Publicidade desativada!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        publicityStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (piScoreBoard.isPubEnable()){
                    message = getString(R.string.Publicity).concat("@"+ "off" +"@");
                    ((MainActivity) getActivity()).sendCommand(message, true);
                }else{
                     Toast.makeText(getActivity(), "Publicidade desativada!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return dialog;
    }
}