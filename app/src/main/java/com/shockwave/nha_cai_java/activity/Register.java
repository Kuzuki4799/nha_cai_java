package com.shockwave.nha_cai_java.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shockwave.nha_cai_java.R;
import com.shockwave.nha_cai_java.base.BaseActivity;
import com.shockwave.nha_cai_java.model.ContactsName;
import com.shockwave.nha_cai_java.utils.ParamUtils;
import com.shockwave.nha_cai_java.utils.ShareUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Register extends BaseActivity {

    private AppCompatImageView imgBack;
    private AppCompatEditText edName;
    private AppCompatEditText edPhone;
    private Button btnRegister;

    private String link;

    @Override
    protected int getLayoutId() {
        return R.layout.register;
    }

    @Override
    protected void initView() {
        imgBack = findViewById(R.id.imageBack);
        edName = findViewById(R.id.edName);
        edPhone = findViewById(R.id.edPhone);
        btnRegister = findViewById(R.id.btnRegister);
    }

    @Override
    protected void initData() {
        link = getIntent().getStringExtra(ParamUtils.URL);
    }

    @Override
    protected void listener() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edName.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, getString(R.string.empty_name), Toast.LENGTH_SHORT).show();
                } else if (edPhone.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, getString(R.string.empty_phone), Toast.LENGTH_SHORT).show();
                } else if (!isValidPhoneNumber(edPhone.getText().toString())) {
                    Toast.makeText(Register.this, getString(R.string.wrong_phone), Toast.LENGTH_SHORT).show();
                } else {
                    ContactsName contactsInfo = new ContactsName();
                    contactsInfo.name = edName.getText().toString();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    DatabaseReference memberRef = FirebaseDatabase.getInstance().getReference().child(
                            "account" + "/" + formatter.format(new Date())
                    );
                    memberRef.child(edPhone.getText().toString()).setValue(contactsInfo);
                    ShareUtils.putBoolean(Register.this, ParamUtils.REGISTER, true);
                    handlerIntentWebView(link);
                }
            }
        });
    }

    private boolean isValidPhoneNumber(CharSequence target) {
        if (target == null || target.length() < 6 || target.length() > 13) {
            return false;
        } else {
            return Patterns.PHONE.matcher(target).matches();
        }
    }

    private void handlerIntentWebView(String url) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, Web.class);
        bundle.putString(ParamUtils.URL, url);
        intent.putExtra(ParamUtils.BUNDLE_KEY, bundle);
        startActivity(intent);
    }
}
