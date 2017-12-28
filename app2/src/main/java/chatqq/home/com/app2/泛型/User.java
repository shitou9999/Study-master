package chatqq.home.com.app2.泛型;

/**
 * Created by PVer on 2017/7/2.
 */

public class User {

    /**
     * 泛型构造器
     * @param t
     * @param <T>
     */
    public <T> User(T t) {
        System.out.println(t);
    }

    /**
     * 使用----》一种是显式指定泛型参数，另一种是隐式推断
     */

    /*
    //隐式
    new User(22);
    //显示
    new<String>User("hello");*/



//    如果构造器是泛型构造器，同时该类也是一个泛型类的情况下应该如何使用泛型构造器：
//    因为泛型构造器可以显式指定自己的类型参数（需要用到菱形，放在构造器之前），
//    而泛型类自己的类型实参也需要指定（菱形放在构造器之后），这就同时出现了两个菱形了，这就会有一些小问题，


//    public class User<E> {
//        public <T> User(T t) {
//            System.out.println(t);
//        }
//
//    }
//    User<String> a = new <Integer>User<>(15); 这种语法不允许，会直接编译报错！























}
