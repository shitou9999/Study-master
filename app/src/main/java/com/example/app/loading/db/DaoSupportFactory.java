package com.example.app.loading.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

/**
 * 数据库写到内存卡中----需要加密  和NDK图片压缩很像
 * 平时是data/data/包名/db
 *  为什么用工厂，目前的数据在内存卡中，有时候需要放到data/data/包名/db中
 *  1获取的Factory不一样那么写入的位置是可以不一样的 2 方便切换其他数据库 3 高扩展
 * Created by PVer on 2017/6/27.
 */
public class DaoSupportFactory {
    public static DaoSupportFactory mFactory;
    // 数据库SQLiteDatabase
    private SQLiteDatabase mDatabase;


    //持有外部数据库的引用
    public DaoSupportFactory() {
        //判断是否有内存卡，6.0权限
        File databaseDir = new File(Environment.getExternalStorageDirectory()
                + File.separator + "nhdz" + File.separator + "database");
        if (!databaseDir.exists()) {
            databaseDir.mkdirs();
        }
        // 数据库放在外部存储卡下面（内存卡中）  nhdz/database/nhdz.db
        File dataBaseFile = new File(databaseDir, "nhdz.db");
        mDatabase = SQLiteDatabase.openOrCreateDatabase(dataBaseFile, null);
    }


    /**
     * 获取DaoSupportFactory实例
     * @return
     */
    public static DaoSupportFactory getFactory() {
        if (mFactory == null) {
            synchronized (DaoSupportFactory.class) {
                if (mFactory == null) {
                    mFactory = new DaoSupportFactory();
                }
            }
        }
        return mFactory;
    }

    /**
     * 获取IDaoSupport
     */
    public <T> IDaoSupport<T> getDaoSupport(Class<T> clazz) {
        IDaoSupport<T> daoSupport = new DaoSupport<>();
        // 初始化数据
        daoSupport.init(mDatabase, clazz);
        return daoSupport;
    }



}
