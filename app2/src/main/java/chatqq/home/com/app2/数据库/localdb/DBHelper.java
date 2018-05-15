package chatqq.home.com.app2.数据库.localdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // 数据库版本
    private static final int VERSION = 1;
    // pos管理员表
//	 	String create_collector= "create table if not exists collector (collector_code varchar(50) primary key,passwrod varchar(50),name varchar(50),sex varchar(2),tel varchar(20),idcard varchar(30),address text,birth varchar(20),recvtm varchar(20))"; 
//		String drop_collector = "drop table if exists collector";

    //系统消息
    String create_notify = "create table if not exists notify (id integer primary key autoincrement, notify_title varchar(50) ,notify_content varchar(50),notify_level varchar(2),notify_type varchar(2),pdausercode varchar(20),systime varchar(20),isread varchar(2) default '1')";
    String drop_notify = "drop table if exists notify";

    //打印信息表
    String create_printinfo = "create table if not exists printinfo (id integer primary key autoincrement, plate varchar(10) ," +
            "berthcode varchar(10),time varchar(20),type varchar(2),money DOUBLE,printText TEXT,printTime integer)";
//    FLOAT
    String drop_printinfo = "drop table if exists printinfo";

    public DBHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    public DBHelper(Context context, String name) {
        this(context, name, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_notify);  //系统消息
        db.execSQL(create_printinfo); //打印信息
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(drop_notify);  //删除系统消息表
        db.execSQL(create_notify);  //创建系统消息
        db.execSQL(drop_printinfo);  //删除打印信息表
        db.execSQL(create_printinfo);  //创建打印信息表

    }

}