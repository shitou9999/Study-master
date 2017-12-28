package chatqq.home.com.app2.反射;

/**
 * 反射测试类
 * Created by PVer on 2017/7/1.
 */
public class Person {

    private String name;

    private int age;

    private boolean sex;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }


    public Person(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
