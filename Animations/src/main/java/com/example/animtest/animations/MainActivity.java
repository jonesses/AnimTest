package com.example.animtest.animations;

import android.animation.Animator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.animtest.raphael.AktuellesFragment;
import com.example.animtest.raphael.Article;
import com.example.animtest.raphael.AuflugFragment;
import com.example.animtest.raphael.AusflugAutoFragmen;
import com.example.animtest.raphael.AusflugFussFragment;
import com.example.animtest.raphael.AusflugRadFragment;
import com.example.animtest.raphael.AusflugSchiffFragment;
import com.example.animtest.raphael.BischofshofApplikation;
import com.example.animtest.raphael.BischofshofClasses;
import com.example.animtest.raphael.BitmapItem;
import com.example.animtest.raphael.GeneralContent;
import com.example.animtest.raphael.Geschichte;
import com.example.animtest.raphael.GeschichteAllgemeinesFragment;
import com.example.animtest.raphael.Logos;
import com.example.animtest.raphael.Produkte;
import com.example.animtest.raphael.ProdukteAllgemeinesFragment;
import com.example.animtest.raphael.StringItem;
import com.example.animtext.max.WeltenburgGeschichteFragment;

import java.util.ArrayList;

import de.ur.mi.projektion.bischofshof.CategoryConstants;
import de.ur.mi.projektion.bischofshof.listeners.OnCategorySelectedListener;
import de.ur.mi.projektion.bischofshof.listeners.OnFragmentInteractionListener;


public class MainActivity extends FragmentActivity implements OnFragmentInteractionListener, OnCategorySelectedListener, Animator.AnimatorListener {


    private FragmentManager fragmentManager;
    private BackgroundFragment background;
    private StartScreen startScreen;
    private LeftSideFragment leftSide;
    private RightSideFragment rightSide;
    private AktuellesFragment aktuellesFragment;
    private AusflugAutoFragmen ausflugAutoFragment;
    private AusflugFussFragment ausflugFussFragment;
    private AusflugRadFragment ausflugRadFragment;
    private AusflugSchiffFragment ausflugSchiffFragment;
    private ProdukteAllgemeinesFragment produkteAllgemeinesFragment;
    private GeschichteAllgemeinesFragment geschichteAllgemeinesFragment;
    private BackgroundCircleFragment backgroundCircle;
    private AuflugFragment ausflugFragment;
    private WeltenburgGeschichteFragment geschichteFragment;

    //NEU
    private BischofshofApplikation app;
    private BischofshofClasses classes;
    private Bitmap weltenburg_logo;
    private GeneralContent weltenburg_aktuelles, weltenburg_geschichte_allgemeines, weltenburg_produkte_allgemeines, weltenburg_route;
    private ArrayList<Geschichte> weltenburg_geschichte;
    private ArrayList<Produkte> weltenburg_produkte;
    private ArrayList<Logos> weltenburg_aufNachWeltenburg_logos;
    private android.support.v4.app.FragmentManager supportFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initManagers();

        referenceClasses();
        getLogos();
        getWeltenburgData();

        initUI();


    }

    private void initManagers() {
        fragmentManager = getFragmentManager();
        supportFragmentManager = getSupportFragmentManager();
    }


    private void initUI() {
        background = new BackgroundFragment();
        startScreen = new StartScreen();
        leftSide = new LeftSideFragment();
        rightSide = new RightSideFragment();
        backgroundCircle = new BackgroundCircleFragment();
        aktuellesFragment = new AktuellesFragment();

        ausflugAutoFragment = new AusflugAutoFragmen();
        ausflugFussFragment = new AusflugFussFragment();
        ausflugRadFragment = new AusflugRadFragment();
        ausflugSchiffFragment = new AusflugSchiffFragment();
        geschichteAllgemeinesFragment = new GeschichteAllgemeinesFragment();
        produkteAllgemeinesFragment = new ProdukteAllgemeinesFragment();
        ausflugFragment = new AuflugFragment();
        geschichteFragment = new WeltenburgGeschichteFragment();

        leftSide.setFragmentInteractionListener(this);
        rightSide.setFragmentInteractionListener(this);
        leftSide.setCategorySelectedListener(this);
        startScreen.setFragmentInteractionListener(this);
        geschichteAllgemeinesFragment.setOnFragmentInteractionListener(this);

        // supportFragmentManager.beginTransaction().add(R.id.frame, geschichteFragment).hide(geschichteFragment).commit();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.frame, background)//.hide(background)
                .add(R.id.frame, leftSide)//.hide(leftSide)
                .add(R.id.frame, backgroundCircle)//.hide(backgroundCircle)
                .add(R.id.frame, ausflugFragment).hide(ausflugFragment)

                // .add(R.id.frame, ausflugFussFragment).hide(ausflugFussFragment)
//                .add(R.id.frame, ausflugSchiffFragment).hide(ausflugSchiffFragment)
//                .add(R.id.frame, ausflugRadFragment).hide(ausflugRadFragment)
//                .add(R.id.frame, ausflugAutoFragment).hide(ausflugAutoFragment)
                .add(R.id.frame, geschichteAllgemeinesFragment).hide(geschichteAllgemeinesFragment)
                .add(R.id.frame, produkteAllgemeinesFragment).hide(produkteAllgemeinesFragment)
                .add(R.id.frame, aktuellesFragment)//.hide(aktuellesFragment)
                .add(R.id.frame, startScreen)
                .commit();


    }


    @Override
    public void onClick(View clickedView, String side) {

    }

    @Override
    public void onTransitionToFullscreenRequested() {
        FragmentTransaction fullscreenTransaction =  fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fullscreenTransaction.hide(leftSide)
                .hide(rightSide)
                .hide(geschichteAllgemeinesFragment)
                .commit();
        backgroundCircle.expandToFullscreen(this);

    }

    @Override
    public void onAnimationStarted() {
//        showOverviewFragments();


    }

    private void showOverviewFragments() {

        fragmentManager.beginTransaction()
                .show(background)
                .show(leftSide)
                .show(backgroundCircle)
                .show(aktuellesFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void onAnimationFinished() {
        fragmentManager
                .beginTransaction()
                .hide(startScreen)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

                .commit();


    }


    @Override
    public void onCategorySelected(int color) {
        backgroundCircle.animateNavigationChangeShadow(color);
    }

    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in_support, R.anim.fade_out_support)
                .add(R.id.frame, geschichteFragment)
                .commit();
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }


    @Override
    public void onCategoryChangeRequested(int category) {

        switch (category) {
            case CategoryConstants.CATEGORY_AKTUELLES:
                fragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .hide(ausflugFussFragment)
                        .hide(ausflugSchiffFragment)
                        .hide(ausflugRadFragment)
                        .hide(ausflugFragment)
                        .hide(geschichteAllgemeinesFragment)
                        .hide(produkteAllgemeinesFragment)
                        .show(aktuellesFragment)
                        .commit();
                break;
            case CategoryConstants.CATEGORY_GESCHICHTE:
                fragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .hide(ausflugFussFragment)
                        .hide(ausflugSchiffFragment)
                        .hide(ausflugRadFragment)
                        .hide(ausflugFragment)
                        .show(geschichteAllgemeinesFragment)
                        .hide(produkteAllgemeinesFragment)
                        .hide(aktuellesFragment)
                        .commit();
                break;
            case CategoryConstants.CATEGORY_PRODUKTE:
                fragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .hide(ausflugFussFragment)
                        .hide(ausflugSchiffFragment)
                        .hide(ausflugRadFragment)
                        .hide(ausflugFragment)
                        .hide(geschichteAllgemeinesFragment)
                        .show(produkteAllgemeinesFragment)
                        .hide(aktuellesFragment)
                        .commit();
                break;
            case CategoryConstants.CATEGORY_AUSFLUG:
                fragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .show(ausflugFragment)
                        .hide(ausflugSchiffFragment)
                        .hide(ausflugRadFragment)
                        .hide(geschichteAllgemeinesFragment)
                        .hide(produkteAllgemeinesFragment)
                        .hide(aktuellesFragment)
                        .commit();
                break;
        }
    }

    private void getWeltenburgData() {
        //Aktuelles
        weltenburg_aktuelles = getGeneralContent(this.getString(R.string.weltenburg_aktuelles));
        app.setWeltenburgAktuelles(weltenburg_aktuelles);
        //Produkte Allgemeines
        weltenburg_produkte_allgemeines = getGeneralContent(this.getString(R.string.weltenburg_produkte_allgemeines));
        app.setWeltenburgProdukteAllgemeines(weltenburg_produkte_allgemeines);
        //Produkte
        weltenburg_produkte = getProdukte(this.getString(R.string.weltenburg_produkte));
        app.setWeltenburgProdukte(weltenburg_produkte);
        //Geschichte Allgemeines
        weltenburg_geschichte_allgemeines = getGeneralContent(this.getString(R.string.weltenburg_geschichte_allgemeines));
        app.setWeltenburgGeschichteAllgemeines(weltenburg_geschichte_allgemeines);
        //Geschichte
        weltenburg_geschichte = getGeschichte(this.getString(R.string.weltenburg_geschichte));
        app.setWeltenburgGeschichte(weltenburg_geschichte);
        //Auf nach Weltenburg - Logos
        weltenburg_aufNachWeltenburg_logos = getLogos(this.getString(R.string.weltenburg_aufNachWeltenburg_Logos));
        app.setWeltenburgAufNachWeltenburgLogos(weltenburg_aufNachWeltenburg_logos);
        //Auf nach Weltenburg - Rad
        weltenburg_route = getGeneralContent(this.getString(R.string.weltenburg_aufNachWeltenburg_Rad));
        app.setWeltenburgAufNachWeltenburgRad(weltenburg_route);
        //Auf nach Weltenburg - Fuss
        weltenburg_route = getGeneralContent(this.getString(R.string.weltenburg_aufNachWeltenburg_Fuss));
        app.setWeltenburgAufNachWeltenburgFuss(weltenburg_route);
        //Auf nach Weltenburg - Auto
        weltenburg_route = getGeneralContent(this.getString(R.string.weltenburg_aufNachWeltenburg_Auto));
        app.setWeltenburgAufNachWeltenburgAuto(weltenburg_route);
        //Auf nach Weltenburg - Schiff
        weltenburg_route = getGeneralContent(this.getString(R.string.weltenburg_aufNachWeltenburg_Schiff));
        app.setWeltenburgAufNachWeltenburgSchiff(weltenburg_route);
    }

    private ArrayList<Logos> getLogos(String string) {
        ArrayList<Logos> logos = new ArrayList<Logos>();
        ArrayList<BitmapItem> bitmapItems;
        Bitmap currentBitmap;
        ArrayList<StringItem> deTextItems;

        bitmapItems = classes.getLogos(string, this);
        deTextItems = classes.getTextItems(string + this.getString(R.string.de), this);

        for (int i = 0; i < deTextItems.size(); i++) {
            currentBitmap = null;
            for (int m = 0; m < bitmapItems.size(); m++) {
                if (bitmapItems.get(m).getId().equals(deTextItems.get(i).getId() + "")) {
                    currentBitmap = bitmapItems.get(m).getBitmap();
                }
            }
            logos.add(new Logos(currentBitmap, deTextItems.get(i).getStringItem()));
        }

        return logos;
    }

    private ArrayList<Geschichte> getGeschichte(String string) {
        ArrayList<Geschichte> geschichte = new ArrayList<Geschichte>();
        ArrayList<BitmapItem> bitmapItems;
        Bitmap currentBitmap;
        ArrayList<StringItem> deTextItems;

        bitmapItems = classes.getBitmapItems(string, this);
        deTextItems = classes.getTextItems(string + this.getString(R.string.de), this);

        if (bitmapItems != null && deTextItems != null) {
            for (int i = 0; i < deTextItems.size(); i++) {
                currentBitmap = null;
                for (int m = 0; m < bitmapItems.size(); m++) {
                    if (bitmapItems.get(m).getId().equals(deTextItems.get(i).getId() + "")) {
                        currentBitmap = bitmapItems.get(m).getBitmap();
                    }
                }
                geschichte.add(new Geschichte(currentBitmap, deTextItems.get(i).getStringItem(), deTextItems.get(i).getId()));
            }
        } else {
            Log.e(this.getClass().getName(), string);
        }

        return geschichte;
    }

    private ArrayList<Produkte> getProdukte(String string) {
        ArrayList<Produkte> produkte = new ArrayList<Produkte>();
        ArrayList<Bitmap> aktuelleBitmaps = new ArrayList<Bitmap>();
        ArrayList<String> files = new ArrayList<String>();
        ArrayList<String> deTexts = new ArrayList<String>();
        Bitmap currentBitmap;

        files = classes.getPngFilesInDir(string, this);
        if (files != null) {
            for (int i = 0; i < files.size(); i++) {
                currentBitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + string + files.get(i));
                aktuelleBitmaps.add(currentBitmap);
            }
        }
        deTexts = classes.getTextFiles(string + this.getString(R.string.de), this);

        if (deTexts != null) {
            if (aktuelleBitmaps.size() == deTexts.size()) {
                for (int i = 0; i < aktuelleBitmaps.size(); i++) {
                    produkte.add(new Produkte(aktuelleBitmaps.get(i), deTexts.get(i)));
                }
            } else {
                Log.e(this.getClass().getName(), string);
            }
        } else {
            Log.e(this.getClass().getName(), string);
        }

        return produkte;
    }

    private GeneralContent getGeneralContent(String string) {
        ArrayList<BitmapItem> bitmapItems = new ArrayList<BitmapItem>();
        ArrayList<Article> articles = new ArrayList<Article>();
        Bitmap currentBitmap = null;
        String header = "", subHeader = "", content = "";

        ArrayList<String> files = classes.getJpgFilesInDir(string, this);
        if (files != null) {
            for (int i = 0; i < files.size(); i++) {
                currentBitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + string + files.get(i));
                bitmapItems.add(new BitmapItem(files.get(i), currentBitmap));
            }
        }

        String deText = classes.getTextInDir(string + this.getString(R.string.de), this);
        String[] strings = deText.split("\n");

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].contains("<h1>") && strings[i].contains("</h1>")) {
                header = strings[i].replaceAll("\\<[^>]*>", "");
            } else if (strings[i].contains("<h2>") && strings[i].contains("</h2>")) {
                if (content.length() < 5) {
                    subHeader = strings[i].replaceAll("\\<[^>]*>", "");
                } else {
                    articles.add(new Article(subHeader, currentBitmap, content));
                    subHeader = strings[i].replaceAll("\\<[^>]*>", "");
                    content = "";
                    currentBitmap = null;
                }
            } else if (strings[i].contains("<img")) {
                for (int m = 0; m < bitmapItems.size(); m++) {
                    if (strings[i].contains(bitmapItems.get(m).getId())) {
                        currentBitmap = bitmapItems.get(m).getBitmap();
                    }
                }
            } else {
                content += "\n" + strings[i].replaceAll("\\<[^>]*>", "");
            }
        }
        articles.add(new Article(subHeader, currentBitmap, content));
        GeneralContent aktuelles = new GeneralContent(header, articles);
        return aktuelles;
    }

    private void referenceClasses() {
        app = (BischofshofApplikation) getApplication();
        app.setLanguageIsEnglish(false);
        classes = new BischofshofClasses();
        weltenburg_produkte = new ArrayList<Produkte>();
    }


    private void getLogos() {
        weltenburg_logo = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + this.getString(R.string.weltenburg_logo));
        app.setWeltenburgLogo(weltenburg_logo);
    }
}
