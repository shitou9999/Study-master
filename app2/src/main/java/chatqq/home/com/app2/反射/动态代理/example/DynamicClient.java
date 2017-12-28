package chatqq.home.com.app2.反射.动态代理.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import chatqq.home.com.app2.反射.静态代理.example1.IBank;
import chatqq.home.com.app2.反射.静态代理.example1.Man;

/**
 * Created by PVer on 2017/7/6.
 */

public class DynamicClient {


    public static void main(String[] args){

        Man man=new Man();

        //获取代理方法  执行所有方法，每次都执行BankInvokationHandler的invoke方法
        IBank proxy = (IBank) Proxy.newProxyInstance(IBank.class.getClassLoader(),
                new Class[]{IBank.class},//接口类数组
//                man.getClass().getInterfaces()},//接口类数组
                new BankInvokationHandler(man));//回掉

        //动态代理，解析interface所有方法，新建一个class ,class的名，包名+$Proxy ,实例化了proxy对象
        //proxy 里面包含了InvocationHandler，每次掉方法其实执行的是InvokationHandler的invoke方法。

        proxy.applyBank();
        proxy.loseBank();

    }

    private static class BankInvokationHandler implements InvocationHandler{
        private IBank man;//接口类

        public BankInvokationHandler(IBank man) {
            this.man = man;
        }

        /**
         * @param proxy 代理对象  一般没啥用
         * @param method
         * @param args
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //方法回掉 方法执行还是要Man 执行
            //代理了所有方法！！！

            System.out.println("方法执行："+method.getName());

            Object object = method.invoke(man,args);

            return object;
        }
    }




}
