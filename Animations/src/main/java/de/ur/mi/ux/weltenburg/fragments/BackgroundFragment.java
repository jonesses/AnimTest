package de.ur.mi.ux.weltenburg.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.animtest.animations.R;

/**
 * Created by Jonas on 17.06.2014.
 */
public class BackgroundFragment extends Fragment {

    private View v;
    private ViewGroup container;
    private Activity activity;
    private ImageView image;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.container = container;
        v = inflater.inflate(R.layout.background_fragment, container, false);
        image = (ImageView) v.findViewById(R.id.backgroundImage);


        return v;
    }





}
