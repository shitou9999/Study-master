package chatqq.home.com.app2.插件化.host.plugin;

import android.content.Context;

/**
 * Created by PVer on 2017/7/8.
 */

public class PluginManager {

    public static final void install(Context context, String apkPath) {
        try {
            //解决类加载的问题
            FixDexManager fixDexManager = new FixDexManager(context);
            //把apk的class加载到ApplicationClassLoader
            fixDexManager.fixDex(apkPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
