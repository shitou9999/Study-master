package com.example.app.loading.service;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.app.loading.Processconnections;

/**
 * 守护进程，进程通信
 * Processconnection.aidl ---------》建好之后clear项目一下(最好不要重命名)，再rebuild(要rebuild重新编译才行，要不然找不到包)
 * 　双进程守护和唤醒在5.0以下还是很爽的，至少能保证各种流氓的清理软件不能够清理掉我们的Service，
 * 但是在5.0以后就不管用了，尤其实在小米、魅族这些手机上面
 * Created by PVer on 2017/6/22.
 */

public class GuardService extends Service {

    private final int GuardId = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //提高进程优先级----->可以不用 new Notification()有些手机会有问题
        startForeground(GuardId,new Notification());
        //绑定建立链接
        bindService(new Intent(this,MessageService.class),mServiceConnection, Context.BIND_IMPORTANT);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Processconnections.Stub(){};
//        return new Processconnections.Stub(){
//            @Override
//            public void processConnected() throws RemoteException {
//
//            }
//        };
    }

    private ServiceConnection mServiceConnection = new  ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 建立连接
            Toast.makeText(GuardService.this, "建立连接", Toast.LENGTH_LONG).show();
        }


        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 断开连接---重新启动 重新链接
            Toast.makeText(GuardService.this, "断开连接", Toast.LENGTH_LONG).show();

            startService(new Intent(GuardService.this,MessageService.class));
            bindService(new Intent(GuardService.this,MessageService.class),mServiceConnection, Context.BIND_IMPORTANT);
        }
    };
}
