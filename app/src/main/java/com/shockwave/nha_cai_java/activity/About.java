package com.shockwave.nha_cai_java.activity;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import com.shockwave.nha_cai_java.R;
import com.shockwave.nha_cai_java.base.BaseActivity;

public class About extends BaseActivity {

    private AppCompatImageView imgBack;

    @Override
    protected int getLayoutId() {
        return R.layout.about;
    }

    @Override
    protected void initView() {
        imgBack = findViewById(R.id.imageBack);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void listener() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
