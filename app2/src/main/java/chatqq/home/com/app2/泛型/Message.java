package chatqq.home.com.app2.泛型;

/**
 * 利用泛型类来构造填充泛型接口
 * Created by PVer on 2017/7/2.
 */

public class Message<T,U> implements MsgClass<T> {

    private U name;
    private T msg;

    @Override
    public T getMsg() {
        return msg;
    }

    @Override
    public void setMsg(T msg) {
        this.msg = msg;
    }

    public U getName() {
        return name;
    }

    public void setName(U name) {
        this.name = name;
    }
}
