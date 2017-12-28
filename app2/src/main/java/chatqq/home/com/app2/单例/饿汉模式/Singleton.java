package chatqq.home.com.app2.单例.饿汉模式;

/**
 * 定义：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 * Created by Administrator on 2017/7/12 0012.
 */

public class Singleton {

    //线程安全的
    //很饿，在一开始就直接创建了这个对象。类装载的时候就完成实例化。避免了线程同步问题
    //类加载时初试化，类加载较慢，获取对象的速度快，避免了多线程的同步的问题，但是没有达到懒加载的效果。
    // 如果从始至终从未使用过这个实例，则会造成内存的浪费
    private static Singleton instance = new Singleton();

    private  Singleton() {

    }

    public static Singleton getInstance() {
        return instance;
    }
}
