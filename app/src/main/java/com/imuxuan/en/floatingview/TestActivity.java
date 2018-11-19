package com.imuxuan.en.floatingview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.imuxuan.floatingview.FloatingView;

/**
 * @ClassName TestActivity
 * @Description
 * @Author Yunpeng Li
 * @Creation 2018/3/15 下午5:01
 * @Mender Yunpeng Li
 * @Modification 2018/3/15 下午5:01
 */
public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        FloatingView.get().add();
//        FloatingView.getInstance().remove();
    }

    public void createActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, TestActivity.class);
        startActivity(intent);
    }
}
