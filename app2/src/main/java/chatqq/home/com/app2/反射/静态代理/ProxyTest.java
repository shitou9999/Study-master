package chatqq.home.com.app2.反射.静态代理;

/**
 * 静态代理测试
 */

public class ProxyTest {

    public static void main(String args[]){
        RealSubject subject = new RealSubject();
        Proxy p = new Proxy(subject);
        p.request();

//        Subject proxy = SubjectStaticFactory.getInstance();

    }

    /*
    生成静态代理类工厂
    public class SubjectStaticFactory {
        //客户类调用此工厂方法获得代理对象。
        //对客户类来说，其并不知道返回的是代理类对象还是委托类对象。
        public static Subject getInstance(){
            return new Proxy(new RealSubject());
        }
    }*/


}
