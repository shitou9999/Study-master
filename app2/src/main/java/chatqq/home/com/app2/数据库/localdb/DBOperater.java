package chatqq.home.com.app2.数据库.localdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBOperater {

    static String TAG = "DBOperater";
    private DBHelper dbhelper = null;
    private SQLiteDatabase db = null;

    public DBOperater(Context context) {
        dbhelper = new DBHelper(context, "ucpos.db");
    }

    public boolean ExecSQL(String sql) {
        // 插入数据操作
        try {
            // 使用execSQL方法向表中插入数据
            if (getWriteDb() != null)
                getWriteDb().execSQL(sql);
        } catch (Exception e) {
            return false;
        } finally {
            closeDb();
        }
        return true;
    }

    public DBHelper getDbhelper() {
        return dbhelper;
    }

    public void closeDb() {
        // 与获取db成对出现
        if (db != null) {
            db.close();
        }
    }

    /**
     * 可读
     * @return
     */
    public SQLiteDatabase getReadDb() {
        if (getDbhelper() != null) {
            return db = getDbhelper().getReadableDatabase();
        } else {
            return null;
        }
    }

    /**
     * 可写
     * @return
     */
    public SQLiteDatabase getWriteDb() {
        if (getDbhelper() != null) {
            db = getDbhelper().getWritableDatabase();
            return db;
        } else {
            return null;
        }
    }
}