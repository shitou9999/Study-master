package com.example.app.loading.db.example;

import com.example.app.loading.db.DaoSupportFactory;
import com.example.app.loading.db.IDaoSupport;

import java.util.List;

/**
 * Created by PVer on 2017/6/30.
 */

public class CacheDataUtil {

    public static String getCacheResultJson(String finalUrl){
        IDaoSupport<CacheData> dataDaoSupport = DaoSupportFactory.getFactory().getDaoSupport(CacheData.class);
        List<CacheData> cacheDataList = dataDaoSupport.querySupport().selection("mUrlKey=?").selectionArgs(finalUrl).query(); //http: 报错 最好MD5加密下url

        if (cacheDataList.size()!=0) {
            //代表有数据
            CacheData cacheData = cacheDataList.get(0);
            String resultJson = cacheData.getResultJson();
            return resultJson;
        }
        return null;
    }


    public static long cacheData(String finalUrl,String resultJson){
        IDaoSupport<CacheData> dataDaoSupport = DaoSupportFactory.getFactory().getDaoSupport(CacheData.class);
        dataDaoSupport.delete("mUrlKey=?",finalUrl);
        long number =  dataDaoSupport.insert(new CacheData(finalUrl,resultJson));
        return number;
    }
}
