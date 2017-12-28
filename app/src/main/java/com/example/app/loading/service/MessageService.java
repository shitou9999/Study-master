package com.example.app.loading.service;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.app.loading.Processconnections;

/**
 * Created by PVer on 2017/6/22.
 */

public class MessageService extends Service {
    private final int MessageId = 1;
    private static final String TAG= "MessageService";
//    private MessageServiceConnection mServiceConnection;
//    private MessageBind mMessageBind;
    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Log.e(TAG, "run:等待接收消息 ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

//        if (mServiceConnection == null) {
//            mServiceConnection = new MessageServiceConnection();
//        }

//        if (mMessageBind == null) {
//            mMessageBind = new MessageBind();
//        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //提高进程优先级----->可以不用 new Notification()有些手机会有问题
        startForeground(MessageId,new Notification());
        //绑定建立链接
        bindService(new Intent(this,GuardService.class),mServiceConnection, Context.BIND_IMPORTANT);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Processconnections.Stub(){};
//        return mMessageBind ;
    }

//    private class MessageBind extends Processconnections.Stub {
//        @Override
//        public void processConnected() throws RemoteException {
//
//        }
//    }


    private ServiceConnection mServiceConnection = new  ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 建立连接
            Toast.makeText(MessageService.this, "建立连接", Toast.LENGTH_LONG).show();
        }


        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 断开连接-----重新启动 重新链接
            Toast.makeText(MessageService.this, "断开连接", Toast.LENGTH_LONG).show();

            startService(new Intent(MessageService.this,GuardService.class));
            bindService(new Intent(MessageService.this,GuardService.class),mServiceConnection, Context.BIND_IMPORTANT);
        }
    };




}
