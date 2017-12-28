package com.example.app.loading.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 查询支持类
 * Created by PVer on 2017/6/29.
 */
public class QuerySupport<T> {
    //查询列
    private String[] mQueryColumns;
    //查询的条件
    private String mQuerySelection;
    //查询的参数
    private String[] mQuerySelectionArgs;
    //查询分组
    private String mQueryGroupBy;
    //查询对结果进行过滤
    private String mQueryHaving;
    //查询排序
    private String mQueryOrderBy;
    //查询用于分页
    private String mQueryLimit;

    private Class<T> mClass;
    private SQLiteDatabase mSQLiteDatabase;

    public QuerySupport(SQLiteDatabase mSQLiteDatabase, Class<T> mClass) {
        this.mSQLiteDatabase = mSQLiteDatabase;
        this.mClass = mClass;
    }

    public QuerySupport columns(String... columns) {
        this.mQueryColumns = columns;
        return this;
    }

    public QuerySupport selectionArgs(String... mQuerySelectionArgs) {
        this.mQuerySelectionArgs = mQuerySelectionArgs;
        return this;
    }

    public QuerySupport having(String mQueryHaving) {
        this.mQueryHaving = mQueryHaving;
        return this;
    }

    public QuerySupport orderBy(String mQueryOrderBy) {
        this.mQueryOrderBy = mQueryOrderBy;
        return this;
    }

    public QuerySupport limit(String mQueryLimit) {
        this.mQueryLimit = mQueryLimit;
        return this;
    }

    public QuerySupport selection(String mQuerySelection) {
        this.mQuerySelection = mQuerySelection;
        return this;
    }

    //    Cursor query(String table, String[] columns, String selection,
    //                 String[] selectionArgs, String groupBy, String having,
    //                 String orderBy) {

    public List<T> query() {
        Cursor cursor = mSQLiteDatabase.query(DaoUtil.getTableName(mClass), mQueryColumns, mQuerySelection,
                mQuerySelectionArgs, mQueryGroupBy, mQueryHaving, mQueryOrderBy, mQueryLimit);
        clearQueryParams();
        return cursorToList(cursor);
    }


    public List<T> queryAll() {
        Cursor cursor = mSQLiteDatabase.query(DaoUtil.getTableName(mClass), null, null, null, null, null, null);
        return cursorToList(cursor);
    }

    //清空参数
    private void clearQueryParams() {
        mQueryColumns = null;
        mQuerySelection = null;
        mQuerySelectionArgs = null;
        mQueryGroupBy = null;
        mQueryHaving = null;
        mQueryOrderBy = null;
        mQueryLimit = null;
    }

    //通过Cursor封装成查找对象
    //return  对象集合列表
    private List<T> cursorToList(Cursor cursor) {
        List<T> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            //不断的从游标里面获取数据
            do {
                try {
                    //通过反射new 对象
                    T instance = mClass.newInstance();
                    Field[] fields = mClass.getDeclaredFields();

                    //                    cursor.getColumnIndex()

                    for (Field field : fields) {
                        //遍历属性
                        field.setAccessible(true);
                        String name = field.getName();
                        //获取角标  获取第几列
                        int index = cursor.getColumnIndex(name);
                        if (index == -1) {
                            continue;
                        }

                        //                        cursor.getInt()
                        //                          cursor.getLong()

                        //通过反射获取游标的方法  field.getType()  类型  如 String int
                        Method cursorMethod = cursorMethod(field.getType());
                        if (cursorMethod != null) {
                            //通过反射获取了values
                            Object value = cursorMethod.invoke(cursor, index);
                            if (value == null) {
                                continue;
                            }

                            //处理一些特殊的部分
                            if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                                if ("0".equals(String.valueOf(value))) {
                                    value = false;
                                } else if ("1".equals(String.valueOf(value))) {
                                    value = true;
                                }
                            } else if (field.getType() == char.class || field.getType() == Character.class) {
                                value = ((String) value).charAt(0);
                            } else if (field.getType() == Date.class) {
                                long date = (long) value;
                                if (date <= 0) {
                                    value = null;
                                } else {
                                    value = new Date(date);
                                }
                            }

                            //通过反射注入
                            field.set(instance, value);

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    //获取游标的方法
    private Method cursorMethod(Class<?> type) throws Exception {
        String methodName = getColumMethodName(type);
        //type 是String  getString(index)
        //  int getInt   boolean getBoolean
        Method method = Cursor.class.getMethod(methodName, int.class);
        return method;
    }

    private String getColumMethodName(Class<?> fieldType) {
        String typeName;
        //isPrimitive()确定指定的Class对象表示一个基本类型。
        if (fieldType.isPrimitive()) {
            typeName = DaoUtil.capitalize(fieldType.getName());
        } else {
            typeName = fieldType.getSimpleName();
        }

        String methodName = "get" + typeName;
        if ("getBoolean".equals(methodName)) {
            methodName = "getInt";
        } else if ("getChar".equals(methodName) || "getCharacter".equals(methodName)) {
            methodName = "getString";
        } else if ("getDate".equals(methodName)) {
            methodName = "getLong";
        } else if ("getInteger".equals(methodName)) {
            methodName = "getInt";
        }
        return methodName;
    }


}
