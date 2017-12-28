package chatqq.home.com.app2.反射.动态代理;

/**
 * 目标对象类
 * Created by PVer on 2017/7/1.
 */

public class RealSubject implements Subject {


    @Override
    public void request() {
        System.out.println("====RealSubject Request====");
    }



}
