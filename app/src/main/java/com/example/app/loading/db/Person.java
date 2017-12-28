package com.example.app.loading.db;

/**
 * Created by PVer on 2017/6/27.
 */

public class Person extends DaoSupport{

    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
