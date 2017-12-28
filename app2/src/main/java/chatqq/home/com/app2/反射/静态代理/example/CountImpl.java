package chatqq.home.com.app2.反射.静态代理.example;

/**
 * 目标对象   委托类(包含业务逻辑)
 */    
public class CountImpl implements Count {    
    
    @Override    
    public void queryCount() {    
        System.out.println("查看账户方法...");    
    
    }    
    
    @Override    
    public void updateCount() {    
        System.out.println("修改账户方法...");    
    
    }    
    
}     