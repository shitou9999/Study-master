package chatqq.home.com.app2.反射.静态代理.example1;

/**
 * 银行业务员
 */

public class Salesman implements IBank{

    private IBank man;//持有办卡人的对象

    public Salesman(IBank man) {
        this.man = man;
    }

    @Override
    public void applyBank() {
        //代理 砍价
        System.out.println("先搞完一些流程");

        //调用我们的方法
        //自己掏钱
        man.applyBank();
        System.out.println("完成！");

    }

    @Override
    public void loseBank() {

    }


}
