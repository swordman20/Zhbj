package com.example.xwf.zhbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hsia on 16/5/13.
 * E-mail: xiaweifeng@live.cn
 * //TODO:文件描述
 */
public class CacheUtils {
    private static final String ZHBJSP = "splashconfig";
    private static SharedPreferences sharedPreferences;

    public static boolean putSharedPreferences(Context context, String key,boolean value){
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(ZHBJSP, Context.MODE_PRIVATE);
        }else {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(key,value);
            edit.commit();
            return true;
        }
        return false;
    }
    public static boolean getSharedPreferences(Context context, String key,boolean defValue){
        if (sharedPreferences==null) {
            sharedPreferences = context.getSharedPreferences(ZHBJSP, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, defValue);
    }
}
