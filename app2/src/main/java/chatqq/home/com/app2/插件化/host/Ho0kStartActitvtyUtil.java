package chatqq.home.com.app2.插件化.host;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by PVer on 2017/7/7.
 */

public class Ho0kStartActitvtyUtil {

    private static final String TAG = "Ho0kStartActitvtyUtil";
    private static final String EXTRA_ORIGIN_INTENT = "EXTRA_ORIGIN_INTENT";

    private Context mContext;
    private Class<?> mProxyClass;

    public Ho0kStartActitvtyUtil(Context mContext, Class<?> proxyClass) {
        this.mContext = mContext.getApplicationContext();
        this.mProxyClass = proxyClass;
    }

    public void hookLaunchActitvty() throws Exception {
        //        1.获取ActitvtyThread实例
        Class<?> actitvtyManagerClass = Class.forName("android.app.ActivityThread");
        Field scatField = actitvtyManagerClass.getDeclaredField("sCurrentActivityThread");
        scatField.setAccessible(true);//这个是privte
        Object sCurrentActitvtyThread = scatField.get(null);//这个是静态的传null
        //        2.获取ActitvtyThread的mH
        Field mhField = actitvtyManagerClass.getDeclaredField("mH");
        mhField.setAccessible(true);
        Handler mHandler = (Handler) mhField.get(sCurrentActitvtyThread);
        //        3.hook  handlerLaunchActitvty
        //给Handler设置CallBack回掉，只能反射
        Class<?> handlerClass = Class.forName("android.os.Handler");
        Field mCallBackField = handlerClass.getDeclaredField("mCallback");
        mCallBackField.setAccessible(true);
        mCallBackField.set(mHandler, new HandlerCallBack());

    }

    /**
     * hook start activity
     */
    public void hookStartActitvtyUtil() throws Exception {

        //3.1获取ActivityManagerNative中的gDefault
        Class<?> amnClass = Class.forName("android.app.ActivityManagerNative");
        //获取属性   private static final Singleton<IActivityManager> gDefault = new Singleton<IActivityManager>() {
        Field gDefaultField = amnClass.getDeclaredField("gDefault");
        //设置权限 private
        gDefaultField.setAccessible(true);
        Object gDefault = gDefaultField.get(null);//是static

        //3.2 获取gDefault中的mInstance属性
        Class<?> singletonClass = Class.forName("android.util.Singleton");
        Field mInstanceFiled = singletonClass.getDeclaredField("mInstance");
        mInstanceFiled.setAccessible(true);
        Object iamInstance = mInstanceFiled.get(gDefault);


        //IActivityManager   {@hide}
        // 动态代理Hook下钩子
        Class<?> iamClass = Class.forName("android.app.IActivityManager");
        iamInstance = Proxy.newProxyInstance(Ho0kStartActitvtyUtil.class.getClassLoader(),
                new Class[]{iamClass},
                new StartActitvtyInvocationHandler(iamInstance)
        ); //InvocationHandler 必须执行者，谁去执行他。

        //3 重新指定  gDefault替换未自己的代理-----------> 注入
        mInstanceFiled.set(gDefault, iamInstance);
    }


    private class StartActitvtyInvocationHandler implements InvocationHandler {
        //方法的执行者
        private Object object;

        public StartActitvtyInvocationHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Log.e(TAG, method.getName());
            //替换Intent，过AndroidManifest.xml检测
            if (method.getName().equals("startActivity")) {
                //1.首先获取原来的Intent
                Intent originIntent = (Intent) args[2];
                //2.创建一个安全的
                Intent safeIntent = new Intent(mContext, mProxyClass);
//                safeIntent.setComponent(new ComponentName(mContext,mProxyClass));
                args[2] = safeIntent;
                //绑定原来的Itent 偷梁换柱 启动的是代理actitvty
                safeIntent.putExtra(EXTRA_ORIGIN_INTENT, originIntent);
            }
            return method.invoke(object, args);
        }
    }


    private class HandlerCallBack implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            Log.e(TAG, "handleMessage");
            //每发一个消息都会走一次这个CallBack方法
            if (msg.what == 100) {
                handleLaunchActitvty(msg);
            }
            return false;
        }


    }

    //开始启动创建拦截actitvty
    private void handleLaunchActitvty(Message msg) {
        try {
            Object record = msg.obj;
            //1.record获取过安检的intent
            Field intentField = record.getClass().getDeclaredField("intent");
            intentField.setAccessible(true);
            Intent safeIntent = (Intent) intentField.get(record);
            //2.从safeIntent中获取originIntent   // 代理意图
            Intent origiIntent = safeIntent.getParcelableExtra(EXTRA_ORIGIN_INTENT);
            //3.重新设置回去
            if (origiIntent != null) {
                intentField.set(record, origiIntent);//origiIntent 不能过检测的intent----->替换意图
            }

            // 兼容AppCompatActivity报错问题
            Class<?> forName = Class.forName("android.app.ActivityThread");
            Field field = forName.getDeclaredField("sCurrentActivityThread");
            field.setAccessible(true);
            Object activityThread = field.get(null);
            //我自己执行一次那么就好创建PackageManager，系统再次获取的时候就是下面的iPackageManager
            Method getPackageManager = activityThread.getClass().getDeclaredMethod("getPackageManager");
            Object iPackageManager = getPackageManager.invoke(activityThread);

            PackageManagerHandler handler = new PackageManagerHandler(iPackageManager);
            Class<?> iPackageManagerIntercept = Class.forName("android.content.pm.IPackageManager");
            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class<?>[]{iPackageManagerIntercept}, handler);
            // 获取 sPackageManager 属性
            Field iPackageManagerField = activityThread.getClass().getDeclaredField("sPackageManager");
            iPackageManagerField.setAccessible(true);
            iPackageManagerField.set(activityThread, proxy);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public class PackageManagerHandler implements InvocationHandler{
        private Object mActivityManagerObject;

        public PackageManagerHandler(Object mActivityManagerObject) {
            this.mActivityManagerObject = mActivityManagerObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().startsWith("getActivityInfo")){
                ComponentName componentName=new ComponentName(mContext,mProxyClass);
                args[0]=componentName;
            }
            return method.invoke(mActivityManagerObject,args);
        }
    }


/*
    public abstract class Singleton<T> {
        private T mInstance;
        protected abstract T create();
        public final T get() {
            synchronized (this) {
                if (mInstance == null) {
                    mInstance = create();
                }
                return mInstance;
            }
        }
    }*/


/*
    07-07 14:12:19.948 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: getActivityDisplayId
    07-07 14:12:19.952 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: getContentProvider
    07-07 14:12:19.953 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: setTaskDescription
    07-07 14:12:19.987 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: checkPermission
    07-07 14:12:19.987 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: checkPermission
    07-07 14:12:19.989 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: getActivityStackId
    07-07 14:12:20.066 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: getActivityOptions
    07-07 14:12:20.067 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: getActivityOptions
    07-07 14:12:20.068 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: reportSizeConfigurations
    07-07 14:12:20.069 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: isTopOfTask
    07-07 14:12:20.092 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: setRenderThread

    [ 07-07 14:12:20.098  3560: 3560 D/         ]
    HostConnection::get() New Host Connection established 0xaf32de00, tid 3560


            [ 07-07 14:12:20.107  3560: 3560 W/         ]
    Process pipe failed
    07-07 14:12:20.117 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: activityResumed
    07-07 14:12:20.375 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: activityIdle
    07-07 14:12:24.499 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: startActivity
    07-07 14:12:24.501 3560-3560/home.shitou.com.hostapp E/Ho0kStartActitvtyUtil: handleApplicationCrash
    */



}
