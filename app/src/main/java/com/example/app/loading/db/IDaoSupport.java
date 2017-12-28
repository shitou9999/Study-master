package com.example.app.loading.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by PVer on 2017/6/27.
 */

public interface IDaoSupport<T> {

    public void init(SQLiteDatabase mDatabase, Class<T> mClazz);

    public long insert(T t);

    //批量插入 测试性能
    public void insert(List<T> datas);

    //查询   按语句查询
    //获取查询的支持
    QuerySupport<T> querySupport();

    int delete(String whereClause,String... whereArgs);

    int update(T obj,String whereClause,String... whereArgs);


/*
    // 4.0 直接从工厂里面获取daoSupport
    IDaoSupport<Person> daoSupport = DaoSupportFactory.getFactory().
            getDaoSupport(Person.class);

    // 4.1 插入数据对象（单条）
    daoSupport.insert(new Person("Darren", 23));

    // 4.2 插入数据对象（批量）
    List<Person> persons = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {
        persons.add(new Person("darren", "23"));
    }
    daoSupport.insert(persons);

    // 4.3 查询所有
    List<Person> persons = daoSupport.querySupport().queryAll();

    // 4.4 根据条件进行查询
    List<Person> persons = daoSupport.querySupport()
            .selection("age = ?").selectionArgs("23").query();

    // 4.5 根据条件进行删除
    daoSupport.delete("age = ?","23");

    // 4.6 根据条件进行更新
    Person person = new Person("Jack",24);
    daoSupport.upadte(person,"age = ?","23");
    */


}
