package com.shockwave.nha_cai_java.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.shockwave.nha_cai_java.R;

public abstract class BaseDialog {
    private Context context;
    private int gravity;
    private boolean isCancel;
    public DialogPlus dialogPlus;

    public abstract int getLayoutId();

    public BaseDialog(Context context, int gravity, boolean isCancel) {
        this.context = context;
        this.gravity = gravity;
        this.isCancel = isCancel;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(getLayoutId(), null);
        dialogPlus = DialogPlus.newDialog(context)
                .setGravity(gravity)
                .setCancelable(isCancel)
                .setContentBackgroundResource(R.color.transparent)
                .setContentHolder(new ViewHolder(view))
                .create();
    }

    public void show() {
        dialogPlus.show();
    }

    public void dismiss() {
        dialogPlus.dismiss();
    }
}
