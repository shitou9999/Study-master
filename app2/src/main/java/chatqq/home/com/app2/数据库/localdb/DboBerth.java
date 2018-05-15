package chatqq.home.com.app2.数据库.localdb;

import android.content.Context;

public class DboBerth extends DBOperater {


    public DboBerth(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    //------------------------------------------------------------------------------
    //泊位信息
//	public boolean deleteBerth(String collector_code){
//		 
//		boolean ret = false;
//		if (getWriteDb() != null) {
//			if (getWriteDb().delete("berth", "collector_code=?",
//					new String[] {collector_code}) > 0) {
//				//Log.i(TAG, "Berth delete BerthCode="+ BerthCode);
//				ret = true;
//			}
//			closeDb();
//		}
//		return ret;
//	}
//	public List<Berth> getListBerth(String collector_code) {//
//		// Log.i(TAG, "user getUserListData");
//		List<Berth> lstBerth = new ArrayList<Berth>();
//		Cursor cursor = null;
//		if (getReadDb() != null) {
//			cursor = getReadDb().query("berth",new String[] { "collector_code", "berth_code",
//									"berth_name", "berth_desc","recvtm" }, "collector_code=?",
//                                  new String[] {collector_code}, null, null, null);
//
//			if (cursor != null) {
//				if (cursor.getCount() > 0) {
//					for (cursor.moveToFirst(); !cursor.isAfterLast();
//						 cursor.moveToNext()) {
//						int collector_codeindex = cursor.getColumnIndex("collector_code");
//						int berth_codeindex = cursor.getColumnIndex("berth_code");
//						int berth_nameindex = cursor.getColumnIndex("berth_name");
//						int berth_descindex = cursor.getColumnIndex("berth_desc"); 
//						int recvtmindex = cursor.getColumnIndex("recvtm");
//						Berth berth = new Berth();
//						berth.setCollector_code(cursor.getString(collector_codeindex));
//						berth.setBerth_code(cursor.getString(berth_codeindex));
//						berth.setBerth_name(cursor.getString(berth_nameindex));
//						berth.setBerth_desc(cursor.getString(berth_descindex));
//						berth.setRecvtm(cursor.getString(recvtmindex));
//						lstBerth.add(berth);
//					}
//				}
//				cursor.close();
//			}
//
//			closeDb();
//		}
//		return lstBerth;
//	}
//
//	public int insertListBerth(List<Berth> lstBerth)//
//	{//返回插入成功的数量
//		int retNum=0;
//		if (lstBerth != null && lstBerth.size() > 0) {
//			for (int i = 0; i < lstBerth.size(); i++) {
//				Berth berth = lstBerth.get(i);
//				if (null != berth) {
//					ContentValues cv = new ContentValues();
//					cv.put("collector_code", berth.getCollector_code());
//					cv.put("berth_code", berth.getBerth_code());
//					cv.put("berth_name", berth.getBerth_name());
//					cv.put("berth_desc", berth.getBerth_desc()); 
//					cv.put("recvtm", berth.getRecvtm());
//					if (getWriteDb() != null) {
//						if (getWriteDb().insert("berth","berth_code", cv) > 0) {
//							// Log.i(TAG,"collector insert success");
//							retNum++;
//						}
//						closeDb();
//					}
//				}
//			}
//		}
//		return retNum;
//	}


}