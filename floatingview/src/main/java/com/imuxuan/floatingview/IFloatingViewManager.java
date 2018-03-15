package com.imuxuan.floatingview;

import android.app.Activity;
import android.content.Context;
import android.widget.FrameLayout;

/**
 * Created by Yunpeng Li on 2018/3/15.
 */

public interface IFloatingViewManager {

    void remove();

    void add(Context applicationContext);

    void attach(Activity activity);

    void attach(FrameLayout container);

    void detach(Activity activity);

    void detach(FrameLayout container);

}
