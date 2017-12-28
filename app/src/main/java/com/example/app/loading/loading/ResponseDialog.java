package com.example.app.loading.loading;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

import com.example.app.loading.loading.dialog.LoadingDialog;


/**
 * Created by Administrator on 2017/6/7.
 */
public class ResponseDialog {

    public static void showLoading(Context context) {
        showLoading(context,"请稍后...",true);
    }

    public static void closeLoading() {
        LoadingDialog.cancel();
    }


    public static void showLoading(final Context context, String edMessage, boolean isCancelable) {
        Dialog dialog = LoadingDialog.make(context, new CustomDialogFactory())
                .setMessage(edMessage.toString())//提示消息
                .setCancelable(isCancelable)
                .create();
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadingDialog.cancel();
            }
        }, 60 * 1000);

    }



}
