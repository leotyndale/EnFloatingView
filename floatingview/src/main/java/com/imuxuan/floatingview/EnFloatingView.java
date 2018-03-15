package com.imuxuan.floatingview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

/**
 * @ClassName EnFloatingView
 * @Description 悬浮窗
 * @Author Yunpeng Li
 * @Creation 2018/3/15 下午5:04
 * @Mender Yunpeng Li
 * @Modification 2018/3/15 下午5:04
 */
public class EnFloatingView extends FloatingMagnetView {

    private long mLastTouchDownTime;
    private static final int TOUCH_TIME_THRESHOLD = 150;

    public EnFloatingView(@NonNull Context context) {
        super(context, null);
        inflate(context, R.layout.en_floating_view, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (event != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mLastTouchDownTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_UP:
                    if (isOnClickEvent()) {
                        dealClickEvent();
                    }
                    break;
            }
        }
        return true;
    }

    protected boolean isOnClickEvent() {
        return System.currentTimeMillis() - mLastTouchDownTime < TOUCH_TIME_THRESHOLD;
    }

}
