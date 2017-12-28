package chatqq.home.com.app2.单例;

import android.content.Context;

/**
 * 基础的单例
 * 单例对象的类只能允许一个实例存在
 * Created by Administrator on 2017/6/30 0030.
 */

public class CommonUtil {

    private Context context;
    //是静态变量 ---->整个app周期存在  持有了传入的Activity，当Activity调用onDestroy()销毁时（例如屏幕旋转时，Activity会重建）
    private static CommonUtil mInstance;

    public CommonUtil(Context context) {
        this.context = context.getApplicationContext();
//        SparseArray
//        ArrayMap
    }

    public static CommonUtil getInstance(Context context) {
        if (mInstance==null){
            synchronized (CommonUtil.class){
                if (mInstance == null) {
                    mInstance = new CommonUtil(context);
                }
            }
        }
        return mInstance;
    }


}
