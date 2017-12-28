package chatqq.home.com.app2.反射;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 反射机制是在运行状态中，对于任意一个类，都能够知道这个类中的所有属性和方法；
 * 对于任意一个对象，都能够调用它的任意一个方法和属性；
 * http://www.jianshu.com/p/779b3e27b26d
 * http://blog.csdn.net/lfsf802/article/details/7239711
 * Created by PVer on 2017/7/1.
 */

public class Reflection2 {


    Person person=new Person();
    /**
     * 获得Class对象
     */
    //第一种方式 通过Class类的静态方法——forName()来实现
//    class1 = Class.forName("com.lvr.reflection.Person");
    //第二种方式 通过类的class属性
//    class1 = Person.class;
    //第三种方式 通过对象getClass方法
    Class<?> classPerson = person.getClass();

    /**
     * 获取class对象的属性，方法，构造函数
     */

    /**
     * 1.获取class对象的成员变量
     */
    Field[] allField=classPerson.getDeclaredFields();//获取class对象的所有属性
    Field[] publicField=classPerson.getFields();//获取class对象的public属性
//    Field ageField=classPerson.getDeclaredField("age");//获取class的指定属性
//    Field sexField=classPerson.getField("sex");//获取class指定的public属性

    /**
     * 获取class 对象的方法
     */
    Method[] methods=classPerson.getDeclaredMethods();//获取class对象的所有声明方法
    Method[] allMethods=classPerson.getMethods();//获取class对象的所有public方法，包括父类的方法
//    Method method=classPerson.getMethod("info",String.class);//返回次class对象对应类的,带指定形参列表的public方法
//    Method declareMethod=classPerson.getDeclaredMethod("info",String.class);//返回次class对象对应类的,带指定形参列表的方法

    /**
     * 获取class对象的构造函数
     */
    Constructor<?>[] allConstructors=classPerson.getDeclaredConstructors();//获取class对象的所有声明的构造函数
    Constructor<?>[] publicConstructors=classPerson.getConstructors();//获取class对象public构造函数
//    Constructor<> constructor=classPerson.getDeclaredConstructor(String.class);//获取指定声明的构造函数
//    Constructor publicConstructor=classPerson.getConstructor(String.class);//获取指定声明的public构造函数

    /**
     * 其他方法
     */
    Annotation[] annotations=(Annotation[])classPerson.getAnnotations();//获取class对象的所有注解
    Annotation annotation=(Annotation)classPerson.getAnnotation(Deprecated.class);//获取class对象的指定注解
    Type genericSuperClass=classPerson.getGenericSuperclass();//获取class对象的直接超类的Type
    Type[] interfaces=classPerson.getGenericInterfaces();//获取class对象的所有接口的type集合

    /**
     * 获取class对象的信息
     */
    boolean isPrimitive=classPerson.isPrimitive();//是否是基础类型
    boolean isArray=classPerson.isArray();//是否是集合
    boolean isAnnotation=classPerson.isAnnotation();//是否是注解类
    boolean isInterface=classPerson.isInterface();//是否是接口类
    boolean isEnum=classPerson.isEnum();//是否是枚举类
    boolean isAnonymousClass=classPerson.isAnonymousClass();//是否是匿名内部类
    boolean isAnnotationPresent=classPerson.isAnnotationPresent(Deprecated.class);//是否被某个注解类修饰
    String className=classPerson.getName();//获取class名字 包含包名路径
    Package aPackage=classPerson.getPackage();//获取class的包信息
    String simpleName=classPerson.getSimpleName();//获取class的类名
    int modifiers=classPerson.getModifiers();//获取class的访问权限
    Class<?>[] declaredClasses=classPerson.getDeclaredClasses();//获取内部类
    Class<?> declaringClass=classPerson.getDeclaringClass();//外部类

    /**
     * 通过Java反射生成并操作对象
     */

    /**
     * 生成类的实例对象
     *1.对象的newInstance() 2.获取对象的构造函数然后newInstance();
     */
    /*
    1.使用Class对象的newInstance()方法来创建该Class对象对应类的实例。
      这种方式要求该Class对象的对应类有默认构造器，而执行newInstance()方法时实际上是利用默认构造器来创建该类的实例。

    2.先使用Class对象获取指定的Constructor对象，再调用Constructor对象的
      newInstance()方法来创建该Class对象对应类的实例。通过这种方式可以选择使用指定的构造器来创建实例。*/

    //第一种方式 Class对象调用newInstance()方法生成
//    Object obj = classPerson.newInstance();
    //第二种方式 对象获得对应的Constructor对象，再通过该Constructor对象的newInstance()方法生成
//    Constructor<?> constructor = classPerson.getDeclaredConstructor(String.class);//获取指定声明构造函数
//    Object obj = constructor.newInstance("hello");

    /**
     * 调用类的方法
     */
    /*
    1.通过Class对象的getMethods()方法或者getMethod()方法获得指定方法，返回Method数组或对象。

    2.调用Method对象中的Object invoke(Object obj, Object... args)方法。
      第一个参数对应调用该方法的实例对象，第二个参数对应该方法的参数。*/

    /*
    // 生成新的对象：用newInstance()方法
    Object obj = classPerson.newInstance();
    //首先需要获得与该方法对应的Method对象
    Method method = class1.getDeclaredMethod("setAge", int.class);
    //调用指定的函数并传递参数
    method.invoke(obj, 28);*/

    /**
     * 当通过Method的invoke()方法来调用对应的方法时，Java会要求程序必须有调用该方法的权限。
     * 如果程序确实需要调用某个对象的private方法，则可以先调用Method对象的如下方法。
     * setAccessible(boolean flag)：将Method对象的acessible设置为指定的布尔值。
     * 值为true，指示该Method在使用时应该取消Java语言的访问权限检查；
     * 值为false，则知识该Method在使用时要实施Java语言的访问权限检查。
     */

    /**
     * 访问成员变量值
     */
    /*
    1.通过Class对象的getFields()方法或者getField()方法获得指定方法，返回Field数组或对象。

    2.Field提供了两组方法来读取或设置成员变量的值：
      getXXX(Object obj):获取obj对象的该成员变量的值。此处的XXX对应8种基本类型。
      如果该成员变量的类型是引用类型，则取消get后面的XXX。
      setXXX(Object obj,XXX val)：将obj对象的该成员变量设置成val值。*/

    /*
        //生成新的对象：用newInstance()方法
        Object obj = classPerson.newInstance();
        //获取age成员变量
        Field field = classPerson.getField("age");
        //将obj对象的age的值设置为10
        field.setInt(obj, 10);
        //获取obj对象的age的值
        field.getInt(obj);*/







}
