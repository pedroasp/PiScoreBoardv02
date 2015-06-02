package estg.mee.piscoreboard.controller;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import estg.mee.piscoreboard.R;
import me.nereo.multi_image_selector.bean.Image;

/**
 * Created by Rúben Rodrigues on 07-05-2015.
 */
public class HomeScreenFragment extends Fragment {
    View rootview;
    private int mInterval = 500;
    private static final String GLOCAL = "GLOCAL";
    private static final String GVISIT = "GVISIT";
    private int nVisit,nLocal, nLocalFaults, nVisitFaults;
    private Handler clockHandler;
    float initialX, initialY;
    float finalX, finalY;
    TextView  sLocalGoals, sVisitGoals, sTime, sLocalFaults, sVisitFaults;
    private String message;
    ImageView sLocalLogo, sVisitLogo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootview = inflater.inflate(R.layout.fragment_home_screen,container,false);

        int height = this.getResources().getDisplayMetrics().heightPixels;
        int width = this.getResources().getDisplayMetrics().widthPixels;


        final int HCENTER = (int) (width * 0.5);   //Centro Horizontal
        final int desfasamentoSimbolos = (int) (width * 0.15);

        sLocalGoals= (TextView) rootview.findViewById(R.id.sLocalGoals);
        sTime = (TextView) rootview.findViewById(R.id.sTime);

        sLocalLogo = (ImageView) rootview.findViewById(R.id.sLocalLogo);
        sVisitLogo = (ImageView) rootview.findViewById(R.id.sVisitLogo);


        //Resize Logos
        android.view.ViewGroup.LayoutParams sLocalLogoLayoutParams = sLocalLogo.getLayoutParams();
        android.view.ViewGroup.LayoutParams sVisitLogoLayoutParams = sVisitLogo.getLayoutParams();
        sLocalLogoLayoutParams.width = (int) (width * 0.20);
        sLocalLogoLayoutParams.height = (int) (height * 0.20);
        sVisitLogoLayoutParams.width = (int) (width * 0.20);
        sVisitLogoLayoutParams.height = (int) (height * 0.20);


        sLocalLogo.setLayoutParams(sLocalLogoLayoutParams);
        sVisitLogo.setLayoutParams(sVisitLogoLayoutParams);

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

        ViewGroup.MarginLayoutParams sLocalLogoMargins = (ViewGroup.MarginLayoutParams) sLocalLogo.getLayoutParams();
        sLocalLogoMargins.setMargins(HCENTER - desfasamentoSimbolos - sLocalLogo.getLayoutParams().width,
                (int) (width * 0.04), 0, 0);//all in pixels
        sLocalLogo.setLayoutParams(sLocalLogoMargins);

        ViewGroup.MarginLayoutParams sVisitLogoMargins = (ViewGroup.MarginLayoutParams) sVisitLogo.getLayoutParams();
        sVisitLogoMargins.setMargins(HCENTER + desfasamentoSimbolos,
                (int) (width * 0.04), 0, 0);//all in pixels
        sVisitLogo.setLayoutParams(sVisitLogoMargins);


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
                        message = getString(R.string.LocalGoals).concat("@"+nLocal+"@");
                        ((MainActivity)getActivity()).sendCommand(message);
                        sLocalGoals.setText("" + nLocal);

                        ViewGroup.MarginLayoutParams llp = (ViewGroup.MarginLayoutParams) sLocalGoals.getLayoutParams();
                        if (nLocal>9){
                            llp.setMargins(15, 0, 0, 0);
                        }else
                        {
                            llp.setMargins(70, 0, 0, 0);
                        }
                        sLocalGoals.setLayoutParams(llp);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        initialY = event.getY();
                        break;
                }
                return true;
            }
        });
        sVisitGoals = (TextView) rootview.findViewById(R.id.sVisitGoals);


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
                        message = getString(R.string.VisitGoals).concat("@"+nVisit+"@");
                        ((MainActivity)getActivity()).sendCommand(message);

                        sVisitGoals.setText("" + nVisit);


                        ViewGroup.MarginLayoutParams llp = (ViewGroup.MarginLayoutParams) sVisitGoals.getLayoutParams();
                        if (nVisit>9){
                            llp.setMargins(0, 0, 25, 0);
                        }else
                        {
                            llp.setMargins(0, 0, 70, 0);
                        }
                        sVisitGoals.setLayoutParams(llp);

                        break;
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        initialY = event.getY();
                        break;
                }
                return true;
            }
        });

        sLocalFaults = (TextView) rootview.findViewById(R.id.sLocalFaults);

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
                        } else if (initialY - finalY > 100) {
                            nLocalFaults++;
                        }
                        message = getString(R.string.LocalFaults).concat("@"+nLocalFaults+"@");
                        ((MainActivity)getActivity()).sendCommand(message);
                        sLocalFaults.setText("" + nLocalFaults);

//                        ViewGroup.MarginLayoutParams llp = (ViewGroup.MarginLayoutParams) sLocalFaults.getLayoutParams();
//                        if (nLocalFaults>9){
//                            llp.setMargins(0, 0, 25, 0);
//                        }else
//                        {
//                            llp.setMargins(0, 0, 70, 0);
//                        }
//                        sLocalFaults.setLayoutParams(llp);

                        break;
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        initialY = event.getY();
                        break;
                }
                return true;
            }
        });
        sVisitFaults = (TextView) rootview.findViewById(R.id.sVisitFaults);

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
                        } else if (initialY - finalY > 100) {
                            nVisitFaults++;
                        }
                        message = getString(R.string.VisitFaults).concat("@"+nVisitFaults+"@");
                        ((MainActivity)getActivity()).sendCommand(message);
                        sVisitFaults.setText("" + nVisitFaults);

//                        ViewGroup.MarginLayoutParams llp = (ViewGroup.MarginLayoutParams) sLocalFaults.getLayoutParams();
//                        if (nLocalFaults>9){
//                            llp.setMargins(0, 0, 25, 0);
//                        }else
//                        {
//                            llp.setMargins(0, 0, 70, 0);
//                        }
//                        sLocalFaults.setLayoutParams(llp);

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

        return rootview;
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
