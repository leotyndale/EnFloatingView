package com.imuxuan.en.floatingview;

import android.support.v7.app.AppCompatActivity;

import com.imuxuan.floatingview.FloatingViewManager;

/**
 * @ClassName BaseActivity
 * @Description
 * @Author Yunpeng Li
 * @Creation 2018/3/15 下午5:01
 * @Mender Yunpeng Li
 * @Modification 2018/3/15 下午5:01
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        FloatingViewManager.getInstance().attach(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FloatingViewManager.getInstance().detach(this);
    }
}
