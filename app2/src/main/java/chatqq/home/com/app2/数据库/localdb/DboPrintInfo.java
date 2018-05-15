package chatqq.home.com.app2.数据库.localdb;

import android.content.Context;

/**
 * 打印
 */
public class DboPrintInfo extends DBOperater {

    public DboPrintInfo(Context context) {
        super(context);
    }

    /**
     * 打印的list
     * @return
     */
    /*
    public List<PrintInfoDto> getPrintInfoList() {
        List<PrintInfoDto> lstPrintInfo = new ArrayList<PrintInfoDto>();
        Cursor cursor = null;
        if (getReadDb() != null) {
            cursor = getReadDb().query("printinfo", new String[]{"id", "plate", "berthcode",
                                    "time", "type", "money", "printText", "printText", "printTime"},
                            null, null, null, null, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        int idindex = cursor.getColumnIndex("id");
                        int plateindex = cursor.getColumnIndex("plate");
                        int berthcodeindex = cursor.getColumnIndex("berthcode");
                        int timeindex = cursor.getColumnIndex("time");
                        int typeindex = cursor.getColumnIndex("type");
                        int moneyindex = cursor.getColumnIndex("money");
                        int printtextindex = cursor.getColumnIndex("printText");
                        int printtimeindex = cursor.getColumnIndex("printTime");
                        PrintInfoDto printInfo = new PrintInfoDto();
                        printInfo.setId(cursor.getInt(idindex));
                        printInfo.setPlate(cursor.getString(plateindex));
                        printInfo.setBerthCode(cursor.getString(berthcodeindex));
                        printInfo.setTime(cursor.getString(timeindex));
                        printInfo.setType(cursor.getString(typeindex));
                        printInfo.setMoney(cursor.getDouble(moneyindex));
                        printInfo.setPrintText(cursor.getString(printtextindex));
                        printInfo.setPrintTime(cursor.getInt(printtimeindex));
                        lstPrintInfo.add(printInfo);
                    }
                }
                cursor.close();
            }
            closeDb();
        }
        return lstPrintInfo;
    }*/

    /**
     * 插入打印数据
     * @param printInfo
     * @return
     */
    /*
    public boolean insertPrintInfo(PrintInfoDto printInfo) {
        boolean result = false;
        if (printInfo == null) {
            return result;
        }
        ContentValues cv = new ContentValues();
        cv.put("plate", printInfo.getPlate());
        cv.put("berthcode", printInfo.getBerthCode());
        cv.put("time", printInfo.getTime());
        cv.put("type", printInfo.getType());
        cv.put("money", printInfo.getMoney());
        cv.put("printText", printInfo.getPrintText());
        cv.put("printTime", printInfo.getPrintTime());
        if (getWriteDb() != null) {
            if (getWriteDb().insert("printinfo", "id", cv) > 0) {
                //Log.i(TAG,"collector insert success");
                result = true;
            }
            closeDb();
        }else{
            LogUtils.d("db写入打印数据失败");
        }
        return result;
    }*/

    /**
     * 更新打印db
     * @param printInfo
     * @param id
     * @return
     */
    /*
    public boolean updataPrintInfo(PrintInfoDto printInfo, Integer id) {
        boolean result = false;
        if (printInfo == null) {
            return result;
        }
        ContentValues cv = new ContentValues();
        cv.put("plate", printInfo.getPlate());
        cv.put("berthcode", printInfo.getBerthCode());
        cv.put("time", printInfo.getTime());
        cv.put("type", printInfo.getType());
        cv.put("money", printInfo.getMoney());
        cv.put("printText", printInfo.getPrintText());
        cv.put("printTime", printInfo.getPrintTime());
        if (getWriteDb() != null) {
            if (getWriteDb().update("printinfo", cv, "id=?", new String[]{id.toString()}) > 0) {
                //Log.i(TAG,"collector insert success");
                result = true;
            }
            closeDb();
        }else{
            LogUtils.d("db更新打印数据失败");
        }
        return result;
    }*/

    /**
     * 删除某条数据
     * @param id
     * @return
     */
    /*
    public boolean deletePrintInfo(Integer id) {
        boolean ret = false;
        if (getWriteDb() != null) {
            if (getWriteDb().delete("printinfo", "id=?", new String[]{id.toString()}) > 0) {
                //Log.i(TAG, "Berth delete BerthCode="+ BerthCode);
                ret = true;
            }
            closeDb();
        }else{
            LogUtils.d("db删除打印数据失败");
        }
        return ret;
    }*/



}
