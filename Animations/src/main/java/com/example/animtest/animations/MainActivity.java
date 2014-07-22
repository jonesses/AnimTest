package com.example.animtest.animations;

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

import com.example.animtest.raphael.Article;
import com.example.animtest.raphael.BischofshofClasses;
import com.example.animtest.raphael.BitmapItem;
import com.example.animtest.raphael.GeneralContent;
import com.example.animtest.raphael.Geschichte;
import com.example.animtest.raphael.Logos;
import com.example.animtest.raphael.Produkte;
import com.example.animtest.raphael.StringItem;

import java.util.ArrayList;

import de.ur.mi.projektion.bischofshof.CategoryConstants;
import de.ur.mi.projektion.bischofshof.listeners.OnAnimationListener;
import de.ur.mi.projektion.bischofshof.listeners.OnCategorySelectedListener;
import de.ur.mi.projektion.bischofshof.listeners.OnFragmentInteractionListener;
import de.ur.mi.ux.weltenburg.fragments.AktuellesFragment;
import de.ur.mi.ux.weltenburg.fragments.AusflugAutoFragment;
import de.ur.mi.ux.weltenburg.fragments.AusflugFragment;
import de.ur.mi.ux.weltenburg.fragments.AusflugFussFragment;
import de.ur.mi.ux.weltenburg.fragments.AusflugRadFragment;
import de.ur.mi.ux.weltenburg.fragments.AusflugSchiffFragment;
import de.ur.mi.ux.weltenburg.fragments.BackgroundCircleFragment;
import de.ur.mi.ux.weltenburg.fragments.BackgroundFragment;
import de.ur.mi.ux.weltenburg.fragments.BischofshofApplikation;
import de.ur.mi.ux.weltenburg.fragments.GeschichteAllgemeinesFragment;
import de.ur.mi.ux.weltenburg.fragments.LeftSideFragment;
import de.ur.mi.ux.weltenburg.fragments.ProdukteAllgemeinesFragment;
import de.ur.mi.ux.weltenburg.fragments.RightSideFragment;
import de.ur.mi.ux.weltenburg.fragments.StartScreenFragment;
import de.ur.mi.ux.weltenburg.fragments.WeltenburgGeschichteFragment;
import de.ur.mi.ux.weltenburg.fragments.WeltenburgProdukteFragment;


public class MainActivity extends FragmentActivity implements OnFragmentInteractionListener, OnCategorySelectedListener, OnAnimationListener {


    private android.support.v4.app.FragmentManager supportFragmentManager;
    private FragmentManager fragmentManager;
    //NEU
    private BischofshofApplikation app;
    private BischofshofClasses classes;
    private Bitmap weltenburg_logo;
    private GeneralContent weltenburg_aktuelles, weltenburg_geschichte_allgemeines, weltenburg_produkte_allgemeines, weltenburg_route;
    private ArrayList<Geschichte> weltenburg_geschichte;
    private ArrayList<Produkte> weltenburg_produkte;
    private ArrayList<Logos> weltenburg_aufNachWeltenburg_logos;
    //Fragments
    private BackgroundFragment background;
    private StartScreenFragment startScreen;
    private LeftSideFragment leftSide;
    private RightSideFragment rightSide;
    private AktuellesFragment aktuellesFragment;
    private AusflugAutoFragment ausflugAutoFragment;
    private AusflugFussFragment ausflugFussFragment;
    private AusflugRadFragment ausflugRadFragment;
    private AusflugSchiffFragment ausflugSchiffFragment;
    private ProdukteAllgemeinesFragment produkteAllgemeinesFragment;
    private GeschichteAllgemeinesFragment geschichteAllgemeinesFragment;
    private BackgroundCircleFragment backgroundCircle;
    private AusflugFragment ausflugFragment;
    private WeltenburgGeschichteFragment geschichteFragment;
    private WeltenburgProdukteFragment produkteFragment;


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
        initiateFragments();
        setListeners();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.frame, background)
                .add(R.id.frame, leftSide)
                .add(R.id.frame, backgroundCircle)
                .add(R.id.frame, ausflugFragment).hide(ausflugFragment)
                .add(R.id.frame, geschichteAllgemeinesFragment).hide(geschichteAllgemeinesFragment)
                .add(R.id.frame, produkteAllgemeinesFragment).hide(produkteAllgemeinesFragment)
                .add(R.id.frame, aktuellesFragment)
                .add(R.id.frame, produkteFragment).hide(produkteFragment)
                .add(R.id.frame, startScreen)
                .commit();

        supportFragmentManager.beginTransaction().add(R.id.frame, geschichteFragment).hide(geschichteFragment).commit();


    }

    private void setListeners() {
        leftSide.setFragmentInteractionListener(this);
        rightSide.setFragmentInteractionListener(this);
        leftSide.setCategorySelectedListener(this);
        startScreen.setFragmentInteractionListener(this);
        geschichteAllgemeinesFragment.setOnFragmentInteractionListener(this);
        geschichteFragment.setFragmentInteractionListener(this);
        produkteFragment.setFragmentInteractionListener(this);
    }

    private void initiateFragments() {
        background = new BackgroundFragment();
        startScreen = new StartScreenFragment();
        leftSide = new LeftSideFragment();
        rightSide = new RightSideFragment();
        backgroundCircle = new BackgroundCircleFragment();

        aktuellesFragment = new AktuellesFragment();
        produkteFragment = new WeltenburgProdukteFragment();
        ausflugAutoFragment = new AusflugAutoFragment();
        ausflugFussFragment = new AusflugFussFragment();
        ausflugRadFragment = new AusflugRadFragment();
        ausflugSchiffFragment = new AusflugSchiffFragment();
        geschichteAllgemeinesFragment = new GeschichteAllgemeinesFragment();
        produkteAllgemeinesFragment = new ProdukteAllgemeinesFragment();
        ausflugFragment = new AusflugFragment();
        geschichteFragment = new WeltenburgGeschichteFragment();
    }


    @Override
    public void onClick(View clickedView, String side) {

    }

    @Override
    public void onTransitionToFullscreenRequested() {
        backgroundCircle.expandToFullscreen(this);
        FragmentTransaction fullscreenTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fullscreenTransaction.hide(leftSide)
                .hide(rightSide)
                .hide(geschichteAllgemeinesFragment)
                .commit();
    }

    @Override
    public void onTransitionFromFullscreenRequested() {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in_support, R.anim.fade_out_support)
                .hide(geschichteFragment)
                .commit();

        FragmentTransaction fullscreenTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fullscreenTransaction.show(leftSide)
                .show(rightSide)
                .show(geschichteAllgemeinesFragment)
                .commit();

        backgroundCircle.shrinkFromFullscreen(this);
    }

    @Override
    public void onAnimationStarted() {


    }


    @Override
    public void onAnimationFinished() {
        hideStartscreenFragment();
    }

    @Override
    public void onCategorySelected(int color) {
        backgroundCircle.animateNavigationChangeShadow(color);
    }


    @Override
    public void onCategoryChangeRequested(int category) {
        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .hide(ausflugFussFragment)
                .hide(ausflugSchiffFragment)
                .hide(ausflugRadFragment)
                .hide(ausflugFragment)
                .hide(geschichteAllgemeinesFragment)
                .hide(produkteAllgemeinesFragment)
                .hide(aktuellesFragment);

        switch (category) {
            case CategoryConstants.CATEGORY_AKTUELLES:
                transaction
                        .show(aktuellesFragment)
                        .commit();
                break;
            case CategoryConstants.CATEGORY_GESCHICHTE:
                transaction
                        .show(geschichteAllgemeinesFragment)
                        .commit();
                break;
            case CategoryConstants.CATEGORY_PRODUKTE:
                transaction
                        .show(produkteAllgemeinesFragment)
                        .commit();
                break;
            case CategoryConstants.CATEGORY_AUSFLUG:
                transaction
                        .show(ausflugFragment)
                        .commit();
                break;
        }
    }


    private void hideStartscreenFragment() {
        fragmentManager
                .beginTransaction()
                .hide(startScreen)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

                .commit();
    }

    private void getWeltenburgData() {
        //Aktuelles
        weltenburg_aktuelles = getGeneralContent(getString(R.string.weltenburg_aktuelles));
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
        ArrayList<BitmapItem> bitmapItems;
        ArrayList<String> deTexts = new ArrayList<String>();

        bitmapItems = classes.getProdukte(string, this);
        deTexts = classes.getTextFiles(string + this.getString(R.string.de), this);


        if (deTexts != null) {
            if (bitmapItems.size() == deTexts.size()) {
                for (int i = 0; i < bitmapItems.size(); i++) {
                    produkte.add(new Produkte(bitmapItems.get(i).getBitmap(), deTexts.get(i), bitmapItems.get(i).getId()));
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

    @Override
    public void onToFullscreenStarted() {

    }

    @Override
    public void onToFullscreenFinished() {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .show(produkteFragment)
                .commit();
    }

    @Override
    public void onFromFullscreenStarted() {

    }

    @Override
    public void onFromFullscreenFinished() {

    }
}
