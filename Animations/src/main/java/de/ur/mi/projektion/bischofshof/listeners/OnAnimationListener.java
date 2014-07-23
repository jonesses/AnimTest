package de.ur.mi.projektion.bischofshof.listeners;

/**
 * Created by Jonas on 21.07.2014.
 */
public interface OnAnimationListener {
    public void onToFullscreenStarted();
    public void onToFullscreenFinished(int category);
    public void onFromFullscreenStarted();
    public void onFromFullscreenFinished(int category);

}
