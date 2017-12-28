
package chatqq.home.com.app2.插件化.host;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import chatqq.home.com.app2.R;

/**
 * 没有注册 一般都是Activity但是AppCompatActivity兼容了
 *  Caused by: java.lang.IllegalArgumentException:
 *  android.content.pm.PackageManager$NameNotFoundException:
 *  ComponentInfo{home.shitou.com.hostapp/home.shitou.com.hostapp.TestActivity}
 * at android.support.v4.app.NavUtils.getParentActivityName(NavUtils.java:284)
 */
public class TestActivity extends AppCompatActivity {

    //一般都是Activity
    //AppCompatActivity 有写会泵

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    //1.继承自AppCompatActivity绕不过的问题

/*
    public static IPackageManager getPackageManager() {
         //第一次获取IPackageManager先做一次判断，sPackageManager是不是等于空
        if (sPackageManager != null) {
            //Slog.v("PackageManager", "returning cur default = " + sPackageManager);
            return sPackageManager;
        }
        IBinder b = ServiceManager.getService("package");
        //Slog.v("PackageManager", "default service binder = " + b);
        sPackageManager = IPackageManager.Stub.asInterface(b);
        //Slog.v("PackageManager", "default service = " + sPackageManager);
        return sPackageManager;
    }
    静态方法每一次我都先调用一次方法，sPackageManager就会有实例，那么我再次进来获取的就是已经实例好的sPackageManager
    */



}
