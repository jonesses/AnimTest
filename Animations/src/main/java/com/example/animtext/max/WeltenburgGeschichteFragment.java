package com.example.animtext.max;

import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import com.example.animtest.animations.MainActivity;
import com.example.animtest.animations.R;
import com.example.animtest.raphael.BischofshofApplikation;
import com.example.animtest.raphael.Geschichte;

import java.util.ArrayList;

import de.ur.mi.projektion.bischofshof.listeners.OnFragmentInteractionListener;


/**
 * Created by Jonas on 08.07.2014.
 */
public class WeltenburgGeschichteFragment extends android.support.v4.app.Fragment implements
        ViewPager.OnPageChangeListener {

    private Typeface font, fontBold;

    private BischofshofApplikation app;
    private ArrayList<Geschichte> weltenburg_geschichte;
    private Point p;
    private Display display;
    private KKViewPager mPager;
    private TestFragmentAdapter mAdapter;
    private Button button1, button2, button3, button4, button5, button6,
            button7, button8, button9, button10, button11, button12, button13,
            button14, button15, button16, button17;
    private ArrayList<Button> buttons;
    private int alphaButtons = 255;
    private int currentPage;
    private HorizontalScrollView sv;
    private int arg2Old;
    private int firstButtonId; // interne Id des ersten Buttons, drauffolgenden
                                // sind immer +1
    private boolean scrollInForLoop = false;
    private Button backButton;

    View v;
    private OnFragmentInteractionListener interactionListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {        
        v = inflater.inflate(R.layout.layout_weltenburg_geschichte, container, false);
        initData();
        Log.e("geschichteFragment", "oncreateView");

        return v;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
       super.onHiddenChanged(hidden);
       if(!hidden) {
           initData();
       }

    }

    private void initData(){

        referenceClasses();
        referenceFonts();
        referenceUIElements();

        setButtonYear();
        setKKViewPager();
        checkSelectedPage();

        setOnClickListeners();

        setTypeFaces();

        fixPage();

    }

    private void fixPage() {
        float x, tox;

        x = 300.0f;
        tox = 700.0f;

            long downTime = SystemClock.uptimeMillis();
            long eventTime = SystemClock.uptimeMillis() + 100;
            float y = 100.0f;

            // List of meta states found here:
            // developer.android.com/reference/android/view/KeyEvent.html#getMetaState()
            int metaState = 0;
            MotionEvent motionEvent = MotionEvent.obtain(downTime, eventTime,
                    MotionEvent.ACTION_DOWN, x, y, metaState);

            MotionEvent motionEvent2 = MotionEvent.obtain(downTime, eventTime,
                    MotionEvent.ACTION_MOVE, tox, y, metaState);

            MotionEvent motionEvent3 = MotionEvent.obtain(downTime, eventTime,
                    MotionEvent.ACTION_UP, tox, y, metaState);

            // Dispatch touch event to view
            mPager.dispatchTouchEvent(motionEvent);
//            mPager.dispatchTouchEvent(motionEvent2);
            mPager.dispatchTouchEvent(motionEvent3);

    }

    private void setTypeFaces() {
        for (int i = 0; i < 17; i++) {
            buttons.get(i).setTypeface(font);
        }
    }

    private void referenceFonts() {

        font = Typeface.createFromAsset(getActivity().getAssets(), "font/Georgia.ttf");
        fontBold = Typeface.createFromAsset(getActivity().getAssets(),
                "font/Georgia_Bold.ttf");

    }

    private void setOnClickListeners() {
        firstButtonId = button1.getId();
        for (int i = 0; i < 17; i++) {
            buttons.get(i).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    int pressedButtonNumber = (v.getId() - firstButtonId);
                    scrollToPage(pressedButtonNumber);

                    Log.d("test", "button pressed " + pressedButtonNumber);
                }

            });

        }
    }

    private void scrollToPage(int pressedButtonNumber) {
        float x, tox;
        int differenz, scroll;
        if (pressedButtonNumber > currentPage) {
            scroll = 20;
            x = 1700.0f;
            tox = 150.0f;
            differenz = pressedButtonNumber - currentPage;
        } else if (pressedButtonNumber < currentPage) {
            scroll = -20;
            x = 150.0f;
            tox = 1700.0f;
            differenz = currentPage - pressedButtonNumber;
        } else {
            x = 0;
            tox = 0;
            differenz = 0;
            scroll = 0;
        }

        scrollInForLoop = true;
        // Versuch den touchbug zu beheben
        // if (mPager.isClickable()) {
        // mPager.setClickable(false);
        // }

        Log.d("test", "mPager.SCROLL_STATE_DRAGGING: "
                + mPager.SCROLL_STATE_DRAGGING + " mPager.SCROLL_STATE_IDLE: "
                + mPager.SCROLL_STATE_IDLE + " mPager.SCROLL_STATE_SETTLING: "
                + mPager.SCROLL_STATE_SETTLING);

        for (int i = 0; i < differenz; i++) {
            // Obtain MotionEvent object

            sv.smoothScrollBy(scroll, 0);
            long downTime = SystemClock.uptimeMillis();
            long eventTime = SystemClock.uptimeMillis() + 100;
            float y = 100.0f;

            // List of meta states found here:
            // developer.android.com/reference/android/view/KeyEvent.html#getMetaState()
            int metaState = 0;
            MotionEvent motionEvent = MotionEvent.obtain(downTime, eventTime,
                    MotionEvent.ACTION_DOWN, x, y, metaState);

            MotionEvent motionEvent2 = MotionEvent.obtain(downTime, eventTime,
                    MotionEvent.ACTION_MOVE, tox, y, metaState);

            MotionEvent motionEvent3 = MotionEvent.obtain(downTime, eventTime,
                    MotionEvent.ACTION_UP, x, y, metaState);

            // Dispatch touch event to view
            mPager.dispatchTouchEvent(motionEvent);
            mPager.dispatchTouchEvent(motionEvent2);
            mPager.dispatchTouchEvent(motionEvent3);
        }

        scrollInForLoop = false;
        // versuch den touchbug zu beheben, siehe oben
        // mPager.setClickable(true);

    }

    private void checkSelectedPage() {
        for (int i = 0; i < 17; i++) {
            if (currentPage == i) {
                buttons.get(i).setBackgroundResource(
                        R.drawable.round_buttons_active);
            }
        }
        setButtonAlpha();
    }

    private void setButtonYear() {
        for (int i = 0; i < 17; i++) {
            buttons.get(i).setText(
                    String.valueOf(weltenburg_geschichte.get(i)
                            .getGeschichteJahr()));
        }
        setButtonAlpha();
    }

    private void setButtonAlpha() {
        for (int i = 0; i < 17; i++) {
            buttons.get(i).getBackground().setAlpha(alphaButtons);
        }
    }

    private void resetButtons() {
        for (int i = 0; i < 17; i++) {
            buttons.get(i).setBackgroundResource(R.drawable.round_buttons);
        }
    }

    private void referenceUIElements() {
        v.findViewById(R.id.buttonBack).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                interactionListener.onTransitionFromFullscreenRequested();
            }
        });

        sv = (HorizontalScrollView) v.findViewById(R.id.timeLine);
        mPager = (KKViewPager) v.findViewById(R.id.kk_pager);
        p = new Point();

        referenceButtons();
    }

    private void referenceButtons() {
        buttons = new ArrayList<Button>();
        buttons.add(button1 = (Button) v.findViewById(R.id.Button1));
        buttons.add(button2 = (Button) v.findViewById(R.id.Button2));
        buttons.add(button3 = (Button) v.findViewById(R.id.Button3));
        buttons.add(button4 = (Button) v.findViewById(R.id.Button4));
        buttons.add(button5 = (Button) v.findViewById(R.id.Button5));
        buttons.add(button6 = (Button) v.findViewById(R.id.Button6));
        buttons.add(button7 = (Button) v.findViewById(R.id.Button7));
        buttons.add(button8 = (Button) v.findViewById(R.id.Button8));
        buttons.add(button9 = (Button) v.findViewById(R.id.Button9));
        buttons.add(button10 = (Button) v.findViewById(R.id.Button10));
        buttons.add(button11 = (Button) v.findViewById(R.id.Button11));
        buttons.add(button12 = (Button) v.findViewById(R.id.Button12));
        buttons.add(button13 = (Button) v.findViewById(R.id.Button13));
        buttons.add(button14 = (Button) v.findViewById(R.id.Button14));
        buttons.add(button15 = (Button) v.findViewById(R.id.Button15));
        buttons.add(button16 = (Button) v.findViewById(R.id.Button16));
        buttons.add(button17 = (Button) v.findViewById(R.id.Button17));

    }

    private void referenceClasses() {
        app = (BischofshofApplikation) getActivity().getApplication();
        weltenburg_geschichte = sort(app.getWeltenburgGeschichte());

    }

    // Mist - Übergangsweise
    private ArrayList<Geschichte> sort(
            ArrayList<Geschichte> weltenburgGeschichte) {
        ArrayList<Geschichte> sortedArray = new ArrayList<Geschichte>();
        for (int i = 1; i < 2100; i++) {
            for (int m = 0; m < weltenburgGeschichte.size(); m++) {
                if (i == weltenburgGeschichte.get(m).getGeschichteJahr()) {
                    sortedArray.add(weltenburgGeschichte.get(m));
                }
            }
        }
        return sortedArray;
    }

    private void setKKViewPager() {
        display = getActivity().getWindowManager().getDefaultDisplay();
        display.getSize(p);
        mAdapter = new TestFragmentAdapter(getActivity().getSupportFragmentManager(), mPager,
                getActivity(), weltenburg_geschichte);
        mPager.setDisplay(display);
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(this);
        mPager.setPageMargin(-((p.x * 18) / 90));
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.(hasFocus);
//        mPager.onWindowFocusChanged(hasFocus);
//    }
//

    @Override
    public void onPageScrollStateChanged(int arg0) {
        resetButtons();
        checkSelectedPage();

        currentPage = mPager.getCurrentItem();

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

        if (!scrollInForLoop) {
            if (arg2 > arg2Old) {
                sv.smoothScrollBy(10, 0);
            } else if (arg2 < arg2Old) {
                sv.smoothScrollBy(-10, 0);
            }

            arg2Old = arg2;

            if (arg0 == 0) {
                sv.smoothScrollBy(-400, 0);
            } else if (arg0 == 15) {
                sv.smoothScrollBy(400, 0);
            }
        }

    }

    @Override
    public void onPageSelected(int arg0) {
        mPager.getReadyWithNextTwoView(arg0);
    }

    public void setFragmentInteractionListener(OnFragmentInteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }
}
