package chatqq.home.com.app2.反射;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射
 */
public class Reflection {

    //    除了getClass()还有两种方式可以获取class对象，一是forName()，另一个则是使用名称.class来代表
    /*
    在Java程序中获得Class对象通常有如下三种方式：
            1.使用Class类的forName(String clazzName)静态方法。该方法需要传入字符串参数，
                                              该字符串参数的值是某个类的全限定名（必须添加完整包名）。

            2.调用某个类的class属性来获取该类对应的Class对象。

            3.调用某个对象的getClass()方法。该方法是java.lang.Object类中的一个方法。
    */

    /**
     * 得到某个对象的公共属性
     *
     * @param owner, fieldName
     * @return 该属性对象
     * @throws Exception
     */
    public Object getProperty(Object owner, String fieldName) throws Exception {
        //        Class ownerClass =owner.getClass();
        Field field = owner.getClass().getField(fieldName);
        Object property = field.get(owner);
        return property;
    }

    /**
     * 得到某类的静态公共属性
     *
     * @param className 类名  必须添加完整包名
     * @param fieldName 属性名
     * @return 该属性对象
     * @throws Exception
     */
    public Object getStaticProperty(String className, String fieldName) throws Exception {
        Class ownerClass = Class.forName(className);
        Field field = ownerClass.getField(fieldName);
        Object propery = field.get(ownerClass);
        return propery;
    }

    /**
     * 执行某对象方法
     *
     * @param owner      对象
     * @param methodName 方法名
     * @param args       参数
     * @return 方法返回值
     * @throws Exception
     */
    public Object invokeMethod(Object owner,String methodName,Object[] args)throws Exception{
        Class ownerClass=owner.getClass();
        Class[] argsClass=new Class[args.length];//设置参数
        for (int i=0;i<args.length;i++){
            argsClass[i]=args[i].getClass();
        }

        Method method=ownerClass.getMethod(methodName,argsClass);//得到类的方法并传值
        return method.invoke(owner,args);
    }


    /**
     * 执行某类的静态方法
     *
     * @param className  类名
     * @param methodName 方法名
     * @param args       参数数组
     * @return 执行方法返回的结果
     * @throws Exception
     */
    public Object invokeStaticMethod(String className,String methodName,Object[] args)throws Exception{
        Class ownerClass=Class.forName(className);
        Class[] argsClass=new Class[args.length];
        for (int i=0;i<args.length;i++){
            argsClass[i]=args[i].getClass();
        }

        Method method=ownerClass.getMethod(methodName,argsClass);
        return method.invoke(null,args);
    }


    /**
     * 新建实例
     *
     * @param className 类名
     * @param args      构造函数的参数
     * @return 新建的实例
     * @throws Exception
     */
    public Object newInstance(String className, Object[] args) throws Exception {
        Class newoneClass = Class.forName(className);
        Class[] argsClass = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argsClass[i] = args[i].getClass();
        }

        Constructor cons = newoneClass.getConstructor(argsClass);
        return cons.newInstance(args);
    }


    /**
     * 是不是某个类的实例
     *
     * @param obj 实例
     * @param cls 类
     * @return 如果 obj 是此类的实例，则返回 true
     */
    public boolean isInstance(Object obj, Class cls) {
        return cls.isInstance(obj);
    }



    /**
     * 得到数组中的某个元素
     *
     * @param array 数组
     * @param index 索引
     * @return 返回指定数组对象中索引组件的值
     */
    public Object getByArray(Object array, int index) {
        return Array.get(array, index);
    }
}