package com.example.app.loading.loading.dialog;

import android.app.Dialog;

import com.example.app.loading.loading.ILoading;


/**
 * Created by Administrator on 2017/6/7.
 */
public interface ILoadingDialog extends ILoading {

    Dialog create();

    ILoadingDialog setCancelable(boolean flag);

    ILoadingDialog setMessage(CharSequence message);


}
