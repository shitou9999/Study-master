package chatqq.home.com.app2.数据库.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


public class DboSysNotify extends DBOperater {

    public DboSysNotify(Context context) {
        super(context);
    }

    /**
     * 插入消息
     * @param msg
     * @return  0 已读  1未读
     */
    /*
    public boolean insertNotify(SysPdaUserNotify msg) {
        boolean ret = false;
        if (msg != null) {
            ContentValues cv = new ContentValues();
            cv.put("notify_title", msg.getNotifyTitle());
            cv.put("notify_content", msg.getNotifyContent());
            cv.put("notify_level", msg.getNotifyLevel());
            cv.put("notify_type", msg.getNotifyType());
            cv.put("pdausercode", msg.getPdaUserCode());
            cv.put("systime", msg.getSysTime());
            cv.put("isread", "1");
            if (getWriteDb() != null) {
                if (getWriteDb().insert("notify", "id", cv) > 0) {
                    // Log.i(TAG,"collector insert success");
                    ret = true;
                }
                closeDb();
            }
        }
        return ret;
    }*/

    /**
     * 删除消息
     *
     * @param msg
     * @return
     */
    public boolean deleteNotify(String msg) {
        boolean ret = false;
        if (msg != null) {
            if (getWriteDb() != null) {
                if (getWriteDb().delete("notify", "systime=?", new String[]{msg}) > 0) {
                    ret = true;
                }
                closeDb();
            }
        }
        return ret;
    }


    /**
     * 获取当前未读信息
     *
     * @return
     */
    public int getListNotifyCount() {
        int num = 0;
        Cursor cursor = null;
        if (getReadDb() != null) {
            cursor = getReadDb().query("notify", new String[]{"count(id) as num"},
                    "isread=? ", new String[]{"1"}, null, null, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    int numindex = cursor.getColumnIndex("num");
                    num = cursor.getInt(numindex);
                }
                cursor.close();
            }
            closeDb();
        }
        return num;
    }

    /**
     * 获取当前日期后面的所有数据 包括当前日期
     *
     * @return
     */
    /*
    public List<SysPdaUserNotify> getList() {
        List<SysPdaUserNotify> listNotify = new ArrayList<SysPdaUserNotify>();
        if (getReadDb() != null) {
//            Cursor cursor = getReadDb().query("notify", new String[]{"id", "notify_title", "notify_content", "notify_level", "notify_type",
//                    "pdausercode", "systime", "isread"}, null, null, null, null, "systime desc");
            String sql= "SELECT * FROM notify ORDER BY systime desc;";
            Cursor cursor = getReadDb().rawQuery(sql,null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        int idindex = cursor.getColumnIndex("id");
                        int titleindex = cursor.getColumnIndex("notify_title");
                        int contentindex = cursor.getColumnIndex("notify_content");
                        int levelindex = cursor.getColumnIndex("notify_level");
                        int typeindex = cursor.getColumnIndex("notify_type");
                        int pdausercodeindex = cursor.getColumnIndex("pdausercode");
                        int systimeindex = cursor.getColumnIndex("systime");
                        int isreadindex = cursor.getColumnIndex("isread");

                        SysPdaUserNotify notify = new SysPdaUserNotify();
                        notify.setId(Integer.valueOf(cursor.getString(idindex)));
                        notify.setNotifyTitle(cursor.getString(titleindex));
                        notify.setNotifyContent(cursor.getString(contentindex));
                        notify.setNotifyLevel(cursor.getString(levelindex));
                        notify.setNotifyType(cursor.getString(typeindex));
                        notify.setPdaUserCode(cursor.getString(pdausercodeindex));
                        notify.setSysTime(cursor.getString(systimeindex));
                        notify.setIsread(cursor.getString(isreadindex));
                        listNotify.add(notify);
                    }
                }
                cursor.close();
            }
            closeDb();
        }
        return listNotify;
    }*/

    /**
     * 更新消息标记(消息已读)
     *
     * @return
     */
    public boolean updateNotifyAll() {
        boolean ret = false;
        ContentValues cv = new ContentValues();
        cv.put("isread", "0");
        if (getWriteDb() != null) {
            if (getWriteDb().update("notify", cv, "", null) > 0) {
                ret = true;
            }
            closeDb();
        }
        return ret;
    }

    /**
     * 更新消息标记
     *
     * @param msg
     * @return
     */
    /*
    public boolean updateNotifyById(SysPdaUserNotify msg) {
        // 更新消息标记
        boolean ret = false;
        if (msg != null) {
            ContentValues cv = new ContentValues();
            cv.put("isread", msg.getIsread());
            if (getWriteDb() != null) {
                if (getWriteDb().update("notify", cv, "id=?", new String[]{msg.getId() + ""}) > 0) {
                    // Log.i(TAG,"collector insert success");
                    ret = true;
                }
                closeDb();
            }
        }
        return ret;
    }*/


    /**
     * 获取当前日期后面的所有数据 包括当前日期
     *
     * @return
     */
    /*
    public SysPdaUserNotify getMsgBean(String strTime) {
        SysPdaUserNotify notify = null;
        if (getReadDb() != null) {
//            cursor = getReadDb().query("notify", new String[]{"id", "notify_title", "notify_content", "notify_level", "notify_type",
//                    "pdausercode", "systime", "isread"}, "notify_content = " + strTime, null, null, null, null);
            String sql = "SELECT * FROM notify WHERE systime=?";
            Cursor cursor = getReadDb().rawQuery(sql, new String[]{strTime});
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int idindex = cursor.getColumnIndex("id");
                    int titleindex = cursor.getColumnIndex("notify_title");
                    int contentindex = cursor.getColumnIndex("notify_content");
                    int levelindex = cursor.getColumnIndex("notify_level");
                    int typeindex = cursor.getColumnIndex("notify_type");
                    int pdausercodeindex = cursor.getColumnIndex("pdausercode");
                    int systimeindex = cursor.getColumnIndex("systime");
                    int isreadindex = cursor.getColumnIndex("isread");
                    notify = new SysPdaUserNotify();
                    notify.setId(Integer.valueOf(cursor.getString(idindex)));
                    notify.setNotifyTitle(cursor.getString(titleindex));
                    notify.setNotifyContent(cursor.getString(contentindex));
                    notify.setNotifyLevel(cursor.getString(levelindex));
                    notify.setNotifyType(cursor.getString(typeindex));
                    notify.setPdaUserCode(cursor.getString(pdausercodeindex));
                    notify.setSysTime(cursor.getString(systimeindex));
                    notify.setIsread(cursor.getString(isreadindex));
                }
                cursor.close();
            }
            closeDb();
        }
        return notify;
    }*/


}
