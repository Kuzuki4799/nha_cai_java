package com.shockwave.nha_cai_java.dialog;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;

import com.shockwave.nha_cai_java.R;
import com.shockwave.nha_cai_java.base.BaseDialog;

import static android.content.Context.CLIPBOARD_SERVICE;

public class DialogConfirm extends BaseDialog {

    public Activity activity;

    private AppCompatTextView txtCode;
    private Button txtSave;

    public DialogConfirm(Activity context, int gravity, boolean isCancel) {
        super(context, gravity, isCancel);
        activity = context;
        init();
    }

    private void init() {
        txtCode = (AppCompatTextView) dialogPlus.findViewById(R.id.txtCode);
        txtSave = (Button) dialogPlus.findViewById(R.id.txtSave);

        String andId = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
        txtCode.setText(andId);

        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager manager = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", andId);
                manager.setPrimaryClip(clipData);
                Toast.makeText(activity, activity.getString(R.string.copy_success), Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_confirm;
    }
}
