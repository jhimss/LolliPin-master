package com.github.orangegangsters.lollipin.lib.interfaces;

import android.app.Activity;

/**
 * Created by stoyan on 1/12/15.
 * Implemented by {@link com.github.orangegangsters.lollipin.lib.managers} in order to
 * determine when the app was launched for the last time and when to launch the
 */
public interface LifeCycleInterface {

    /**
     * Called in {@link Activity#onResume()}
     */
    public void onActivityResumed(Activity activity);

    /**
     * Called in {@link Activity#onPause()}
     */
    public void onActivityPaused(Activity activity);
}
