package chatqq.home.com.app2.数据库.localdb;

/**
 * 泊位状态列表数据库表信息及操作创建数据表和删除数据表
 * delete from table 删除表的数据（表还在，仍然可以操作表）
 * drop table 从数据库中删除表（表不存在，不能操作表）
 */
public class TableInfo {

    public static final String RECORDID = "recordId";
    public static final String BERTHCODE = "berthCode";
    //	public static final String BERTHSTATUS = "berthStatus";*
    public static final String ALARMSTATUS = "alarmStatus";//"警示状态 0-无 1-正常 2-超时,"
    //	public static final String ARREARMONEY = "arrearMoney";*
    public static final String PLATE = "plate";
    public static final String PLATECOLOR = "plateColor";
    //	public static final String RECORDSTATUS = "recordStatus";*
    public static final String OPSTATUS = "opStatus";// 操作状态 0-下单 1-结算,
//	public static final String PDACALL = "pdaCall";*
//	public static final String CURTTIME = "curtime";*
//	public static final String ENDTIME = "endTime";*

    //	public static final String PLATEPHOTO = "platePhoto";
//	public static final String PANORAMAPHOTO = "panoramaPhoto";
    public static final String ID = "Id";


    public TableInfo() {
        // TODO Auto-generated constructor stub
    }

    public String CREATE_TABLE_SQL(String strTableName) {
        return "CREATE TABLE " + strTableName + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + RECORDID + " INTEGER,"
                + BERTHCODE + " TEXT,"
                + ALARMSTATUS + " TEXT,"
                + PLATE + " TEXT,"
                + PLATECOLOR + " TEXT,"
                + OPSTATUS + " TEXT"
                + " )";
    }

    //删除表
    public String DROP_TABLE_SQL(String strTableName) {
//	public String DELETE_TABLE_SQL(String strTableName){

        return "DROP TABLE " + strTableName;
    }

    //删除表的数据
    public String DELETE_TABLE_SQL(String strTableName) {
        return "DELETE FROM " + strTableName;
    }



//    return "CREATE TABLE " + strTableName + " ("
//            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + RECORDID + " INTEGER,"
//            + BERTHCODE + " TEXT,"
//            + ALARMSTATUS + " TEXT,"
//            + ARREARMONEY + " REAL,"
//            + PLATE + " TEXT,"
//            + PLATECOLOR + " TEXT,"
//            + OPSTATUS + " TEXT,"
//            + PDACALL + " TEXT,"
//            + CURTTIME + " TEXT"
//            + " )";

}
