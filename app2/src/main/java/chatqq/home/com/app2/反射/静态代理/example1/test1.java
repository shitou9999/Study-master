package chatqq.home.com.app2.反射.静态代理.example1;

/**
 * Created by PVer on 2017/7/6.
 */

public class test1 {


    public static void main(String[] args){
        Man man=new Man();//你自己
        Salesman salesman=new Salesman(man);//让代理干什么事
        salesman.applyBank();//代理去干
    }



}
