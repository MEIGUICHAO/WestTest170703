package com.example.webtest.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/*
 * 配置文件存储
 * 
 */
public class CacheUtils {

	private static final String CACHE_FILE_NAME = "MiTaoLe";
	private static SharedPreferences sp;
	private Editor editor;

	public static boolean getBoolean(Context context, String key,
									 boolean defaule) {
		if (sp == null) {
			sp = context.getSharedPreferences(CACHE_FILE_NAME,
					Context.MODE_PRIVATE);
		}
		return sp.getBoolean(key, defaule);
	}

	public static void putBoolean(Context context, String key, boolean defaule) {
		if (sp == null) {
			sp = context.getSharedPreferences(CACHE_FILE_NAME,
					Context.MODE_PRIVATE);
		}
		sp.edit().putBoolean(key, defaule).commit();
	}

	public static void putCache(Context context, String key, String value) {
		if (sp == null) {
			sp = context.getSharedPreferences(CACHE_FILE_NAME,
					Context.MODE_PRIVATE);
		}
		sp.edit().putString(key, value).commit();
	}

	public static String getCache(Context context, String key) {
		if (sp == null) {
			sp = context.getSharedPreferences(CACHE_FILE_NAME,
					Context.MODE_PRIVATE);
		}
		return sp.getString(key, null);
	}

	public static void putLongCache(Context context, String key, long value) {
		if (sp == null) {
			sp = context.getSharedPreferences(CACHE_FILE_NAME,
					Context.MODE_PRIVATE);
		}
		sp.edit().putLong(key, value).commit();
	}

	public static long getLongCache(Context context, String key) {
		if (sp == null) {
			sp = context.getSharedPreferences(CACHE_FILE_NAME,
					Context.MODE_PRIVATE);
		}
		return sp.getLong(key, 0);
	}
}
