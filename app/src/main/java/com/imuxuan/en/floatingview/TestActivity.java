package com.imuxuan.en.floatingview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.imuxuan.floatingview.FloatingMagnetView;
import com.imuxuan.floatingview.FloatingView;
import com.imuxuan.floatingview.MagnetViewListener;

/**
 * @ClassName TestActivity
 * @Description
 * @Author Yunpeng Li
 * @Creation 2018/3/15 下午5:01
 * @Mender Yunpeng Li
 * @Modification 2018/3/15 下午5:01
 */
public class TestActivity extends BaseActivity {
    static int pageNum = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ((TextView) findViewById(R.id.page_num)).setText("页面" + pageNum);
        FloatingView.get().add();
        FloatingView.get().listener(new MagnetViewListener() {
            @Override
            public void onRemove(FloatingMagnetView magnetView) {
                Toast.makeText(TestActivity.this, "我没了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick(FloatingMagnetView magnetView) {
                Toast.makeText(TestActivity.this, "点到我了", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("http://blog.imuxuan.com");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
    }

    public void createActivity(View view) {
        pageNum++;
        Intent intent = new Intent();
        intent.setClass(this, TestActivity.class);
        startActivity(intent);
    }
}
