package de.ur.mi.projektion.bischofshof.listeners;

import android.view.View;

/**
 * Created by Jonas on 15.06.2014.
 */
public interface OnFragmentInteractionListener {
    public void onClick(View clickedView, String side);
    public void onTransitionToFullscreenRequested(int category);
    public void onTransitionFromFullscreenRequested(int category);
    public void onAnimationStarted();
    public void onAnimationFinished();
}
