package chatqq.home.com.app2.test;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/30 0030.
 */

public class NewsDetil01 extends NewsDetil {




    @Override
    void onEorr() {

    }


    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Method method = list.getClass().getMethod("ddd", Object.class);
        method.invoke(list,"Java reflection");
        System.out.println(list.get(0));

    }




}















