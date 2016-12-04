package com.zzw.john.parttime_conmpany.utils;

import android.preference.PreferenceManager;

/**
 * Created by john(Zhewei) on 2016/12/3.
 */

public class ShareP {



    public static void setString(String tag, String content) {
        PreferenceManager.getDefaultSharedPreferences(UIUtils.getContext()).edit()
                .putString(tag, content).apply();
    }

    public static String getString(String tag) {
        return PreferenceManager.getDefaultSharedPreferences(UIUtils.getContext()).getString(tag, null);
    }

}
