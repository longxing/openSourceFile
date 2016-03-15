package com.asiainfo.android.wmm.demo.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.asiainfo.android.common.util.Values;


public class AppUtils {
    public static String getMetaDataString(Context context, String key, String defaultValue) {
        Bundle metaData = getMetaData(context);
        String value = metaData != null ? metaData.getString(key) : null;

        return Values.value(value, defaultValue);
    }

    public static boolean getMetaDataBoolean(Context context, String key, boolean defaultValue) {
        Bundle metaData = getMetaData(context);
        boolean value = metaData != null ? metaData.getBoolean(key) : defaultValue;

        return Values.value(value, defaultValue);
    }

    public static Bundle getMetaData(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return null;
        }

        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);

            return applicationInfo != null ? applicationInfo.metaData : null;
        } catch (Exception e) {
            return null;
        }
    }
}
