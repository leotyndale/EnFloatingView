package com.imuxuan.floatingview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.widget.ImageView;

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
    private final ImageView mIcon;

    public EnFloatingView(@NonNull Context context) {
        super(context, null);
        inflate(context, R.layout.en_floating_view, this);
        mIcon = findViewById(R.id.icon);
    }

    public void setIconImage(@DrawableRes int resId){
        mIcon.setImageResource(resId);
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
