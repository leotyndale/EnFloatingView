package com.imuxuan.floatingview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
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

    private final ImageView mIcon;

    public EnFloatingView(@NonNull Context context) {
        this(context, R.layout.en_floating_view);
    }

    public EnFloatingView(@NonNull Context context, @LayoutRes int resource) {
        super(context, null);
        inflate(context, resource, this);
        mIcon = findViewById(R.id.icon);
    }

    public void setIconImage(@DrawableRes int resId){
        mIcon.setImageResource(resId);
    }

}
