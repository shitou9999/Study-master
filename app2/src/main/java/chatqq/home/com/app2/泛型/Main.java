package chatqq.home.com.app2.泛型;

import java.util.List;

/**
 * 泛型方法
 */
public class Main{

      public static <T> void out(T t){
                System.out.println(t);
      }

    //静态函数
    public static <T> void StaticMethod(T a) {

    }

    //普通函数
    public <T> void OrgnicMethod(T a) {

    }


//    泛型函数的返回值也可以使用泛型表示：
    public static <T> List<T> parseArray(String response, Class<T> object){
//        List<T> modelList = JSON.parseArray(response, object);
//        return modelList;
        return null;
    }


    public static void main(String[] args){
              out("hansheng");
              out(123);
      }




}