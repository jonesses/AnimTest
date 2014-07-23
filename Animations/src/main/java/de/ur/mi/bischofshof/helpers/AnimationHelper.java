package de.ur.mi.bischofshof.helpers;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.example.animtest.animations.R;

import de.ur.mi.projektion.bischofshof.AnimationConstants;
import de.ur.mi.projektion.bischofshof.listeners.OnAnimationListener;

/**
 * Created by Jonas on 14.06.2014.
 */
public class AnimationHelper {

    public static void backgroundCircleShow(ImageView backgroundCircle) {
        backgroundCircle.setScaleX(3f);
        backgroundCircle.setScaleY(3f);
        backgroundCircle.setTranslationX(600);
        ObjectAnimator backgroundFade = ObjectAnimator.ofFloat(backgroundCircle, "alpha", 0f, 1f);
        backgroundFade.setDuration(AnimationConstants.ANIMATION_DURATION * 5);
        backgroundFade.setStartDelay(AnimationConstants.ANIMATION_DURATION * 7);
        backgroundFade.start();
    }


    public static void animateShadowOptionSelected(ImageView shadow, int color) {

        shadow.setTranslationX(600);
        Drawable shadowDrawable = shadow.getBackground();
        shadowDrawable.setColorFilter(color, PorterDuff.Mode.ADD);

        ObjectAnimator shadowFadeIn = ObjectAnimator.ofFloat(shadow, "alpha", 0f, 0.5f);
        ObjectAnimator shadowFadeOut = ObjectAnimator.ofFloat(shadow, "alpha", 0f);
        shadowFadeIn.setDuration(AnimationConstants.ANIMATION_DURATION * 2);
        shadowFadeOut.setDuration(AnimationConstants.ANIMATION_DURATION * 10);

        ObjectAnimator shadowScaleOutX = ObjectAnimator.ofFloat(shadow, "scaleX", 3.5f, 3f);
        ObjectAnimator shadowScaleOutY = ObjectAnimator.ofFloat(shadow, "scaleY", 3.5f, 3f);
        shadowScaleOutX.setDuration(AnimationConstants.ANIMATION_DURATION * 4);
        shadowScaleOutY.setDuration(AnimationConstants.ANIMATION_DURATION * 4);

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new DecelerateInterpolator());
        set.play(shadowFadeIn);
        set.play(shadowScaleOutX);
        set.play(shadowScaleOutY);
        set.play(shadowFadeOut).after(shadowFadeIn);
        set.start();

    }


    public static void startNavigationSelectedAnimation(View view, int color) {

        View background = view.findViewById(R.id.navigation_list_background);

        background.setBackgroundColor(color);
        ObjectAnimator backgroundFadeIn = ObjectAnimator.ofFloat(background, "alpha", 0f, 0.2f, 0.8f);
        backgroundFadeIn.setDuration(AnimationConstants.ANIMATION_DURATION * 4);
        backgroundFadeIn.setInterpolator(new DecelerateInterpolator());
        backgroundFadeIn.setAutoCancel(true);
        backgroundFadeIn.start();

    }

    public static void startNavigationDeselectedAnimation(View view) {
        View background = view.findViewById(R.id.navigation_list_background);

        ObjectAnimator backgroundFadeOut = ObjectAnimator.ofFloat(background, "alpha", 0f);
        backgroundFadeOut.setDuration(AnimationConstants.ANIMATION_DURATION * 2);
        backgroundFadeOut.setInterpolator(new DecelerateInterpolator());
        backgroundFadeOut.start();
    }

    public static void startTransitionToFullscreenAnimation(ImageView backgroundCircle, final OnAnimationListener animatorListener, final int category) {
        //ObjectAnimator backgroundMove = ObjectAnimator.ofFloat(backgroundCircle, "translationX", 0f);
        ObjectAnimator backgroundScaleX = ObjectAnimator.ofFloat(backgroundCircle, "scaleX", 5.7f);
        ObjectAnimator backgroundScaleY = ObjectAnimator.ofFloat(backgroundCircle, "scaleY", 5.7f);
        //  ObjectAnimator backgroundFade = ObjectAnimator.ofFloat(backgroundCircle, "alpha", 0.2f);
        //  backgroundFade.setDuration(AnimationConstants.ANIMATION_DURATION * 4);
        backgroundScaleX.setDuration(AnimationConstants.ANIMATION_DURATION * 4);
        backgroundScaleY.setDuration(AnimationConstants.ANIMATION_DURATION * 4);
        // backgroundMove.setDuration(AnimationConstants.ANIMATION_DURATION * 4);

//        backgroundFade.setStartDelay(AnimationConstants.ANIMATION_DURATION * 4);
//        backgroundScaleX.setStartDelay(AnimationConstants.ANIMATION_DURATION * 3);
//        backgroundScaleY.setStartDelay(AnimationConstants.ANIMATION_DURATION * 3);

        // backgroundMove.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet set = new AnimatorSet();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                animatorListener.onToFullscreenStarted();
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorListener.onToFullscreenFinished(category);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.playTogether(backgroundScaleX, backgroundScaleY);
        set.start();


    }

    public static void startTransitionFromFullscreenAnimation(ImageView backgroundCircle, final OnAnimationListener animatorListener, final int category) {

        // ObjectAnimator backgroundMove = ObjectAnimator.ofFloat(backgroundCircle, "translationX", 600f);
        ObjectAnimator backgroundScaleX = ObjectAnimator.ofFloat(backgroundCircle, "scaleX", 3f);
        ObjectAnimator backgroundScaleY = ObjectAnimator.ofFloat(backgroundCircle, "scaleY", 3f);
        // ObjectAnimator backgroundFade = ObjectAnimator.ofFloat(backgroundCircle, "alpha", 1f);
        //  backgroundFade.setDuration(AnimationConstants.ANIMATION_DURATION * 5);
        backgroundScaleX.setDuration(AnimationConstants.ANIMATION_DURATION * 5);
        backgroundScaleY.setDuration(AnimationConstants.ANIMATION_DURATION * 5);
        //backgroundMove.setDuration(AnimationConstants.ANIMATION_DURATION * 5);

        //backgroundMove.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet set = new AnimatorSet();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                animatorListener.onFromFullscreenStarted();
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorListener.onFromFullscreenFinished(category);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.playTogether(backgroundScaleX, backgroundScaleY);
        set.start();


    }

    public static void dimDialogBackground(View dimmerbackground) {
        ObjectAnimator backgroundDim = ObjectAnimator.ofFloat(dimmerbackground, "alpha", 0.7f);
        backgroundDim.setDuration(800);
        backgroundDim.setInterpolator(new DecelerateInterpolator());
        backgroundDim.start();

    }

    public static void removeDialogBackground(View dimmerbackground) {
        ObjectAnimator backgroundDim = ObjectAnimator.ofFloat(dimmerbackground, "alpha", 0f);
        backgroundDim.setDuration(400);
        backgroundDim.setInterpolator(new DecelerateInterpolator());
        backgroundDim.start();
    }
}
