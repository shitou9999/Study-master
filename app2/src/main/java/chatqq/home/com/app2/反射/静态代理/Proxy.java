package chatqq.home.com.app2.反射.静态代理;

/**
 * 代理对象
 * 静态代理
 * 由程序员创建或特定工具自动生成源代码，再对其编译。在程序运行前，代理类的.class文件就已经存在了
 */

public class Proxy implements Subject{
    /*

    代理模式的实现思路:

    1.代理对象和目标对象均实现同一个行为接口。

    2.代理类和目标类分别具体实现接口逻辑。

    3.在代理类的构造函数中实例化一个目标对象。

    4.在代理类中调用目标对象的行为接口。

    5.客户端想要调用目标对象的行为接口，只能通过代理类来操作。*/

//    private RealSubject realSubject;

//    public Proxy(RealSubject realSubject) {
//        this.realSubject = realSubject;
//    }


    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        System.out.println("事务处理之前");
//        realSubject.request();
        subject.request();
        System.out.println("事务处理之后");
    }



}
