package com.imuxuan.floatingview;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.imuxuan.floatingview.utils.EnContext;

import java.lang.ref.WeakReference;


/**
 * @ClassName FloatingView
 * @Description 悬浮窗管理器
 * @Author Yunpeng Li
 * @Creation 2018/3/15 下午5:05
 * @Mender Yunpeng Li
 * @Modification 2018/3/15 下午5:05
 */
public class FloatingView implements IFloatingView {

    private FloatingMagnetView mEnFloatingView;
    private static volatile FloatingView mInstance;
    private WeakReference<FrameLayout> mContainer;
    @LayoutRes
    private int mLayoutId = R.layout.en_floating_view;
    @DrawableRes
    private int mIconRes = R.drawable.imuxuan;
    private ViewGroup.LayoutParams mLayoutParams = getParams();

    private FloatingView() {
    }

    public static FloatingView get() {
        if (mInstance == null) {
            synchronized (FloatingView.class) {
                if (mInstance == null) {
                    mInstance = new FloatingView();
                }
            }
        }
        return mInstance;
    }

    @Override
    public FloatingView remove() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (mEnFloatingView == null) {
                    return;
                }
                if (ViewCompat.isAttachedToWindow(mEnFloatingView) && getContainer() != null) {
                    getContainer().removeView(mEnFloatingView);
                }
                mEnFloatingView = null;
            }
        });
        return this;
    }

    private void ensureFloatingView() {
        synchronized (this) {
            if (mEnFloatingView != null) {
                return;
            }
            EnFloatingView enFloatingView = new EnFloatingView(EnContext.get(), mLayoutId);
            mEnFloatingView = enFloatingView;
            enFloatingView.setLayoutParams(mLayoutParams);
            enFloatingView.setIconImage(mIconRes);
            addViewToWindow(enFloatingView);
        }
    }

    @Override
    public FloatingView add() {
        ensureFloatingView();
        return this;
    }

    @Override
    public FloatingView attach(Activity activity) {
        attach(getActivityRoot(activity));
        return this;
    }

    @Override
    public FloatingView attach(FrameLayout container) {
        if (container == null || mEnFloatingView == null) {
            mContainer = new WeakReference<>(container);
            return this;
        }
        if (mEnFloatingView.getParent() == container) {
            return this;
        }
        if (getContainer() != null && mEnFloatingView.getParent() == getContainer()) {
            getContainer().removeView(mEnFloatingView);
        }
        mContainer = new WeakReference<>(container);
        container.addView(mEnFloatingView);
        return this;
    }

    @Override
    public FloatingView detach(Activity activity) {
        detach(getActivityRoot(activity));
        return this;
    }

    @Override
    public FloatingView detach(FrameLayout container) {
        if (mEnFloatingView != null && container != null && ViewCompat.isAttachedToWindow(mEnFloatingView)) {
            container.removeView(mEnFloatingView);
        }
        if (getContainer() == container) {
            mContainer = null;
        }
        return this;
    }

    @Override
    public FloatingMagnetView getView() {
        return mEnFloatingView;
    }

    @Override
    public FloatingView icon(@DrawableRes int resId) {
        mIconRes = resId;
        return this;
    }

    @Override
    public FloatingView customView(FloatingMagnetView viewGroup) {
        mEnFloatingView = viewGroup;
        return this;
    }

    @Override
    public FloatingView customView(@LayoutRes int resource) {
        mLayoutId = resource;
        return this;
    }

    @Override
    public FloatingView layoutParams(ViewGroup.LayoutParams params) {
        mLayoutParams = params;
        if (mEnFloatingView != null) {
            mEnFloatingView.setLayoutParams(params);
        }
        return this;
    }

    @Override
    public FloatingView listener(MagnetViewListener magnetViewListener) {
        if (mEnFloatingView != null) {
            mEnFloatingView.setMagnetViewListener(magnetViewListener);
        }
        return this;
    }

    private void addViewToWindow(final View view) {
        if (getContainer() == null) {
            return;
        }
        getContainer().addView(view);
    }

    private FrameLayout getContainer() {
        if (mContainer == null) {
            return null;
        }
        return mContainer.get();
    }

    private FrameLayout.LayoutParams getParams() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.START;
        params.setMargins(13, params.topMargin, params.rightMargin, 500);
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