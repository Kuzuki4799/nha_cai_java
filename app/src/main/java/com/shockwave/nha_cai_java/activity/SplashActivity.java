package com.shockwave.nha_cai_java.activity;

import android.content.Intent;
import android.os.Handler;

import com.shockwave.nha_cai_java.R;
import com.shockwave.nha_cai_java.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    protected void listener() {

    }
}
