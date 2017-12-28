package chatqq.home.com.app2.插件化.host;

import android.app.Application;

/**
 * Created by PVer on 2017/7/7.
 */

public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Ho0kStartActitvtyUtil ho0kStartActitvtyUtil=new Ho0kStartActitvtyUtil(this,ProxyActivity.class);

        try {
            ho0kStartActitvtyUtil.hookStartActitvtyUtil();
            ho0kStartActitvtyUtil.hookLaunchActitvty();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
