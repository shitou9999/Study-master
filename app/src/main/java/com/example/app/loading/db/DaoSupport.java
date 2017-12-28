package com.example.app.loading.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.ArrayMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 实现类
 * Created by PVer on 2017/6/27.
 */

public class DaoSupport<T> implements IDaoSupport<T> {


    private SQLiteDatabase mDatabase;
    private Class<T> mClazz;
    //google  AppCompatViewInflater源码  // 参数的缓存
    private static final Object[] mPutMothodArgs = new Object[2];
    //// put反射方法的缓存
    private static final Map<String, Method> mPutMethods = new ArrayMap<>();

    @Override
    public void init(SQLiteDatabase mDatabase, Class<T> clazz) {
        this.mDatabase = mDatabase;
        this.mClazz = clazz;
        //创建表
        StringBuffer sb = new StringBuffer();
        sb.append("create table if not exists ")
                .append(DaoUtil.getTableName(mClazz))
                .append("(id integer primary key autoincrement, ");

        Field[] fileds = mClazz.getDeclaredFields();
        for (Field filed : fileds) {
            filed.setAccessible(true);//设置权限
            String name = filed.getName();//通过反射获取
            String type = filed.getType().getSimpleName();// int  String booean等
            //type 需要进行转换
            sb.append(name).append(DaoUtil.getColumType(type)).append(", ");
        }

        sb.replace(sb.length() - 2, sb.length(), ")");
        String sqlString = sb.toString();
        System.out.println("表语句----》" + sqlString);

        //        表语句----》create table if not exists Person
        // create table if not exists Person

        //     ???   android.database.sqlite.SQLiteException: near "null": syntax error (code 1): , while compiling: INSERT INTO Person(null) VALUES (NULL)

        mDatabase.execSQL(sqlString);


    }

    //操作任意对象  // 单条插入
    @Override
    public long insert(T obj) {
        ContentValues values = contentValuesByObj(obj);

        return mDatabase.insert(DaoUtil.getTableName(mClazz), null, values);
    }

    // 批量插入
    @Override
    public void insert(List<T> datas) {
        //批量插入采用事物
        mDatabase.beginTransaction();
        for (T data : datas) {
            //调用单条插入
            insert(data);
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }


    private QuerySupport<T> mQuerySupport;
    @Override
    public QuerySupport<T> querySupport() {
        if (mQuerySupport==null){
            mQuerySupport=new QuerySupport<>(mDatabase,mClazz);
        }
        return mQuerySupport;
    }


    //obj 转成ContentValues
    private ContentValues contentValuesByObj(T obj) {
        ContentValues values = new ContentValues();
        Field[] fileds = mClazz.getDeclaredFields();
        for (Field filed : fileds) {
            try {
                filed.setAccessible(true);//设置权限，私有和公有
                String key = filed.getName();
                Object value = filed.get(obj);
                //put 第二个参数是类型，把他转换
                //                if (value instanceof Integer){
                //
                //                }

                mPutMothodArgs[0] = key;
                mPutMothodArgs[1] = value;

                String fileTypeName = filed.getType().getName();
                //使用反射 获取方法 put 反射在一定程度上影响性能---缓存一下，每次不用都去拿，只调用反射去执行方法
                Method putMethod = mPutMethods.get(fileTypeName);
                if (putMethod == null) {
                    putMethod = ContentValues.class.getDeclaredMethod("put",
                            String.class, value.getClass());
                    mPutMethods.put(fileTypeName, putMethod);
                }

                putMethod.invoke(values, mPutMothodArgs);
                //                putMethod.invoke(values,key,value);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mPutMothodArgs[0] = null;
                mPutMothodArgs[1] = null;
            }
        }

        return values;
    }

    //删除
    public int delete(String whereClause,String... whereArgs){
        return mDatabase.delete(DaoUtil.getTableName(mClazz),whereClause,whereArgs);
    }

    //更新
    public int update(T obj,String whereClause,String... whereArgs){
        ContentValues values = contentValuesByObj(obj);
        return mDatabase.update(DaoUtil.getTableName(mClazz),values,whereClause,whereArgs);
    }




    // (id integer primary key autoincrement, age integer, name text, $changenull, serialVersionUID long)
    //        android.database.sqlite.SQLiteException: near "$changenull": syntax error (code 1): , while compiling: create table if not exists Person
    //                (id integer primary key autoincrement, age integer, name text, $changenull, serialVersionUID long)

    //    1、现象：
    //    手机上调测时报错：Android.database.sqlite.SQLiteException: near "$change": syntax error (code 1):
    //    在使用SQLite动态创建表时报错，多了一个$change字段。
    //    于是反复读代码，发现应该不会有$change这个字段。表字段是通过注解方式实现，getDeclareFields方式获取的字段。
    //            2、经过查找，发现多了一个
    //    public static transient volatile com.android.tools.fd.runtime.IncrementalChange这么信息，是系统自动生成的。于是怀疑是工具的原因
    //
    //    3、查找后发现，是android studio2的Instant Run功能导致，默认该功能是开启的。关闭了该功能就好使了。
    //
    //    总结:以后用android studio使用反射就需要注意了，有可能该问题导致多了字段。如果写代码时，也可以加上判断就比较完美些

}
