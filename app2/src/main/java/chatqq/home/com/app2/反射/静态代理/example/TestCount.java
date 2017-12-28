package chatqq.home.com.app2.反射.静态代理.example;

/**
 * 测试Count类
 */    
public class TestCount {    
    public static void main(String[] args) {
        //实际是通过代理类间接调用目标对象的方法
        CountImpl countImpl = new CountImpl();    
        CountProxy countProxy = new CountProxy(countImpl);    
        countProxy.updateCount();    
        countProxy.queryCount();    
    
    }    
}     
