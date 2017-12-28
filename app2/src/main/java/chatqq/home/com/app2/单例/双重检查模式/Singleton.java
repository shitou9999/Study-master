package chatqq.home.com.app2.单例.双重检查模式;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class Singleton {
//一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：

    //保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
    //禁止进行指令重排序。
    //volatile会或多或少的影响性能，但考虑到程序的正确性，牺牲这点性能还是值得的
    private volatile static Singleton instance;

    //：线程安全；延迟加载；效率较高。
    private  Singleton() {
    }

    public static Singleton getInstance() {
        if (instance==null){
            synchronized (Singleton.class){
                if (instance== null) {
                    instance= new Singleton();
                }
            }
        }
        return instance;
    }
}
