package chatqq.home.com.app2.反射.静态代理.example1;

/**
 * 办卡的人
 */

public class Man implements IBank{


    @Override
    public void applyBank() {
        System.out.println("我要去办卡。。。。。。。");
    }

    @Override
    public void loseBank() {

    }


}
