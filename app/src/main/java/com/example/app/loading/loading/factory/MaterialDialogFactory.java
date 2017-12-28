package com.example.app.loading.loading.factory;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.app.loading.R;


/**
 * Factory
 */
public class MaterialDialogFactory implements DialogFactory<ProgressDialog> {

    @Override
    public ProgressDialog onCreateDialog(Context context) {
        ProgressDialog dialog;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            dialog = new ProgressDialog(context, R.style.Dialog_AppCompat_Loading);
//        } else {
            dialog = new ProgressDialog(context);
            dialog.setProgressStyle(R.style.Widget_AppCompat_ProgressBar);
//        }
        dialog.setMessage("请稍等...");
        dialog.setCancelable(false);
        return dialog;
    }

    @Override
    public void setMessage(ProgressDialog dialog, CharSequence message) {
        dialog.setMessage(message);
    }

    @Override
    public int getAnimateStyleId() {
        return 0;
    }
}
