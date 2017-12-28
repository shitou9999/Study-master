package com.example.app.loading.db.example;

/**
 * 缓存实体类
 * Created by PVer on 2017/6/29.
 */

public class CacheData {
    //请求连接
    private String mUrlKey;

    //后台返回json
    private String mResultJson;

    public CacheData() {
    }

    public CacheData(String mUrlKey, String mResultJson) {
        this.mUrlKey = mUrlKey;
        this.mResultJson = mResultJson;
    }


    public String getResultJson() {
        return mResultJson;
    }
}
