package de.ur.mi.ux.weltenburg.fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.animtest.animations.R;
import com.makeramen.RoundedImageView;

import de.ur.mi.projektion.bischofshof.AnimationConstants;
import de.ur.mi.projektion.bischofshof.listeners.OnFragmentInteractionListener;

import static android.view.View.OnClickListener;

/**
 * Created by Jonas on 14.06.2014.
 */
public class StartScreenFragment extends Fragment implements Animator.AnimatorListener {

    private OnFragmentInteractionListener listener;
    private RoundedImageView deckel;
    private View background;
    private Activity parentActivity;
    private TextView welcome;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.parentActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.start_deckel, container, false);
        initUI(v);
        setListeners();
        return v;
    }

    private void initUI(View v) {
        deckel = (RoundedImageView) v.findViewById(R.id.start_deckel);
        background = v.findViewById(R.id.start_background);
       // welcome = (TextView) v.findViewById(R.id.welcome_textview);
    }

    private void setListeners() {
        deckel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setClickable(false);
                listener.onClick(view, "start");
                startRemoveAnimation();
            }
        });
    }

    public void startRemoveAnimation() {


        deckel.setPivotX(0);
        deckel.setPivotY(deckel.getWidth() / 2);
        ObjectAnimator deckelScaleX = ObjectAnimator.ofFloat(deckel, "scaleX", 1f, 3f);
        ObjectAnimator deckelScaleY = ObjectAnimator.ofFloat(deckel, "scaleY", 1f, 3f);
        deckelScaleX.setDuration(AnimationConstants.ANIMATION_DURATION * 5);
        deckelScaleY.setDuration(AnimationConstants.ANIMATION_DURATION * 5);

//        ObjectAnimator welcomeTextFade = ObjectAnimator.ofFloat(welcome, "alpha", 0f);
//        welcomeTextFade.setDuration(AnimationConstants.ANIMATION_DURATION * 2);

        ObjectAnimator backgroundAnim = ObjectAnimator.ofFloat(background, "alpha", 0f);
        backgroundAnim.setDuration(AnimationConstants.ANIMATION_DURATION * 6);
        ObjectAnimator deckelFade = ObjectAnimator.ofFloat(deckel, "alpha", 0f);
        deckelFade.setDuration(AnimationConstants.ANIMATION_DURATION * 8);
        deckelFade.setStartDelay(AnimationConstants.ANIMATION_DURATION * 6);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.play(deckelScaleX).with(deckelScaleY);
        animatorSet.play(backgroundAnim).after(deckelScaleX);
        animatorSet.play(deckelFade);
       // animatorSet.play(welcomeTextFade);
        animatorSet.addListener(this);
        animatorSet.start();
    }

    public void setFragmentInteractionListener(OnFragmentInteractionListener listener) {
        this.listener = listener;
    }


    @Override
    public void onAnimationStart(Animator animator) {
        listener.onAnimationStarted();

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        listener.onAnimationFinished();


    }

    @Override
    public void onAnimationCancel(Animator animator) {
    }

    @Override
    public void onAnimationRepeat(Animator animator) {
    }

}
