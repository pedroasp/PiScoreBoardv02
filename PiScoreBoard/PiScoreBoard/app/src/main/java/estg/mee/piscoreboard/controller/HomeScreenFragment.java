package estg.mee.piscoreboard.controller;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import estg.mee.piscoreboard.R;
import estg.mee.piscoreboard.model.Game;
import estg.mee.piscoreboard.model.PiScoreBoard;
import estg.mee.piscoreboard.utils.AutoResizeTextView;

/**
 * Created by Rúben Rodrigues on 07-05-2015.
 */
public class HomeScreenFragment extends Fragment{
    View rootview;
    private int mInterval = 500;
    private int nVisit = 0,nLocal = 0, nLocalFaults = 0, nVisitFaults = 0, nParts;
    private Handler clockHandler;
    float initialX, initialY;
    float finalX, finalY;
    TextView  sLocalGoals, sVisitGoals, sTime, sLocalFaults, sVisitFaults, sParts, sFaults;
    private String message;
    ImageView sLocalLogo, sVisitLogo;
    Game currentGame = Game.getInstance();
    PiScoreBoard piScoreBoard = PiScoreBoard.getInstance();
    private ViewGroup _textViewcontainer;
    int height, width;
    ViewGroup.MarginLayoutParams sVisitGoalsMargins;
    ViewGroup.MarginLayoutParams sLocalGoalsMargins;
    AutoResizeTextView sLocalNameResized, sVisitNameResized;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootview = inflater.inflate(R.layout.fragment_home_screen,container,false);
        //_textViewcontainer = container;
        _textViewcontainer = (ViewGroup)rootview.findViewById(R.id.parameterscontainer);

        height = this.getResources().getDisplayMetrics().heightPixels;
        width = this.getResources().getDisplayMetrics().widthPixels;

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            width = this.getResources().getDisplayMetrics().heightPixels;
            height = this.getResources().getDisplayMetrics().widthPixels;
        }
        
        final int HCENTER = (int) (width * 0.5);   //Centro Horizontal
        final int desfasamentoSimbolos = (int) (width * 0.10);
        final int desfasamentoNames = (int) (width * 0.26);

        sLocalGoals= (TextView) rootview.findViewById(R.id.sLocalGoals);
        sVisitGoals = (TextView) rootview.findViewById(R.id.sVisitGoals);
        sVisitFaults = (TextView) rootview.findViewById(R.id.sVisitFaults);
        sLocalFaults = (TextView) rootview.findViewById(R.id.sLocalFaults);

        sParts = (TextView) rootview.findViewById(R.id.sParts);
        sTime = (TextView) rootview.findViewById(R.id.sTime);
        sLocalLogo = (ImageView) rootview.findViewById(R.id.sLocalLogo);
        sVisitLogo = (ImageView) rootview.findViewById(R.id.sVisitLogo);
        sFaults = (TextView) rootview.findViewById(R.id.sFaults);

        nLocal = currentGame.getnLocal();
        sLocalGoals.setText(String.valueOf(nLocal));
        nVisit = currentGame.getnVisit();
        sVisitGoals.setText(String.valueOf(nVisit));
        nVisitFaults = currentGame.getnVisitFaults();
        sVisitFaults.setText(String.valueOf(nVisitFaults));
        nLocalFaults = currentGame.getnVisitFaults();
        sLocalFaults.setText(String.valueOf(nLocalFaults));
        nParts = currentGame.getnPart();
        sParts.setText(String.valueOf(currentGame.getnPart()).concat(getString(R.string.sPart)));
        sLocalFaults.setText(String.valueOf(nLocalFaults));

        if(piScoreBoard.isTimeMode()){
            sTime.setVisibility(View.INVISIBLE);
        }else
        {
            sTime.setVisibility(View.VISIBLE);
        }
        if(piScoreBoard.isFaultsEnable()){
            sLocalFaults.setVisibility(View.VISIBLE);
            sVisitFaults.setVisibility(View.VISIBLE);
            sFaults.setVisibility(View.VISIBLE);
        }else{
            sLocalFaults.setVisibility(View.INVISIBLE);
            sVisitFaults.setVisibility(View.INVISIBLE);
            sFaults.setVisibility(View.INVISIBLE);
        }


//    //Draw Point
//        //...
//        RelativeLayout root = (RelativeLayout) rootview.findViewById(R.id.teste);
//        ImageView img = new ImageView(getActivity());
//        img.setBackgroundColor(Color.RED);
//        //..load something inside the ImageView, we just set the background color
//
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(20, 200);
//        params.leftMargin = 50;
//        params.topMargin  = 205;
//        root.addView(img, params);
//        //...
//    //




        //sLocalGoals.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) (width/7));

        //sLocalGoals = (TextView) rootview.findViewById(R.id.sLocalGoals);
        //ViewGroup.MarginLayoutParams sLocalGoalsMargins = (ViewGroup.MarginLayoutParams) sLocalGoals.getLayoutParams();

        //sLocalGoalsMargins.setMargins(0 ,-5, 0, 0);//all in pixels
        //sLocalGoals.setLayoutParams(sLocalGoalsMargins);

        sLocalGoals.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub~

                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_MOVE:

                        break;

                    case MotionEvent.ACTION_UP:
                        finalX = event.getX();
                        finalY = event.getY();


                        if (finalY - initialY > 100 && nLocal != 0) {
                            nLocal--;
                            if(nLocal<0) nLocal=0;
                        } else if (initialY - finalY > 100) {
                            nLocal++;
                        }
                        if (Math.abs(finalY - initialY) > 100) {
                            message = getString(R.string.LocalGoals).concat("@" + nLocal + "@");
                            ((MainActivity) getActivity()).sendCommand(message, true);
                            sLocalGoals.setText(String.valueOf(nLocal));
                            currentGame.setnLocal(nLocal);
                        }
                        sLocalGoalsMargins = (ViewGroup.MarginLayoutParams) sLocalGoals.getLayoutParams();
                        if (nLocal>9){
                            sLocalGoalsMargins.setMargins(15, 0, 0, 0);
                        }else
                        {
                            sLocalGoalsMargins.setMargins(70, 0, 0, 0);
                        }
                        sLocalGoals.setLayoutParams(sLocalGoalsMargins);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        initialY = event.getY();
                        break;
                }
                return true;
            }

        });



        sVisitGoals.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub~
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_MOVE:

                        break;

                    case MotionEvent.ACTION_UP:
                        finalX = event.getX();
                        finalY = event.getY();

                        if (finalY - initialY > 100 && nVisit != 0) {
                            nVisit--;
                            if(nVisit<0) nVisit=0;
                        } else if (initialY - finalY > 100) {
                            nVisit++;
                        }
                        if (Math.abs(finalY - initialY) > 100) {
                            message = getString(R.string.VisitGoals).concat("@" + nVisit + "@");
                            ((MainActivity) getActivity()).sendCommand(message, true);
                            sVisitGoals.setText(String.valueOf(nVisit));
                            currentGame.setnVisit(nVisit);
                        }
                        sVisitGoalsMargins = (ViewGroup.MarginLayoutParams) sVisitGoals.getLayoutParams();
                        if (nVisit>9){
                            sVisitGoalsMargins.setMargins(0, 0, 25, 0);
                        }else
                        {
                            sVisitGoalsMargins.setMargins(0, 0, 70, 0);
                        }
                        sVisitGoals.setLayoutParams(sVisitGoalsMargins);


                        break;
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        initialY = event.getY();
                        break;
                }
                return true;
            }
        });



        sLocalFaults.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub~
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_MOVE:

                        break;

                    case MotionEvent.ACTION_UP:
                        finalX = event.getX();
                        finalY = event.getY();

                        if (finalY - initialY > 20 && nLocalFaults != 0) {
                            nLocalFaults--;
                            if(nLocalFaults<0) nLocalFaults=0;
                        } else if (initialY - finalY > 20 && nLocalFaults < currentGame.getModality().getnFaults()) {
                            nLocalFaults++;
                        }
                        if (Math.abs(finalY - initialY) > 20) {
                            message = getString(R.string.LocalFaults).concat("@" + nLocalFaults + "@");
                            ((MainActivity) getActivity()).sendCommand(message, true);
                            sLocalFaults.setText(String.valueOf(nLocalFaults));
                            currentGame.setnLocalFaults(nLocalFaults);
                        }

                        break;
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        initialY = event.getY();
                        break;
                }
                return true;
            }
        });


        sVisitFaults.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub~
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_MOVE:

                        break;

                    case MotionEvent.ACTION_UP:
                        finalX = event.getX();
                        finalY = event.getY();

                        if (finalY - initialY > 20 && nVisitFaults != 0) {
                            nVisitFaults--;
                            if(nVisitFaults<0) nVisitFaults=0;
                        } else if (initialY - finalY > 20 && nVisitFaults < currentGame.getModality().getnFaults()) {
                            nVisitFaults++;
                        }
                        if (Math.abs(finalY - initialY) > 20) {
                            message = getString(R.string.VisitFaults).concat("@" + nVisitFaults + "@");
                            ((MainActivity) getActivity()).sendCommand(message, true);
                            sVisitFaults.setText(String.valueOf(nVisitFaults));
                            currentGame.setnVisitFaults(nVisitFaults);
                        }

                        break;
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        initialY = event.getY();
                        break;
                }
                return true;
            }
        });

        sParts.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub~

                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_MOVE:

                        break;

                    case MotionEvent.ACTION_UP:
                        finalX = event.getX();
                        finalY = event.getY();



                        if (finalY - initialY > 60 && nParts != 0) {
                            nParts--;
                            if(nParts<1) nParts=1;
                        } else if (initialY - finalY > 60 && nParts < currentGame.getModality().getnParts()) {
                            nParts++;
                            currentGame.setnVisitFaults(0);
                            currentGame.setnLocalFaults(0);
                            nVisitFaults = 0;
                            nLocalFaults = 0;
                            sVisitFaults.setText(String.valueOf(currentGame.getnLocalFaults()));
                            sLocalFaults.setText(String.valueOf(currentGame.getnVisitFaults()));
                            message = getString(R.string.VisitFaults).concat("@" + currentGame.getnVisitFaults() + "@" + "\r\n").concat(getString(R.string.LocalFaults).concat("@" + currentGame.getnLocalFaults() + "@" + "\r\n"));
                            ((MainActivity) getActivity()).sendCommand(message, true);
                        }
                        if (Math.abs(finalY - initialY) > 60) {
                            sParts.setText(String.valueOf(nParts).concat(getString(R.string.sPart)));
                            currentGame.setnPart(nParts);
                            message = getString(R.string.Parts).concat("@" + nParts + "@");
                            ((MainActivity) getActivity()).sendCommand(message, true);

                        }


                        break;
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        initialY = event.getY();
                        break;
                }
                return true;
            }
        });


        clockHandler = new Handler();
        refreshTime();

        startClock();

        //Resize Logos

        //     Size Image
      //  sLocalLogo.setImageResource(currentGame.getEquipaLocal().getLogotipo());
        sLocalLogo.setImageURI(Uri.parse(currentGame.getEquipaLocal().getLogotipo()));
        sVisitLogo.setImageURI(Uri.parse(currentGame.getEquipaVisitante().getLogotipo()));
        android.view.ViewGroup.LayoutParams sLocalLogoLayoutParams = sLocalLogo.getLayoutParams();
        android.view.ViewGroup.LayoutParams sVisitLogoLayoutParams = sVisitLogo.getLayoutParams();
        sLocalLogoLayoutParams.width = (int) (width * 0.20);
        sLocalLogoLayoutParams.height = (int) (height * 0.20);
        sVisitLogoLayoutParams.width = (int) (width * 0.20);
        sVisitLogoLayoutParams.height = (int) (height * 0.20);
        sLocalLogo.setLayoutParams(sLocalLogoLayoutParams);
        sVisitLogo.setLayoutParams(sVisitLogoLayoutParams);
        //    Position Image
        ViewGroup.MarginLayoutParams sLocalLogoMargins = (ViewGroup.MarginLayoutParams) sLocalLogo.getLayoutParams();
        sLocalLogoMargins.setMargins(HCENTER - desfasamentoSimbolos - sLocalLogo.getLayoutParams().width,
                (int) (width * 0.04), 0, 0);//all in pixels
        sLocalLogo.setLayoutParams(sLocalLogoMargins);


        ViewGroup.MarginLayoutParams sVisitLogoMargins = (ViewGroup.MarginLayoutParams) sVisitLogo.getLayoutParams();
        sVisitLogoMargins.setMargins(HCENTER + desfasamentoSimbolos,
                (int) (width * 0.04), 0, 0);//all in pixels
        sVisitLogo.setLayoutParams(sVisitLogoMargins);
        //Position Goals
        sLocalGoalsMargins = (ViewGroup.MarginLayoutParams) sLocalGoals.getLayoutParams();
        if (nLocal>9){
            sLocalGoalsMargins.setMargins(15, 0, 0, 0);
        }else
        {
            sLocalGoalsMargins.setMargins(70, 0, 0, 0);
        }
        sLocalGoals.setLayoutParams(sLocalGoalsMargins);
        sVisitGoalsMargins = (ViewGroup.MarginLayoutParams) sVisitGoals.getLayoutParams();
        if (nVisit>9){
            sVisitGoalsMargins.setMargins(0, 0, 25, 0);
        }else
        {
            sVisitGoalsMargins.setMargins(0, 0, 70, 0);
        }
        sVisitGoals.setLayoutParams(sVisitGoalsMargins);


        sLocalNameResized = recreateTextViewName(sLocalNameResized,currentGame.getEquipaLocal().getName(),HCENTER - desfasamentoNames-(int) (0.2 * width),(int) (width * 0.04));
        sVisitNameResized = recreateTextViewName(sVisitNameResized,currentGame.getEquipaVisitante().getName(),HCENTER + desfasamentoNames,(int) (width * 0.04));

        return rootview;
    }


    protected AutoResizeTextView recreateTextViewName(AutoResizeTextView textView, String title, int x, int y)
    {
        //_textViewcontainer.removeAllViews();
        _textViewcontainer.removeView(textView);
        textView = new AutoResizeTextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        final int textviewwidth = (int) Math.round(0.2 * width);
        final int textviewheight =(int) Math.round(0.2 * height);
        final int maxLinesCount=2;
        textView.setMaxLines(maxLinesCount);
        textView.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, width, getResources().getDisplayMetrics()));
        textView.setEllipsize(TextUtils.TruncateAt.END);
        // since we use it only once per each click, we don't need to cache the results, ever
        textView.setEnableSizeCache(false);
        textView.setEnableSizeCache(false);
        textView.setLayoutParams(new ViewGroup.LayoutParams(textviewwidth, textviewheight));
        textView.setX(x);
        textView.setY(y);
        textView.setBackgroundColor(0x00ffffff);
        textView.setText(title);
        textView.getLeft();


        _textViewcontainer.addView(textView);
        return textView;
    }



    /**
     * inicio metodo para refresh do relogio
     */
    private void refreshTime() {
        Calendar sysTime = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String strDate = sdf.format(sysTime.getTime());
        sTime.setText(strDate);
    }

    /**
     * Simple task 500mS para actualizar o relógio
     */
    Runnable clockStatusChecker = new Runnable() {
        @Override
        public void run() {


            refreshTime();


            clockHandler.postDelayed(clockStatusChecker, mInterval);
        }
    };

    void startClock() {
        clockStatusChecker.run();
    }

    void stopClock() {
        clockHandler.removeCallbacks(clockStatusChecker);
    }

}
