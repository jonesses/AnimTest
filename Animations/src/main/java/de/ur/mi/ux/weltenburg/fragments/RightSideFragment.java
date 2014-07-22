package de.ur.mi.ux.weltenburg.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.animtest.animations.R;

import de.ur.mi.projektion.bischofshof.CategoryConstants;
import de.ur.mi.projektion.bischofshof.listeners.OnFragmentInteractionListener;

/**
 * Created by Jonas on 15.06.2014.
 */
public class RightSideFragment extends Fragment {
    private OnFragmentInteractionListener listener;
    private Activity parentActivity;
    private Button expandButton;
    private View v;
    private ViewGroup container;
    private LayoutInflater inflater;
    private LinearLayout layoutAktuelles;
    private ImageView parallaxScrollFirstImage;
    private String[] strings;
    private ScrollView scrollView;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.parentActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.inflater = inflater;
        this.container = container;
        v = inflater.inflate(R.layout.right_side_fragment_layout, container, false);
        initUI();


        return v;
    }

    private void initUI() {



    }




    public void setFragmentInteractionListener(OnFragmentInteractionListener listener) {
        this.listener = listener;
    }


    public void setCategoryLayout(int category) {
        switch (category) {
            case CategoryConstants.CATEGORY_AKTUELLES:
                v = inflater.inflate(R.layout.layout_aktuelles, container, false);
                break;
            case CategoryConstants.CATEGORY_AUSFLUG:
                break;
            case CategoryConstants.CATEGORY_GESCHICHTE:
                break;
            case CategoryConstants.CATEGORY_PRODUKTE:
                break;

        }

    }
}
