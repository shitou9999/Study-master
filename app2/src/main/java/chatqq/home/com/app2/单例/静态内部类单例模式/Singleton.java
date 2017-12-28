package chatqq.home.com.app2.单例.静态内部类单例模式;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class Singleton {
    //    第一次加载Singleton类时并不会初始化sInstance，
    //    只有第一次调用getInstance方法时虚拟机加载SingletonHolder 并初始化sInstance ，
    //    这样不仅能确保线程安全也能保证Singleton类的唯一性，所以推荐使用静态内部类单例模式。
    private Singleton() {
    }

    public static Singleton getInstance() {
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final Singleton sInstance = new Singleton();
    }


}
