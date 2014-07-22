package de.ur.mi.ux.weltenburg.fragments;

import android.animation.Animator;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.animtest.animations.R;
import com.makeramen.RoundedImageView;

import de.ur.mi.bischofshof.helpers.AnimationHelper;
import de.ur.mi.projektion.bischofshof.listeners.OnAnimationListener;

/**
 * Created by Jonas on 03.07.2014.
 */
public class BackgroundCircleFragment extends Fragment {

    private View v;
    private ViewGroup container;
    private Activity activity;
    private ImageView backgroundCircle;
    private ImageView shadow;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.container = container;
        v = inflater.inflate(R.layout.background_circle_fragment, container, false);



        super.onCreateView(inflater, container, savedInstanceState);
        show();
        return v;
    }

    public void show() {
        backgroundCircle = (ImageView) v.findViewById(R.id.background_circle);


        AnimationHelper.backgroundCircleShow(backgroundCircle);


    }
    public void animateNavigationChangeShadow(int color){
        ImageView shadow = (ImageView) v.findViewById(R.id.background_shadow_circle);
        AnimationHelper.animateShadowOptionSelected(shadow, color);

    }
    public void expandToFullscreen(OnAnimationListener listener){
        AnimationHelper.startTransitionToFullscreenAnimation(backgroundCircle, listener);

    }

    public void shrinkFromFullscreen(OnAnimationListener listener){
        AnimationHelper.startTransitionFromFullscreenAnimation(backgroundCircle, listener);
    }


    public void showShadow(int color) {
        shadow = (ImageView) v.findViewById(R.id.background_shadow_circle);
        AnimationHelper.animateShadowOptionSelected(shadow, color);
    }
}
