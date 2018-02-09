package com.example.app.loading;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.app.loading.dialog.AlertDialog;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "file";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        startService(new Intent(this, MessageService.class));
        startService(new Intent(this, GuardService.class));

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            startService(new Intent(this, JobAwakenService.class));
        }*/



    }

    public void test(View view){
        final AlertDialog dialog=new AlertDialog.Builder(this)
                .setContentView(R.layout.test_dialog_layout)
                .fromBottom(true).show();
        //dialog操作点击事件 特有操作对象listview recylerview checkbox---->dialog.getView()
        dialog.setOnClickListener(R.id.button, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setText(R.id.button,"改变了字体");
            }
        });
        //弹出软件盘网上有
    }

//       registerComponentCallbacks()
    //    unregisterComponentCallbacks() 进行解注
    ComponentCallbacks2 componentCallbacks2=new ComponentCallbacks2() {
        @Override
        public void onTrimMemory(int level) {

        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {

        }

        @Override
        public void onLowMemory() {

        }
    };


    private TextView textView;

    static class WeakReferenceHander extends Handler{
        WeakReference<MainActivity> activityWeakReference;

        public WeakReferenceHander(WeakReference<MainActivity> activityWeakReference) {
            this.activityWeakReference = activityWeakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);



        }
    }






}
