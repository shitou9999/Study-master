package chatqq.home.com.app2.单例.懒汉模式;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class Singleton {

    private static Singleton instance;

    private  Singleton() {
    }

    //懒汉模式（线程不安全） ,,很懒，只有在你要调用它的时候，通过自己写的方法里面来对他实例化
    //懒汉模式申明了一个静态对象，在用户第一次调用时初始化，虽然节约了资源，但第一次加载时需要实例化，反映稍慢一些，而且在多线程不能正常工作。
    public static Singleton getInstance() {
        if (instance==null){
            instance=new Singleton();
        }
        return instance;
    }


    //懒汉模式（线程安全）
    //调用getInstance方法时都需要进行同步，造成不必要的同步开销，而且大部分时候我们是用不到同步的，所以不建议用这种模式。
    public static synchronized Singleton getInstance2() {
        if (instance==null){
            instance=new Singleton();
        }
        return instance;
    }


}
