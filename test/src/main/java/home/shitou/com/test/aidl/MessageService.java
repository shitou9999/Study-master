package home.shitou.com.test.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import home.shitou.com.test.UserAidl;

/**
 * 应用服务端
 */

public class MessageService extends Service {

    private static final String TAG = "server";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    // 应用间通信进行绑定
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBind;
    }

    // 应用间解绑
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    // mBinder 的实例
    private final UserAidl.Stub mBind = new UserAidl.Stub() {
        @Override
        public String getUserName() throws RemoteException {
            return "shitou.com";
        }

        @Override
        public String getUserPassword() throws RemoteException {
            return "0123456";
        }
    };

}























