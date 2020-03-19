package com.imuxuan.floatingview;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Yunpeng Li on 2018/3/15.
 */

public interface IFloatingView {

    FloatingView remove();

    FloatingView add();

    FloatingView attach(Activity activity);

    FloatingView attach(FrameLayout container);

    FloatingView detach(Activity activity);

    FloatingView detach(FrameLayout container);

    FloatingMagnetView getView();

    FloatingView icon(@DrawableRes int resId);

    FloatingView customView(FloatingMagnetView viewGroup);

    FloatingView customView(@LayoutRes int resource);

    FloatingView layoutParams(ViewGroup.LayoutParams params);

    FloatingView listener(MagnetViewListener magnetViewListener);

}
