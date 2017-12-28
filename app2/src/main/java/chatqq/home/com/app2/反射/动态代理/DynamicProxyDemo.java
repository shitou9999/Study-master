package chatqq.home.com.app2.反射.动态代理;

import java.lang.reflect.Proxy;

/**
 * Created by PVer on 2017/7/1.
 */

public class DynamicProxyDemo {

//    动态代理实现步骤
//    具体步骤是：
//    a. 实现InvocationHandler接口创建自己的调用处理器
//    b. 给Proxy类提供ClassLoader和代理接口类型数组创建动态代理类
//    c. 以调用处理器类型为参数，利用反射机制得到动态代理类的构造函数
//    d. 以调用处理器对象为参数，利用动态代理类的构造函数创建动态代理类对象

    /*
    分步骤实现动态代理
    // InvocationHandlerImpl 实现了 InvocationHandler 接口，并能实现方法调用从代理类到委托类的分派转发
    // 其内部通常包含指向委托类实例的引用，用于真正执行分派转发过来的方法调用
    InvocationHandler handler = new InvocationHandlerImpl(..);

    // 通过 Proxy 为包括 Interface 接口在内的一组接口动态创建代理类的类对象
    Class clazz = Proxy.getProxyClass(classLoader, new Class[] { Interface.class, ... });

    // 通过反射从生成的类对象获得构造函数对象
    Constructor constructor = clazz.getConstructor(new Class[] { InvocationHandler.class });

    // 通过构造函数对象创建动态代理类实例
    Interface Proxy = (Interface)constructor.newInstance(new Object[] { handler });
    Proxy类的静态方法newProxyInstance对上面具体步骤的后三步做了封装，简化了动态代理对象的获取过程*/


    //    我们通过newProxyInstance就产生了一个Subject 的实例，即代理类的实例，然后就可以通过Subject .request()，
    //    就会调用InvocationHandler中的invoke()方法，传入方法Method对象，以及调用方法的参数，通过Method.invoke
    //    调用RealSubject中的方法的request()方法。同时可以在InvocationHandler中的invoke()方法加入其他执行逻辑。

    public static void main(String[] args) {
        //1.创建目标对象
        RealSubject realSubject = new RealSubject();
        //2.创建调用处理器对象
        ProxyHandler handler = new ProxyHandler(realSubject);
        //3.动态生成代理对象   通过 Proxy 直接创建动态代理类实例
        Subject proxySubject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                RealSubject.class.getInterfaces(), handler);
        //4.通过代理对象调用方法
        proxySubject.request();


    }



    /**
     * 生成动态代理对象的工厂.
     */
    /*
    public class DynProxyFactory {
        //客户类调用此工厂方法获得代理对象。
        //对客户类来说，其并不知道返回的是代理类对象还是委托类对象。
        public static Subject getInstance(){
            Subject delegate = new RealSubject();
            InvocationHandler handler = new SubjectInvocationHandler(delegate);
            Subject proxy = null;
            proxy = (Subject)Proxy.newProxyInstance(
                    delegate.getClass().getClassLoader(),
                    delegate.getClass().getInterfaces(),
                    handler);
            return proxy;
        }
    }*/


}
