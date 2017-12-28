package home.shitou.com.test.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import home.shitou.com.test.R;
import home.shitou.com.test.UserAidl;

/**
 * 应用端！！
 */
public class AidlAppActivity extends AppCompatActivity {

    //A----》》B返回一个 IBinder service
    // ----》》连接上onServiceConnected
    // ----》》 UserAidl.Stub.asInterface(service)
    //------》》 return new home.shitou.com.test.UserAidl.Stub.Proxy(obj);有了实例
    //-------》》 mCalcAidl.getUserPassword() mCalcAidl.getUserName()
    //-------》调用getUserName()过程；；；；首先调用 服务端的onTransact的方法传入数据
    //--------》

    private UserAidl mCalcAidl;

    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("client", "onServiceDisconnected");
            mCalcAidl = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("client", "onServiceConnected");
            mCalcAidl = UserAidl.Stub.asInterface(service);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_app);


    }

    /**
    * 点击BindService按钮时调用
    * @param view
    */
    public void bindService(View view) {
        Intent intent = new Intent();
        intent.setAction("com.study.aidl.user");
        // 在Android 5.0之后google出于安全的角度禁止了隐式声明Intent来启动Service.
        // 也禁止使用Intent filter.否则就会抛个异常出来
        intent.setPackage("com.study.aidl");
        bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
    }
    /**
     * 点击unBindService按钮时调用
     * @param view
     */
    public void unbindService(View view) {
        if(mCalcAidl != null){
            unbindService(mServiceConn);
        }
    }


    /**
     * 获取用户密码
     * @param view
     */
    public void getUserPassword(View view) throws Exception {
        if (mCalcAidl != null) {
            String userPassword = mCalcAidl.getUserPassword();
            Toast.makeText(this, "用户密码："+userPassword, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "服务器未绑定或被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取用户名
     * @param view
     */
    public void getUserName(View view) throws Exception {
        if (mCalcAidl != null) {
            String userName = mCalcAidl.getUserName();
            Toast.makeText(this, "用户名："+userName, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "服务器未绑定或被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT).show();
        }
    }



}
