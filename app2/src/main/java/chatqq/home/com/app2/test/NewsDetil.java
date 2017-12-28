package chatqq.home.com.app2.test;

/**
 * abstract 某一类共性的方法
 * 抽象类中可以做方法申明，也可以做方法实现
 *  抽象类需要extends继承    而接口需要实现！！！！
 *  抽象类表示的是，这个对象是什么。接口表示的是，这个对象能做什么
 * Created by Administrator on 2017/6/30 0030.
 */

public abstract class NewsDetil {

//    抽象类里的抽象方法必须全部被子类所实现，如果子类不能全部实现父类抽象方法，
//    那么该子类只能是抽象类。同样，一个实现接口的时候，如不能全部实现接口方法，那么该类也只能为抽象类。

//    抽象类中可以做方法申明，也可以做方法实现

    void show() {

    }

     abstract void onEorr();


    void onSuccess() {

    }


}
