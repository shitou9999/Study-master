package chatqq.home.com.app2.数据库.localdb;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 某个用户的数据库操作类(泊位状态列表)
 */
public class LocalCacheDBHelper extends SQLiteOpenHelper {

    private TableInfo mTableInfo;
    private static final String mDBName = "localcache.db";
    private static final int mDBVersion = 1;
    private String mTableName;


    public LocalCacheDBHelper(Context context, String strTableName) {
        super(context, mDBName, null, mDBVersion);

        mTableInfo = new TableInfo();//
        mTableName = "[T" + strTableName + "]";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //Log.i("UCPOS SQL EXEC:",mTableInfo.CREATE_TABLE_SQL(mTableName));
        try {
            //db.execSQL("CREATE TABLE [T0527] (Id INTEGER PRIMARY KEY AUTOINCREMENT," +
            //		"recordId INTEGER,berthCode TEXT,berthStatus INTEGERarrearMoney INTEGERplate TEXT,plateColor TEXT,recordStatus TEXT,pdaCall TEXT,curtime TEXT)");
            db.execSQL(mTableInfo.CREATE_TABLE_SQL(mTableName));
        } catch (Exception e) {
            Log.i("UCPOS SQL EXEC ERROR", "On onCreate()," + mTableInfo.CREATE_TABLE_SQL(mTableName));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        //Log.i("UCPOS SQL EXEC:",mTableInfo.DELETE_TABLE_SQL(mTableName));
        //Log.i("UCPOS SQL EXEC:",mTableInfo.CREATE_TABLE_SQL(mTableName));
        try {
            db.execSQL(mTableInfo.DROP_TABLE_SQL(mTableName));//删除表
            db.execSQL(mTableInfo.CREATE_TABLE_SQL(mTableName));
        } catch (Exception e) {
            //Log.i("UCPOS SQL EXEC ERROR","On onUpgrade()");
        }
    }


    /**
     * 删除数据库的表-->表不存在，以后不能操作表
     *
     * @param strUserCode
     */
    public void DeleteInfo(String strUserCode) {
        //Log.i("UCPOS SQL EXEC:",mTableInfo.DELETE_TABLE_SQL("[T" + strUserCode + "]"));
        SQLiteDatabase sqldb = null;
        try {
            sqldb = getWritableDatabase();
            sqldb.beginTransaction();
            sqldb.execSQL(mTableInfo.DROP_TABLE_SQL("[T" + strUserCode + "]"));
        } catch (Exception e) {
            //Log.i("UCPOS SQL EXEC ERROR","On DeleteInfo()");
        } finally {
            if (sqldb != null) {
                sqldb.setTransactionSuccessful();
                sqldb.endTransaction();
                sqldb.close();
                sqldb = null;
            }
        }
    }

    /**
     * 删除表中的数据
     */
    public void loginToClearDbList(String strUserCode) {
        SQLiteDatabase sqldb = null;
        try {
            sqldb = getWritableDatabase();
            sqldb.beginTransaction();
            sqldb.execSQL(mTableInfo.DELETE_TABLE_SQL("[T" + strUserCode + "]"));
        } catch (Exception e) {
            //Log.i("UCPOS SQL EXEC ERROR","On DeleteInfo()");
        } finally {
            if (sqldb != null) {
                sqldb.setTransactionSuccessful();
                sqldb.endTransaction();
                sqldb.close();
                sqldb = null;
            }
        }
    }

    /**
     * 创建表
     */
    public void ReBuildInfo(String strUserCode) {
        //Log.i("UCPOS SQL EXEC:",mTableInfo.CREATE_TABLE_SQL("[T" + strUserCode + "]"));
        SQLiteDatabase sqldb = null;
        try {
            sqldb = getWritableDatabase();
            sqldb.beginTransaction();
            sqldb.execSQL(mTableInfo.CREATE_TABLE_SQL("[T" + strUserCode + "]"));
        } catch (Exception e) {
            //Log.i("UCPOS SQL EXEC ERROR","On RebuildInfo()");
        } finally {
            if (sqldb != null) {
                sqldb.setTransactionSuccessful();
                sqldb.endTransaction();
                sqldb.close();
                sqldb = null;
            }
        }
    }

    /**
     * 从本地数据库取数据
     *
     * @param strUserId
     * @return
     */
    @SuppressLint("NewApi")
    public List<HashMap<String, Object>> LoadOrderInfoList(String strUserId) {

        List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
        SQLiteDatabase sqldb = null;
        Cursor cursor = null;
        String[] columns = new String[]{mTableInfo.ID,
                mTableInfo.RECORDID,
                mTableInfo.BERTHCODE,
                mTableInfo.ALARMSTATUS,
//                mTableInfo.ARREARMONEY,
                mTableInfo.PLATE,
                mTableInfo.PLATECOLOR,
                mTableInfo.OPSTATUS};
//                mTableInfo.PDACALL,
//                mTableInfo.CURTTIME
        String section = mTableInfo.ID + " IS NOT NULL";
        try {
            sqldb = getReadableDatabase();
            cursor = sqldb.query(true, "[T" + strUserId + "]", columns, section, null, null, null, mTableInfo.ID, null, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, Object> map = new HashMap<String, Object>();

                    map.put(mTableInfo.RECORDID, cursor.getLong(cursor.getColumnIndex(mTableInfo.RECORDID)));
                    map.put(mTableInfo.BERTHCODE, cursor.getString(cursor.getColumnIndex(mTableInfo.BERTHCODE)));
                    map.put(mTableInfo.ALARMSTATUS, cursor.getString(cursor.getColumnIndex(mTableInfo.ALARMSTATUS)));
//                    map.put(mTableInfo.ARREARMONEY, cursor.getFloat(cursor.getColumnIndex(mTableInfo.ARREARMONEY)));
                    map.put(mTableInfo.PLATE, cursor.getString(cursor.getColumnIndex(mTableInfo.PLATE)));
                    map.put(mTableInfo.PLATECOLOR, cursor.getString(cursor.getColumnIndex(mTableInfo.PLATECOLOR)));
                    map.put(mTableInfo.OPSTATUS, cursor.getString(cursor.getColumnIndex(mTableInfo.OPSTATUS)));
//                    map.put(mTableInfo.PDACALL, cursor.getString(cursor.getColumnIndex(mTableInfo.PLATECOLOR)));
//                    map.put(mTableInfo.CURTTIME, cursor.getString(cursor.getColumnIndex(mTableInfo.CURTTIME)));

                    result.add(map);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.i("UCPOS SQL EXEC ERROR ", "On LoadOrderInfoList()");
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
            if (sqldb != null) {
                sqldb.close();
                sqldb = null;
            }
        }

        return result;
    }


    /**
     * 将泊位状态列表存入数据库(每次都删表，建表？不好)
     */
    public void saveOrderInfoList(List<HashMap<String, Object>> lsOrderInfo, String strUserId) {
        SQLiteDatabase sqldb = null;
        DeleteInfo(strUserId);//删除数据库的表-->表不存在，以后不能操作表
        ReBuildInfo(strUserId);//创建表
        try {
            sqldb = getWritableDatabase();
            sqldb.beginTransaction();
            ContentValues contentvalues = null;
            for (int n = 0; n < lsOrderInfo.size(); n++) {

                contentvalues = new ContentValues();
                contentvalues.put(mTableInfo.RECORDID, (Long) lsOrderInfo.get(n).get(mTableInfo.RECORDID));
                contentvalues.put(mTableInfo.BERTHCODE, (String) lsOrderInfo.get(n).get(mTableInfo.BERTHCODE));
                contentvalues.put(mTableInfo.ALARMSTATUS, (Integer) lsOrderInfo.get(n).get(mTableInfo.ALARMSTATUS));
//                contentvalues.put(mTableInfo.ARREARMONEY, (Float) lsOrderInfo.get(n).get(mTableInfo.ARREARMONEY));
                contentvalues.put(mTableInfo.PLATE, (String) lsOrderInfo.get(n).get(mTableInfo.PLATE));
                contentvalues.put(mTableInfo.PLATECOLOR, (String) lsOrderInfo.get(n).get(mTableInfo.PLATECOLOR));
                contentvalues.put(mTableInfo.OPSTATUS, (String) lsOrderInfo.get(n).get(mTableInfo.OPSTATUS));
//                contentvalues.put(mTableInfo.PDACALL, (String) lsOrderInfo.get(n).get(mTableInfo.PDACALL));
//                contentvalues.put(mTableInfo.CURTTIME, (String) lsOrderInfo.get(n).get(mTableInfo.CURTTIME));

                long result = sqldb.insert("[T" + strUserId + "]", null, contentvalues);
                //Log.i("UCPOS SQL INSERT "," On SaveOrderInfoList() result = " + result);
            }
        } catch (Exception e) {
            //Log.i("UCPOS SQL EXEC ERROR "," On SaveOrderInfoList()");
        } finally {
            if (sqldb != null && sqldb.isOpen()) {
                sqldb.setTransactionSuccessful();
                sqldb.endTransaction();
                sqldb.close();
                sqldb = null;
            }
        }
    }

}
