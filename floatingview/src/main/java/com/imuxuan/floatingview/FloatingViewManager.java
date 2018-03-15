package com.imuxuan.floatingview;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


/**
 * @ClassName FloatingViewManager
 * @Description 悬浮窗管理器
 * @Author Yunpeng Li
 * @Creation 2018/3/15 下午5:05
 * @Mender Yunpeng Li
 * @Modification 2018/3/15 下午5:05
 */
public class FloatingViewManager implements IFloatingViewManager {

    private EnFloatingView mEnFloatingView;
    private static volatile FloatingViewManager mInstance;
    private FrameLayout mContainer;

    private FloatingViewManager() {
    }

    public static FloatingViewManager getInstance() {
        if (mInstance == null) {
            synchronized (FloatingViewManager.class) {
                if (mInstance == null) {
                    mInstance = new FloatingViewManager();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void remove() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (mEnFloatingView == null) {
                    return;
                }
                if (ViewCompat.isAttachedToWindow(mEnFloatingView) && mContainer != null) {
                    mContainer.removeView(mEnFloatingView);
                }
                mEnFloatingView = null;
            }
        });
    }

    private void ensureMiniPlayer(Context context) {
        synchronized (this) {
            if (mEnFloatingView != null) {
                return;
            }
            mEnFloatingView = new EnFloatingView(context.getApplicationContext());
            mEnFloatingView.setLayoutParams(getParams());
            addViewToWindow(mEnFloatingView);
        }
    }

    @Override
    public void add(Context context) {
        ensureMiniPlayer(context);
    }

    @Override
    public void attach(Activity activity) {
        attach(getActivityRoot(activity));
    }

    @Override
    public void attach(FrameLayout container) {
        if (container == null || mEnFloatingView == null) {
            mContainer = container;
            return;
        }
        if (mEnFloatingView.getParent() == container) {
            return;
        }
        if (mContainer != null && mEnFloatingView.getParent() == mContainer) {
            mContainer.removeView(mEnFloatingView);
        }
        mContainer = container;
        container.addView(mEnFloatingView);
    }

    @Override
    public void detach(Activity activity) {
        detach(getActivityRoot(activity));
    }

    @Override
    public void detach(FrameLayout container) {
        if (mEnFloatingView != null && container != null && ViewCompat.isAttachedToWindow(mEnFloatingView)) {
            container.removeView(mEnFloatingView);
        }
        if (mContainer == container) {
            mContainer = null;
        }
    }

    public EnFloatingView getFloatingView() {
        return mEnFloatingView;
    }

    private void addViewToWindow(final EnFloatingView view) {
        if (mContainer == null) {
            return;
        }
        mContainer.addView(view);
    }

    private FrameLayout.LayoutParams getParams() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.START;
        params.setMargins(13, params.topMargin, params.rightMargin, 56);
        return params;
    }

    private FrameLayout getActivityRoot(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            return (FrameLayout) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}