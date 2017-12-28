package com.example.app.loading.db;

import android.text.TextUtils;

import java.util.Locale;

/**
 * 数据库类型转换类
 * Created by PVer on 2017/6/27.
 */
public class DaoUtil {

    public DaoUtil() {
        throw new UnsupportedOperationException("not init");
    }

    public static String getTableName(Class<?> clazz){
        return clazz.getSimpleName();
    }

    public static String getColumType(String type){
        String value = null;
        if (type.contains("String")){
            value=" text";
        } else if (type.contains("int")){
            value=" integer";
        }else if (type.contains("boolean")){
            value=" boolean";
        }else if (type.contains("float")){
            value=" float";
        }else if (type.contains("double")){
            value=" double";
        }else if (type.contains("char")){
            value=" varchar";
        }else if (type.contains("long")){
            value=" long";
        }
        return value;
    }

    //使这个词的第一个字母大写
    public static String capitalize(String string) {
        if (!TextUtils.isEmpty(string)){
            return string.substring(0,1).toUpperCase(Locale.US) + string.substring(1);
        }
        return string == null ? null : "";
    }


}
