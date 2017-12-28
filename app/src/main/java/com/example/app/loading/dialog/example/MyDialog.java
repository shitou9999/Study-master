package com.example.app.loading.dialog.example;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.example.app.loading.R;

/**
 * 如果逻辑代码多的话可以这样用
 * Created by Administrator on 2017/6/26 0026.
 */
public class MyDialog extends Dialog {
    private Context context;

    public MyDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_dialog_layout);
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        // findViewById()可以处理复杂的东西

//        MyDialog myDialog=new MyDialog();
//        myDialog.show();
          //dissmiss之前判断是否为空
    }
}