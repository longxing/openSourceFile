package com.asiainfo.android.wmm.demo.util;

import android.content.Context;
import android.widget.Toast;

public class UIUtils {

    public static void showToast(Context context, CharSequence text, int duration) {
        if (context != null)
            Toast.makeText(context, text, duration).show();
    }
}