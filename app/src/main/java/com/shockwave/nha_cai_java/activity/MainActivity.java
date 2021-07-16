package com.shockwave.nha_cai_java.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shockwave.nha_cai_java.R;
import com.shockwave.nha_cai_java.base.BaseActivity;
import com.shockwave.nha_cai_java.dialog.DialogConfirm;
import com.shockwave.nha_cai_java.model.Link;
import com.shockwave.nha_cai_java.utils.NetWorkUtils;
import com.shockwave.nha_cai_java.utils.ParamUtils;
import com.shockwave.nha_cai_java.utils.ShareUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private Button btnRegister;
    private Button btnLogin;
    private Button btnGift;
    private Button btnCSKH;
    private LinearLayout llAbout;
    private LinearLayout llCode;


    private Link link = null;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        btnGift = findViewById(R.id.btnGift);
        btnCSKH = findViewById(R.id.btnCSKH);
        llAbout = findViewById(R.id.llAbout);
        llCode = findViewById(R.id.llCode);
    }

    @Override
    protected void initData() {
        setUpDraw();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        if (NetWorkUtils.isNetworkConnected(this)) {
            database.child("link").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    link = snapshot.getValue(Link.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void listener() {
        llCode.setOnClickListener(this);
        llAbout.setOnClickListener(this);
        btnCSKH.setOnClickListener(this);
        btnGift.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    private void setUpDraw() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationIcon(R.drawable.icon_menu);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.double_tab), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void handlerAction(String url) {
        if (link != null) {
            handlerIntentWebView(url);
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }

    private void handlerIntentWebView(String url) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, WebActivity.class);
        bundle.putString(ParamUtils.URL, url);
        intent.putExtra(ParamUtils.BUNDLE_KEY, bundle);
        startActivity(intent);
    }

    private void handlerRegister() {
        if (link != null) {
            if (ShareUtils.getBoolean(MainActivity.this, ParamUtils.REGISTER, false)) {
                handlerIntentWebView(link.register);
            } else {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                intent.putExtra(ParamUtils.URL, link.register);
                startActivity(intent);
            }
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llCode:
                new DialogConfirm(MainActivity.this, Gravity.CENTER, false).show();
                drawerLayout.closeDrawers();
                break;
            case R.id.llAbout:
                openNewActivity(AboutActivity.class);
                drawerLayout.closeDrawers();
                break;
            case R.id.btnCSKH:
                if (link != null) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link.cskh)));
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnGift:
                handlerAction(link.gift);
                break;
            case R.id.btnLogin:
                handlerAction(link.login);
                break;
            case R.id.btnRegister:
                handlerRegister();
                break;
        }
    }
}